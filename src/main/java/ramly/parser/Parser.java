package ramly.parser;

import ramly.command.*; import ramly.model.*; import ramly.parser.*; import ramly.storage.*; import ramly.ui.*; import ramly.exception.*;

/** Converts raw user command text into the parts needed by the application. */
public class Parser {
    /** Returns the command object for an exact exit command, or null otherwise. */
    public Command parse(String input) {
        try {
            switch (getCommandType(input)) {
            case BYE: return new ExitCommand();
            case LIST: return new ListCommand();
            case MARK: return new MarkCommand(input);
            case UNMARK: return new UnmarkCommand(input);
            case TODO: return new TodoCommand(parseTodo(input));
            case DEADLINE:
                String[] deadline = parseDeadline(input);
                return new DeadlineCommand(deadline[0].trim(), deadline[1].trim());
            case EVENT:
                String[] event = parseEvent(input);
                return new EventCommand(event[0].trim(), event[1].trim(), event[2].trim());
            case DELETE: return new DeleteCommand(input);
            case FIND: return new FindCommand(parseFind(input));
            default: return new UnknownCommand();
            }
        } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
            return new ErrorCommand("Please use the correct command format.");
        } catch (java.time.format.DateTimeParseException e) {
            return new ErrorCommand("Please enter the deadline date as yyyy-MM-dd or d/M/yyyy HHmm.");
        }
    }

    /** Classifies a raw input before its command-specific fields are parsed. */
    public CommandType getCommandType(String input) {
        if (input.equals("bye")) return CommandType.BYE;
        if (input.equals("list")) return CommandType.LIST;
        for (CommandType type : CommandType.values()) {
            String word = type.name().toLowerCase();
            if (!type.equals(CommandType.BYE) && !type.equals(CommandType.LIST)
                    && !type.equals(CommandType.UNKNOWN) && isCommand(input, word)) {
                return type;
            }
        }
        return CommandType.UNKNOWN;
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

    /** Returns the keyword from a find command. */
    public String parseFind(String command) {
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
