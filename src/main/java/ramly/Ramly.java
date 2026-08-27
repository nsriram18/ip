package ramly;

import ramly.command.*; import ramly.model.*; import ramly.parser.*; import ramly.storage.*; import ramly.ui.*; import ramly.exception.*;

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
            try {
                Command command = parser.parse(input);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (RuntimeException e) {
                ui.showError("I could not process that command. Please check its format.");
            } finally {
                if (!isExit) {
                    ui.showLine();
                }
            }
        }
        ui.close();
    }

    /** Starts Ramly using the default task data file. */
    public static void main(String[] args) {
        new Ramly("./data/ramly.txt").run();
    }

}
