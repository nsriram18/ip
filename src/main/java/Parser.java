/** Converts raw user command text into the parts needed by the application. */
public class Parser {
    /** Returns the command object for an exact exit command, or null otherwise. */
    public Command parse(String input) {
        if (input.equals("bye")) {
            return new ExitCommand();
        }
        if (input.equals("list")) {
            return new ListCommand();
        }
        if (isCommand(input, "delete")) {
            return new DeleteCommand(input);
        }
        return null;
    }

    /** Splits a deadline command into its description and deadline value. */
    public String[] parseDeadline(String command) {
        String pure = command.substring(9);
        return pure.split(" /by ", 2);
    }

    /** Returns the description in a todo command. */
    public String parseTodo(String command) {
        return command.substring(5).trim();
    }

    /** Splits an event command into its description, start, and end values. */
    public String[] parseEvent(String command) {
        String pure = command.substring(6);
        return pure.split(" /from | /to ");
    }

    /** Converts a one-based task number from a command into a zero-based index. */
    public int parseTaskIndex(String command, int prefixLength) {
        return Integer.parseInt(command.substring(prefixLength).trim()) - 1;
    }

    /** Checks whether a raw command begins with the specified command word. */
    public boolean isCommand(String input, String command) {
        return input.equals(command) || input.startsWith(command + " ");
    }
}
