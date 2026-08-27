package ramly.command;

import ramly.command.*; import ramly.model.*; import ramly.parser.*; import ramly.storage.*; import ramly.ui.*; import ramly.exception.*;

/** Command that displays all tasks currently in the task list. */
public class ListCommand extends Command {
    @Override
    /** Displays the current tasks or an empty-list message. */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.size() == 0) {
            ui.show("You have no tasks in the list! Add some tasks to view them!");
            return;
        }
        ui.show("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            ui.show(i + 1 + "." + tasks.get(i));
        }
    }
}
