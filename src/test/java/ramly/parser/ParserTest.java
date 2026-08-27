package ramly.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;
import ramly.command.DeadlineCommand;
import ramly.command.ExitCommand;
import ramly.command.FindCommand;
import ramly.command.UnknownCommand;

/** Tests command classification and construction. */
public class ParserTest {
    private final Parser parser = new Parser();

    @Test
    public void getCommandType_knownCommands_returnsExpectedTypes() {
        assertEquals(CommandType.BYE, parser.getCommandType("bye"));
        assertEquals(CommandType.LIST, parser.getCommandType("list"));
        assertEquals(CommandType.DEADLINE,
                parser.getCommandType("deadline return book /by 2019-10-15"));
    }

    @Test
    public void getCommandType_unknownInput_returnsUnknown() {
        assertEquals(CommandType.UNKNOWN, parser.getCommandType("hello"));
    }

    @Test
    public void parse_knownAndUnknownInputs_returnsMatchingCommands() {
        assertInstanceOf(ExitCommand.class, parser.parse("bye"));
        assertInstanceOf(DeadlineCommand.class,
                parser.parse("deadline return book /by 2019-10-15"));
        assertInstanceOf(UnknownCommand.class, parser.parse("hello"));
    }

    @Test
    public void parseEvent_eventCommand_returnsThreeParts() {
        assertArrayEquals(new String[] {"meeting", "Monday", "Tuesday"},
                parser.parseEvent("event meeting /from Monday /to Tuesday"));
    }
    @Test
    public void parse_findCommand_returnsFindCommand() {
        assertEquals(CommandType.FIND, parser.getCommandType("find book"));
        assertInstanceOf(FindCommand.class, parser.parse("find book"));
    }
}
