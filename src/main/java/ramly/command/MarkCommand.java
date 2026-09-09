package ramly.command;

import ramly.model.Task;
import ramly.model.TaskList;

/** Command that marks a task as done. */
public class MarkCommand extends TaskStateCommand {
    /** Creates a mark command from raw user input. */
    public MarkCommand(String command) {
        super(command, 5);
    }

    /** Marks the selected task as completed. */
    @Override
    protected Task update(TaskList tasks, int taskIndex) {
        return tasks.mark(taskIndex);
    }

    /** Returns the confirmation shown after marking a task. */
    @Override
    protected String successMessage() {
        return "Nice! I've marked this task as done:";
    }
}
