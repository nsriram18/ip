/** Converts raw user command text into the parts needed by the application. */
public class Parser {
    /** Splits a deadline command into its description and deadline value. */
    public String[] parseDeadline(String command) {
        String pure = command.substring(9);
        return pure.split(" /by ", 2);
    }
}
