/** Command used when the user input is not recognized. */
public class UnknownCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.show(new RamlyException().randomWord());
    }
}
