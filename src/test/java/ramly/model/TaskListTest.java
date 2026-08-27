package ramly.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;

/** Tests task collection mutation and access. */
public class TaskListTest {
    @Test
    public void addAndGet_task_returnsStoredTask() {
        TaskList tasks = new TaskList(new ArrayList<>());
        Task task = new Todo("read a book");

        tasks.add(task);

        assertEquals(1, tasks.size());
        assertSame(task, tasks.get(0));
    }

    @Test
    public void remove_existingIndex_returnsRemovedTaskAndUpdatesSize() {
        Task task = new Todo("read a book");
        TaskList tasks = new TaskList(new ArrayList<>());
        tasks.add(task);

        assertSame(task, tasks.remove(0));
        assertEquals(0, tasks.size());
    }
}
