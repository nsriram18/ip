/** Converts raw user command text into the parts needed by the application. */
public class Parser {
    /** Returns the command object for an exact exit command, or null otherwise. */
    public Command parse(String input) {
        try {
            if (input.equals("bye")) {
            return new ExitCommand();
        }
        if (input.equals("list")) {
            return new ListCommand();
        }
        if (isCommand(input, "mark")) {
            return new MarkCommand(input);
        }
        if (isCommand(input, "unmark")) {
            return new UnmarkCommand(input);
        }
        if (isCommand(input, "todo")) {
            return new TodoCommand(parseTodo(input));
        }
        if (isCommand(input, "deadline")) {
            String[] parts = parseDeadline(input);
            return new DeadlineCommand(parts[0].trim(), parts[1].trim());
        }
        if (isCommand(input, "event")) {
            String[] parts = parseEvent(input);
            return new EventCommand(parts[0].trim(), parts[1].trim(), parts[2].trim());
        }
        if (isCommand(input, "delete")) {
            return new DeleteCommand(input);
        }
            return new UnknownCommand();
        } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
            return new ErrorCommand("Please use the correct command format.");
        } catch (java.time.format.DateTimeParseException e) {
            return new ErrorCommand("Please enter the deadline date as yyyy-MM-dd or d/M/yyyy HHmm.");
        }
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
