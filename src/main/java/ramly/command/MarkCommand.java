package ramly.command;

import ramly.command.*; import ramly.model.*; import ramly.parser.*; import ramly.storage.*; import ramly.ui.*; import ramly.exception.*;

/** Command that marks a task as done. */
public class MarkCommand extends TaskStateCommand {
    public MarkCommand(String command) { super(command, 5); }

    @Override
    protected void update(Task task) { task.mark(); }

    @Override
    protected String successMessage() { return "Nice! I've marked this task as done:"; }
}
