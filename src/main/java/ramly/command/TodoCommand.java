package ramly.command;

import ramly.command.*; import ramly.model.*; import ramly.parser.*; import ramly.storage.*; import ramly.ui.*; import ramly.exception.*;

/** Command that adds a todo task. */
public class TodoCommand extends AddTaskCommand {
    private final String description;
    public TodoCommand(String description) { this.description = description; }
    @Override protected Task createTask() { return new Todo(description); }
}
