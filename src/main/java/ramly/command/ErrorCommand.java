package ramly.command;

import ramly.command.*; import ramly.model.*; import ramly.parser.*; import ramly.storage.*; import ramly.ui.*; import ramly.exception.*;

/** Command that displays a parsing or validation error. */
public class ErrorCommand extends Command {
    private final String message;

    public ErrorCommand(String message) { this.message = message; }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.show(message);
    }
}
