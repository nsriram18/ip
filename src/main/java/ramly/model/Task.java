package ramly.model;

import ramly.command.*; import ramly.model.*; import ramly.parser.*; import ramly.storage.*; import ramly.ui.*; import ramly.exception.*;

/** Represents a task with a description, type, and completion state. */
public class Task {
    protected String description;
    protected boolean isDone;
    protected TaskType type;

    /** Creates an incomplete task with the given description and type. */
    public Task(String description, TaskType type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    /** Returns this task's type. */
    public TaskType getType() {
        return this.type;
    }

    /** Returns the display icon corresponding to the completion state. */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    /** Marks this task as completed. */
    public void mark() {
        this.isDone = true;
    }

    /** Marks this task as incomplete. */
    public void unmark() { this.isDone = false; }

    /** Returns the serialized representation used by storage. */
    public String toFileString() {
        return type.getCode() + " | " + (isDone ? "1" : "0") + " | " + description;
    }

    @Override
    /** Returns the user-facing representation of this task. */
    public String toString() {
        return (type.getIcon() + getStatusIcon() + " " + this.description);
    }
}
