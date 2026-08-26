public class Ramly {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;
    private final Parser parser;

    public Ramly(String filePath) {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

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

    public static void main(String[] args) {
        new Ramly("./data/ramly.txt").run();
    }

}
