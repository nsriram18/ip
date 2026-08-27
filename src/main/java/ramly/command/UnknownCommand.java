package ramly.command;

import ramly.command.*; import ramly.model.*; import ramly.parser.*; import ramly.storage.*; import ramly.ui.*; import ramly.exception.*;

/** Command used when the user input is not recognized. */
public class UnknownCommand extends Command {
    @Override
    /** Displays the unknown-command response. */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.show(new RamlyException().randomWord());
    }
}
