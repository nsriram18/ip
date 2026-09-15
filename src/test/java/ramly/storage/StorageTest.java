package ramly.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import ramly.model.Task;

/** Tests storage configuration and collaborator assumptions. */
public class StorageTest {
    @TempDir
    Path temporaryDirectory;

    @Test
    public void constructor_nullFilePath_throwsAssertionError() {
        assertThrows(AssertionError.class, () -> new Storage(null));
    }

    @Test
    public void constructor_blankFilePath_throwsAssertionError() {
        assertThrows(AssertionError.class, () -> new Storage("  "));
    }

    @Test
    public void save_nullTaskList_throwsAssertionError() {
        Storage storage = new Storage("unused.txt");

        assertThrows(AssertionError.class, () -> storage.save(null));
    }

    @Test
    public void load_validAndUnknownRecords_returnsValidTasks() throws IOException {
        Path storageFile = temporaryDirectory.resolve("ramly.txt");
        Files.writeString(storageFile, String.join(System.lineSeparator(),
                "T | 1 | read a book",
                "D | 0 | submit report | 2019-10-15T00:00:00",
                "E | 0 | team meeting | 10am | 11am",
                "X | 0 | unknown task"));

        ArrayList<Task> tasks = new Storage(storageFile.toString()).load();

        assertEquals(3, tasks.size());
        assertEquals("[•][✓] read a book", tasks.get(0).toString());
        assertEquals("[⏳][ ] submit report (by: Oct 15 2019)", tasks.get(1).toString());
        assertEquals("[◆][ ] team meeting (from: 10am to: 11am)", tasks.get(2).toString());
    }
}
