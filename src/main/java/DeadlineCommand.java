/** Command that adds a deadline task. */
public class DeadlineCommand extends Command {
    private final String description;
    private final String deadline;
    public DeadlineCommand(String description, String deadline) {
        this.description = description; this.deadline = deadline;
    }
    @Override public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task;
        try {
            task = new Deadline(description, deadline);
        } catch (java.time.format.DateTimeParseException e) {
            ui.show("Please enter the deadline date as yyyy-MM-dd or d/M/yyyy HHmm.");
            return;
        }
        tasks.add(task); storage.save(tasks);
        ui.show("Received! I've added this task:"); ui.show(" " + task);
        ui.show("Now you have " + tasks.size() + " tasks in the list.");
    }
}
