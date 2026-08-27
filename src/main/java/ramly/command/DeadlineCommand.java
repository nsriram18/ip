package ramly.command;

import ramly.command.*; import ramly.model.*; import ramly.parser.*; import ramly.storage.*; import ramly.ui.*; import ramly.exception.*;

/** Command that adds a deadline task. */
public class DeadlineCommand extends AddTaskCommand {
    private final String description;
    private final String deadline;
    /** Creates a deadline command for the supplied description and deadline. */
    public DeadlineCommand(String description, String deadline) {
        this.description = description; this.deadline = deadline;
    }
    @Override protected Task createTask() { return new Deadline(description, deadline); }
}
