package ramly.model;

import ramly.command.*; import ramly.model.*; import ramly.parser.*; import ramly.storage.*; import ramly.ui.*; import ramly.exception.*;

/** Represents a task without a deadline or event period. */
public class Todo extends Task {

    protected String by;


    /** Creates a todo task with the specified description. */
    public Todo(String description) {
        super(description, TaskType.TODO);

    }

    @Override
    /** Returns the user-facing todo representation. */
    public String toString() {
        return super.toString();
    }
}
