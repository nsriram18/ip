/** Command that displays all tasks currently in the task list. */
public class ListCommand extends Command {
    @Override
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
