package ramly;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.util.ArrayList;

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
}
