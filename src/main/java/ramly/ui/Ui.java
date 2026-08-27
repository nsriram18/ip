package ramly.ui;

import ramly.command.*; import ramly.model.*; import ramly.parser.*; import ramly.storage.*; import ramly.ui.*; import ramly.exception.*;

import java.util.Scanner;

/** Handles console input and output for Ramly. */
public class Ui {
    private static final String LINE = "____________________________________________________________";
    private final Scanner scanner = new Scanner(System.in);

    /** Reads one command from the user. */
    public String readCommand() { return scanner.nextLine(); }

    /** Displays a message to the user. */
    public void show(String message) { System.out.println(message); }

    /** Displays an error message to the user. */
    public void showError(String message) { show(message); }

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
    public void showLine() { System.out.println(LINE); }

    /** Releases the console input resource. */
    public void close() { scanner.close(); }
}
