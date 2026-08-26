/** Command that marks a task as not done. */
public class UnmarkCommand extends TaskStateCommand {
    public UnmarkCommand(String command) { super(command, 7); }

    @Override
    protected void update(Task task) { task.unmark(); }

    @Override
    protected String successMessage() { return "Orite, I've marked this task as not done yet:"; }
}
