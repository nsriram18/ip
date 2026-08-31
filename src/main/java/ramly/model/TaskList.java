package ramly.model;

import java.util.ArrayList;
import java.util.Iterator;

/** Owns the collection of tasks and its basic operations. */
public class TaskList implements Iterable<Task> {
    private final ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
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
        tasks.add(task);
    }

    /** Removes and returns the task at the specified index. */
    public Task remove(int index) {
        return tasks.remove(index);
    }

    /** Returns tasks whose descriptions contain the keyword, ignoring case. */
    public ArrayList<Task> find(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.description.toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
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
