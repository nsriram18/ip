package ramly.command;

import ramly.command.*; import ramly.model.*; import ramly.parser.*; import ramly.storage.*; import ramly.ui.*; import ramly.exception.*;

/** Command that marks a task as done. */
public class MarkCommand extends TaskStateCommand {
    /** Creates a mark command from raw user input. */
    public MarkCommand(String command) { super(command, 5); }

    @Override
    /** Marks the selected task as completed. */
    protected void update(Task task) { task.mark(); }

    @Override
    /** Returns the confirmation shown after marking a task. */
    protected String successMessage() { return "Nice! I've marked this task as done:"; }
}
