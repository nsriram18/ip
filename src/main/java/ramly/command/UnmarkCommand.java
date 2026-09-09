package ramly.command;

import ramly.model.Task;
import ramly.model.TaskList;

/** Command that marks a task as not done. */
public class UnmarkCommand extends TaskStateCommand {
    /** Creates an unmark command from raw user input. */
    public UnmarkCommand(String command) {
        super(command, 7);
    }

    /** Marks the selected task as incomplete. */
    @Override
    protected Task update(TaskList tasks, int taskIndex) {
        return tasks.unmark(taskIndex);
    }

    /** Returns the confirmation shown after unmarking a task. */
    @Override
    protected String successMessage() {
        return "Orite, I've marked this task as not done yet:";
    }
}
