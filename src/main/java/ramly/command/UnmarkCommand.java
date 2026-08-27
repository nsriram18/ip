package ramly.command;

import ramly.command.*; import ramly.model.*; import ramly.parser.*; import ramly.storage.*; import ramly.ui.*; import ramly.exception.*;

/** Command that marks a task as not done. */
public class UnmarkCommand extends TaskStateCommand {
    /** Creates an unmark command from raw user input. */
    public UnmarkCommand(String command) { super(command, 7); }

    @Override
    /** Marks the selected task as incomplete. */
    protected void update(Task task) { task.unmark(); }

    @Override
    /** Returns the confirmation shown after unmarking a task. */
    protected String successMessage() { return "Orite, I've marked this task as not done yet:"; }
}
