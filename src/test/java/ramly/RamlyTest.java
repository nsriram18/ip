package ramly;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import ramly.ui.Ui;

/** Tests the command-processing bridge shared by the text and JavaFX interfaces. */
public class RamlyTest {
    @TempDir
    Path temporaryDirectory;

    @Test
    public void executeCommand_addsTaskAndReturnsResponse() {
        Ramly ramly = new Ramly(temporaryDirectory.resolve("ramly.txt").toString());
        ArrayList<String> responses = new ArrayList<>();

        boolean isExit = ramly.executeCommand("todo buy milk", new Ui(responses::add));

        assertFalse(isExit);
        assertTrue(responses.stream().anyMatch(response -> response.contains("I've added this task")));
    }

    @Test
    public void executeCommand_byeReturnsExitSignal() {
        Ramly ramly = new Ramly(temporaryDirectory.resolve("ramly.txt").toString());
        ArrayList<String> responses = new ArrayList<>();

        boolean isExit = ramly.executeCommand("bye", new Ui(responses::add));

        assertTrue(isExit);
        assertTrue(responses.stream().anyMatch(response -> response.contains("take my leave")));
    }

    @Test
    public void executeCommand_nullInput_throwsAssertionError() {
        Ramly ramly = new Ramly(temporaryDirectory.resolve("ramly.txt").toString());

        assertThrows(AssertionError.class, () -> ramly.executeCommand(null, new Ui(message -> { })));
    }

    @Test
    public void executeCommand_nullOutputUi_throwsAssertionError() {
        Ramly ramly = new Ramly(temporaryDirectory.resolve("ramly.txt").toString());

        assertThrows(AssertionError.class, () -> ramly.executeCommand("list", null));
    }

    @Test
    public void executeCommand_undoAdd_removesTaskAndUpdatesStorage() throws IOException {
        Path storageFile = temporaryDirectory.resolve("ramly.txt");
        Ramly ramly = new Ramly(storageFile.toString());
        ArrayList<String> responses = new ArrayList<>();
        execute(ramly, "todo buy milk", responses);

        execute(ramly, "undo", responses);

        assertEquals(List.of(
                "Okay, I've removed the task added by the previous command:",
                " [T][ ] buy milk",
                "Now you have 0 tasks in the list."), responses);
        assertEquals("", Files.readString(storageFile));
    }

    @Test
    public void executeCommand_undoDelete_restoresOriginalPosition() {
        Ramly ramly = new Ramly(temporaryDirectory.resolve("ramly.txt").toString());
        ArrayList<String> responses = new ArrayList<>();
        execute(ramly, "todo first", responses);
        execute(ramly, "todo second", responses);
        execute(ramly, "todo third", responses);
        execute(ramly, "delete 2", responses);

        execute(ramly, "undo", responses);

        assertEquals(List.of(
                "Okay, I've restored the task deleted by the previous command:",
                " [T][ ] second",
                "Now you have 3 tasks in the list."), responses);

        execute(ramly, "list", responses);
        assertEquals(List.of(
                "Here are the tasks in your list:",
                "1.[T][ ] first",
                "2.[T][ ] second",
                "3.[T][ ] third"), responses);
    }

    @Test
    public void executeCommand_undoDeadlineAndEvent_removesAddedTask() {
        Ramly ramly = new Ramly(temporaryDirectory.resolve("ramly.txt").toString());
        ArrayList<String> responses = new ArrayList<>();
        execute(ramly, "deadline submit report /by 2019-10-15", responses);

        execute(ramly, "undo", responses);
        assertEquals(" [D][ ] submit report (by: Oct 15 2019)", responses.get(1));

        execute(ramly, "event meeting /from 10am /to 11am", responses);
        execute(ramly, "undo", responses);
        assertEquals(" [E][ ] meeting (from: 10am to: 11am)", responses.get(1));
    }

    @Test
    public void executeCommand_undoStatusChanges_restoresPreviousStatus() {
        Ramly ramly = new Ramly(temporaryDirectory.resolve("ramly.txt").toString());
        ArrayList<String> responses = new ArrayList<>();
        execute(ramly, "todo buy milk", responses);
        execute(ramly, "mark 1", responses);

        execute(ramly, "undo", responses);

        assertEquals(List.of(
                "Okay, I've restored this task's previous status:",
                " [T][ ] buy milk"), responses);

        execute(ramly, "mark 1", responses);
        execute(ramly, "unmark 1", responses);
        execute(ramly, "undo", responses);
        assertEquals(List.of(
                "Okay, I've restored this task's previous status:",
                " [T][X] buy milk"), responses);
    }

    @Test
    public void executeCommand_readOnlyAndFailedCommands_preserveUndoHistory() {
        Ramly ramly = new Ramly(temporaryDirectory.resolve("ramly.txt").toString());
        ArrayList<String> responses = new ArrayList<>();
        execute(ramly, "todo buy milk", responses);
        execute(ramly, "list", responses);
        execute(ramly, "find milk", responses);
        execute(ramly, "delete 99", responses);
        execute(ramly, "mark 99", responses);
        execute(ramly, "unmark invalid", responses);
        execute(ramly, "deadline report /by invalid", responses);
        execute(ramly, "event meeting", responses);
        execute(ramly, "hello", responses);

        execute(ramly, "undo", responses);

        assertEquals("Okay, I've removed the task added by the previous command:", responses.get(0));
    }

    @Test
    public void executeCommand_newMutationReplacesHistoryAndUndoIsSingleUse() {
        Ramly ramly = new Ramly(temporaryDirectory.resolve("ramly.txt").toString());
        ArrayList<String> responses = new ArrayList<>();
        execute(ramly, "todo first", responses);
        execute(ramly, "todo second", responses);

        execute(ramly, "undo", responses);
        execute(ramly, "list", responses);
        assertEquals(List.of("Here are the tasks in your list:", "1.[T][ ] first"), responses);

        execute(ramly, "undo", responses);
        assertEquals(List.of("There is no command to undo."), responses);
    }

    @Test
    public void executeCommand_restartedApplication_hasNoUndoHistory() {
        Path storageFile = temporaryDirectory.resolve("ramly.txt");
        Ramly firstSession = new Ramly(storageFile.toString());
        execute(firstSession, "todo buy milk", new ArrayList<>());
        Ramly secondSession = new Ramly(storageFile.toString());
        ArrayList<String> responses = new ArrayList<>();

        execute(secondSession, "undo", responses);

        assertEquals(List.of("There is no command to undo."), responses);
        execute(secondSession, "list", responses);
        assertEquals(List.of("Here are the tasks in your list:", "1.[T][ ] buy milk"), responses);
    }

    @Test
    public void executeCommand_invalidUndoSyntax_returnsSpecifiedMessages() {
        Ramly ramly = new Ramly(temporaryDirectory.resolve("ramly.txt").toString());
        ArrayList<String> responses = new ArrayList<>();

        execute(ramly, "undo 1", responses);
        assertEquals(List.of("Please use the correct command format."), responses);

        execute(ramly, "Undo", responses);
        assertEquals(List.of("OOPS! I'm sorry but I don't understand what that means! Try Again!"), responses);
    }

    /** Executes a command after clearing responses from the previous command. */
    private void execute(Ramly ramly, String command, ArrayList<String> responses) {
        responses.clear();
        ramly.executeCommand(command, new Ui(responses::add));
    }
}
