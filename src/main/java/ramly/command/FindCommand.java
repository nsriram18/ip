package ramly.command;

import java.util.ArrayList;

import ramly.model.Task;
import ramly.model.TaskList;
import ramly.storage.Storage;
import ramly.ui.Ui;

/** Command that displays tasks whose descriptions contain a keyword. */
public class FindCommand extends Command {
    private final String keyword;

    /** Creates a find command for the supplied keyword. */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /** Displays all matching tasks with their original list numbers. */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> matchingTasks = tasks.find(keyword);
        ui.show("Here are the matching tasks in your list:");
        for (Task task : matchingTasks) {
            ui.show((tasks.indexOf(task) + 1) + "." + task);
        }
    }
}
