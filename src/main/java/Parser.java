/** Converts raw user command text into the parts needed by the application. */
public class Parser {
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
}
