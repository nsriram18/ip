package ramly.command;

import ramly.exception.RamlyException;
import ramly.model.Task;
import ramly.model.TaskList;
import ramly.storage.Storage;
import ramly.ui.Ui;

/** Shared workflow for commands that change a task's completion state. */
public abstract class TaskStateCommand extends Command {
    private final int taskIndex;
    private final boolean invalidNumber;

    protected TaskStateCommand(String command, int prefixLength) {
        int parsedIndex;
        boolean parseFailed;
        try {
            parsedIndex = Integer.parseInt(command.substring(prefixLength).trim()) - 1;
            parseFailed = false;
        } catch (NumberFormatException e) {
            parsedIndex = -1;
            parseFailed = true;
        }
        taskIndex = parsedIndex;
        invalidNumber = parseFailed;
    }

    /** Applies the concrete completion-state change. */
    protected abstract void update(Task task);
    /** Returns the concrete success message. */
    protected abstract String successMessage();

    /** Validates, updates, saves, and reports the selected task. */
    @Override
    public final void execute(TaskList tasks, Ui ui, Storage storage) {
        if (invalidNumber) {
            ui.show(new RamlyException().notANumber());
            return;
        }
        try {
            Task task = tasks.get(taskIndex);
            update(task);
            storage.save(tasks);
            ui.show(successMessage(), " " + task);
        } catch (IndexOutOfBoundsException e) {
            ui.show(new RamlyException().invalidNumber());
        }
    }
}
