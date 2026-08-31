package ramly.command;

import ramly.exception.RamlyException;
import ramly.model.TaskList;
import ramly.storage.Storage;
import ramly.ui.Ui;

/** Command used when the user input is not recognized. */
public class UnknownCommand extends Command {
    /** Displays the unknown-command response. */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.show(new RamlyException().randomWord());
    }
}
