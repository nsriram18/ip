package ramly.ui;

import java.util.Scanner;
import java.util.function.Consumer;

/** Handles console input and output for Ramly. */
public class Ui {
    private static final String LINE = "____________________________________________________________";
    private final Consumer<String> output;
    private Scanner scanner;

    /** Creates a console UI that reads from standard input and writes to standard output. */
    public Ui() {
        this(System.out::println);
    }

    /** Creates an output UI that sends each displayed message to the supplied consumer. */
    public Ui(Consumer<String> output) {
        this.output = output;
    }

    /** Reads one command from the user. */
    public String readCommand() {
        if (scanner == null) {
            scanner = new Scanner(System.in);
        }
        return scanner.nextLine();
    }

    /** Displays a message to the user. */
    public void show(String message) {
        output.accept(message);
    }

    /** Displays an error message to the user. */
    public void showError(String message) {
        show(message);
    }

    /** Displays the initial banner and greeting. */
    public void showWelcome() {
        showLine();
        show(" ____                 _       \n"
                + "|  _ \\ __ _ _ __ ___ | |_   _ \n"
                + "| |_) / _` | '_ ` _ \\| | | | |\n"
                + "|  _ < (_| | | | | | | |_| |\n"
                + "|_| \\_\\__,_|_| |_| |_|_|\\__, |\n"
                + "                        |___/ \n");
        show("Hello! I'm Ramly.");
        show("What do you have in mind today?");
        showLine();
    }

    /** Displays a divider between interactions. */
    public void showLine() {
        show(LINE);
    }

    /** Releases the console input resource. */
    public void close() {
        if (scanner != null) {
            scanner.close();
        }
    }
}
