package ramly.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

/** Tests ordered output from the console and GUI-compatible UI adapter. */
public class UiTest {
    @Test
    public void show_multipleMessages_preservesMessageOrder() {
        ArrayList<String> messages = new ArrayList<>();
        Ui ui = new Ui(messages::add);

        ui.show("first", "second", "third");

        assertEquals(java.util.List.of("first", "second", "third"), messages);
    }
}
