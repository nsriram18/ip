package ramly.command;

import ramly.command.*; import ramly.model.*; import ramly.parser.*; import ramly.storage.*; import ramly.ui.*; import ramly.exception.*;

/** Shared workflow for commands that create and save tasks. */
public abstract class AddTaskCommand extends Command {
    protected abstract Task createTask();

    @Override
    public final void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task task = createTask();
            tasks.add(task);
            storage.save(tasks);
            ui.show("Received! I've added this task:");
            ui.show(" " + task);
            ui.show("Now you have " + tasks.size() + " tasks in the list.");
        } catch (java.time.format.DateTimeParseException e) {
            ui.show("Please enter the deadline date as yyyy-MM-dd or d/M/yyyy HHmm.");
        }
    }
}
