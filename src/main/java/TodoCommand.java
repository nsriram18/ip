/** Command that adds a todo task. */
public class TodoCommand extends AddTaskCommand {
    private final String description;
    public TodoCommand(String description) { this.description = description; }
    @Override protected Task createTask() { return new Todo(description); }
}
