package ramly.command;

import ramly.model.Task;
import ramly.model.TaskList;
import ramly.storage.Storage;
import ramly.ui.Ui;

/** Shared workflow for commands that create and save tasks. */
public abstract class AddTaskCommand extends Command {
    /** Creates the concrete task represented by this command. */
    protected abstract Task createTask();

    /** Creates, saves, and reports the newly created task. */
    @Override
    public final void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task task = createTask();
            tasks.add(task);
            storage.save(tasks);
            ui.show("Received! I've added this task:",
                    " " + task,
                    "Now you have " + tasks.size() + " tasks in the list.");
        } catch (java.time.format.DateTimeParseException e) {
            ui.show("Please enter the deadline date as yyyy-MM-dd or d/M/yyyy HHmm.");
        }
    }
}
