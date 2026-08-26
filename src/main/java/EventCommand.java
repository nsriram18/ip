/** Command that adds an event task. */
public class EventCommand extends Command {
    private final String description;
    private final String from;
    private final String to;
    public EventCommand(String description, String from, String to) {
        this.description = description; this.from = from; this.to = to;
    }
    @Override public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = new Event(description, from, to);
        tasks.add(task); storage.save(tasks);
        ui.show("Received! I've added this task:"); ui.show(" " + task);
        ui.show("Now you have " + tasks.size() + " tasks in the list.");
    }
}
