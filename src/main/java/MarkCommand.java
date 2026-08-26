/** Command that marks a task as done. */
public class MarkCommand extends Command {
    private final int taskIndex;
    private final boolean invalidNumber;

    public MarkCommand(String command) {
        int parsedIndex;
        boolean parseFailed;
        try {
            parsedIndex = Integer.parseInt(command.substring(5).trim()) - 1;
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
            tasks.get(taskIndex).mark();
            storage.save(tasks);
            ui.show("Nice! I've marked this task as done:");
            ui.show(" " + tasks.get(taskIndex));
        } catch (IndexOutOfBoundsException e) {
            ui.show(new RamlyException().invalidNumber());
        }
    }
}
