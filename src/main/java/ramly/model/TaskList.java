package ramly.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Collectors;

/** Owns the collection of tasks and its basic operations. */
public class TaskList implements Iterable<Task> {
    private final ArrayList<Task> tasks;

    /** Creates a task list backed by the supplied collection. */
    public TaskList(ArrayList<Task> tasks) {
        assert tasks != null : "Backing task collection must not be null";
        assert tasks.stream().noneMatch(task -> task == null) : "Task list must not contain null tasks";
        this.tasks = tasks;
    }

    /** Returns the number of tasks. */
    public int size() {
        return tasks.size();
    }

    /** Returns the task at the specified zero-based index. */
    public Task get(int index) {
        return tasks.get(index);
    }

    /** Adds a task to the collection. */
    public void add(Task task) {
        assert task != null : "Added task must not be null";
        tasks.add(task);
    }

    /** Removes and returns the task at the specified index. */
    public Task remove(int index) {
        return tasks.remove(index);
    }

    /** Returns tasks whose descriptions contain the keyword, ignoring case. */
    public ArrayList<Task> find(String keyword) {
        assert keyword != null : "Search keyword must not be null";
        String normalizedKeyword = keyword.toLowerCase();
        return tasks.stream()
                .filter(task -> task.description.toLowerCase().contains(normalizedKeyword))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /** Returns the zero-based position of a task, or -1 if it is absent. */
    public int indexOf(Task task) {
        return tasks.indexOf(task);
    }

    @Override
    public Iterator<Task> iterator() {
        return tasks.iterator();
    }
}
