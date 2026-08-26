package ramly.command;

import ramly.command.*; import ramly.model.*; import ramly.parser.*; import ramly.storage.*; import ramly.ui.*; import ramly.exception.*;

/** Command that removes a task by its one-based user-facing number. */
public class DeleteCommand extends Command {
    private final int taskIndex;
    private final boolean invalidNumber;

    public DeleteCommand(String command) {
        int parsedIndex;
        boolean parseFailed;
        try {
            parsedIndex = Integer.parseInt(command.substring(7).trim()) - 1;
            parseFailed = false;
        } catch (NumberFormatException e) {
            parsedIndex = -1;
            parseFailed = true;
        }
        this.taskIndex = parsedIndex;
        this.invalidNumber = parseFailed;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (invalidNumber) {
            ui.show(new RamlyException().notANumber());
            return;
        }
        try {
            Task task = tasks.get(taskIndex);
            tasks.remove(taskIndex);
            storage.save(tasks);
            ui.show("Noted. I've removed this task:");
            ui.show(" " + task);
            ui.show("Now you have " + tasks.size() + " tasks in the list.");
        } catch (IndexOutOfBoundsException e) {
            ui.show(new RamlyException().invalidNumber());
        }
    }
}
