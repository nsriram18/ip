/** Command that adds a todo task. */
public class TodoCommand extends Command {
    private final String description;
    public TodoCommand(String description) { this.description = description; }
    @Override public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = new Todo(description);
        tasks.add(task); storage.save(tasks);
        ui.show("Received! I've added this task:"); ui.show(" " + task);
        ui.show("Now you have " + tasks.size() + " tasks in the list.");
    }
}
