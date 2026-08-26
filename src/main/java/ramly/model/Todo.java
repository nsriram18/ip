package ramly.model;

import ramly.command.*; import ramly.model.*; import ramly.parser.*; import ramly.storage.*; import ramly.ui.*; import ramly.exception.*;

public class Todo extends Task {

    protected String by;


    public Todo(String description) {
        super(description, TaskType.TODO);

    }

    @Override
    public String toString() {
        return super.toString();
    }
}
