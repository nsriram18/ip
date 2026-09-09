package ramly.storage;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/** Tests storage configuration and collaborator assumptions. */
public class StorageTest {
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
}
