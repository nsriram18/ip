package ramly.model;

/** Represents a task occurring between a start and end time. */
public class Event extends Task {

    protected String from;
    protected String to;


    /** Creates an event with the specified description and time bounds. */
    public Event(String description, String from, String to) {
        super(description, TaskType.EVENT);
        this.from = from;
        this.to = to;
    }

    /** Returns the serialized event representation. */
    @Override
    public String toFileString() {
        return super.toFileString() + " | " + from + " | " + to;
    }

    /** Returns the user-facing event representation. */
    @Override
    public String toString() {
        return super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }
}
