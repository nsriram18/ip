public class Ramly {
    private static final String FILE_PATH = "./data/ramly.txt";
    public static void main(String[] args) {
        Ui ui = new Ui();
        Parser parser = new Parser();
        ui.showWelcome();

        Storage storage = new Storage(FILE_PATH);
        TaskList tasks = new TaskList(storage.load());

        while (true) {
            String input = ui.readCommand();
            ui.showLine();

            Command command = parser.parse(input);
            if (command != null) {
                command.execute(tasks, ui, storage);
                if (command.isExit()) {
                    break;
                }
                ui.showLine();
            }

        }
        ui.close();
    }

}
