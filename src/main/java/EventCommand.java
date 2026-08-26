/** Command that adds an event task. */
public class EventCommand extends AddTaskCommand {
    private final String description;
    private final String from;
    private final String to;
    public EventCommand(String description, String from, String to) {
        this.description = description; this.from = from; this.to = to;
    }
    @Override protected Task createTask() { return new Event(description, from, to); }
}
