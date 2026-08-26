/** Command that marks a task as not done. */
public class UnmarkCommand extends Command {
    private final int taskIndex;
    private final boolean invalidNumber;

    public UnmarkCommand(String command) {
        int parsedIndex;
        boolean parseFailed;
        try {
            parsedIndex = Integer.parseInt(command.substring(7).trim()) - 1;
            parseFailed = false;
        } catch (NumberFormatException e) {
            parsedIndex = -1;
            parseFailed = true;
        }
        taskIndex = parsedIndex;
        invalidNumber = parseFailed;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (invalidNumber) {
            ui.show(new RamlyException().notANumber());
            return;
        }
        try {
            tasks.get(taskIndex).unmark();
            storage.save(tasks);
            ui.show("Orite, I've marked this task as not done yet:");
            ui.show(" " + tasks.get(taskIndex));
        } catch (IndexOutOfBoundsException e) {
            ui.show(new RamlyException().invalidNumber());
        }
    }
}
