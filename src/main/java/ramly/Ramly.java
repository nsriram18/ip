package ramly;

import ramly.command.Command;
import ramly.model.TaskList;
import ramly.parser.Parser;
import ramly.storage.Storage;
import ramly.ui.Ui;

/** Main application class that coordinates the UI, parser, storage, and commands. */
public class Ramly {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;
    private final Parser parser;

    /** Creates an application using the specified task storage file. */
    public Ramly(String filePath) {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    /** Runs the command-processing loop until an exit command is received. */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            String input = ui.readCommand();
            ui.showLine();
            isExit = executeCommand(input, ui);
            if (!isExit) {
                ui.showLine();
            }
        }
        ui.close();
    }

    /**
     * Parses and executes one command, writing every response through the supplied UI.
     *
     * @return true when the command requests that the application exits
     */
    public boolean executeCommand(String input, Ui outputUi) {
        try {
            Command command = parser.parse(input);
            command.execute(tasks, outputUi, storage);
            return command.isExit();
        } catch (RuntimeException e) {
            outputUi.showError("I could not process that command. Please check its format.");
            return false;
        }
    }

    /** Starts Ramly using the default task data file. */
    public static void main(String[] args) {
        new Ramly("./data/ramly.txt").run();
    }

}
