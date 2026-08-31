package ramly.command;

import ramly.model.TaskList;
import ramly.storage.Storage;
import ramly.ui.Ui;

/** Command that terminates the application. */
public class ExitCommand extends Command {
    /** Displays the exit message. */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.show("I will take my leave now. Pleasure assisting you!");
        ui.showLine();
    }

    /** Indicates that the application should terminate. */
    @Override
    public boolean isExit() {
        return true;
    }
}
