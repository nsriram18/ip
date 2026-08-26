/** Command that adds a deadline task. */
public class DeadlineCommand extends AddTaskCommand {
    private final String description;
    private final String deadline;
    public DeadlineCommand(String description, String deadline) {
        this.description = description; this.deadline = deadline;
    }
    @Override protected Task createTask() { return new Deadline(description, deadline); }
}
