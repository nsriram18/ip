package ramly.model;

import ramly.command.*; import ramly.model.*; import ramly.parser.*; import ramly.storage.*; import ramly.ui.*; import ramly.exception.*;

import java.util.ArrayList;

/** Owns the collection of tasks and its basic operations. */
public class TaskList implements Iterable<Task> {
    private final ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /** Returns the number of tasks. */
    public int size() { return tasks.size(); }

    /** Returns the task at the specified zero-based index. */
    public Task get(int index) { return tasks.get(index); }

    /** Adds a task to the collection. */
    public void add(Task task) { tasks.add(task); }

    /** Removes and returns the task at the specified index. */
    public Task remove(int index) { return tasks.remove(index); }

    @Override
    public java.util.Iterator<Task> iterator() { return tasks.iterator(); }
}
