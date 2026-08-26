/** Command that adds a deadline task. */
public class DeadlineCommand extends Command {
    private final String description;
    private final String deadline;
    public DeadlineCommand(String description, String deadline) {
        this.description = description; this.deadline = deadline;
    }
    @Override public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = new Deadline(description, deadline);
        tasks.add(task); storage.save(tasks);
        ui.show("Received! I've added this task:"); ui.show(" " + task);
        ui.show("Now you have " + tasks.size() + " tasks in the list.");
    }
}
