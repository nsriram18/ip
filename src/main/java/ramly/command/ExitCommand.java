package ramly.command;

import ramly.command.*; import ramly.model.*; import ramly.parser.*; import ramly.storage.*; import ramly.ui.*; import ramly.exception.*;

/** Command that terminates the application. */
public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.show("I will take my leave now. Pleasure assisting you!");
        ui.showLine();
    }

    @Override
    public boolean isExit() { return true; }
}
