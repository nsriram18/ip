import java.util.Scanner;

/** Handles console input and output for Ramly. */
public class Ui {
    private static final String LINE = "____________________________________________________________";
    private final Scanner scanner = new Scanner(System.in);

    /** Displays the application's initial greeting. */
    public void showWelcome() {
        System.out.println(LINE);
        System.out.println("Hello! I'm Ramly.");
        System.out.println("What do you have in mind today?");
        showLine();
    }

    /** Reads one command from the user. */
    public String readCommand() { return scanner.nextLine(); }

    public void showLine() { System.out.println(LINE); }

    public void close() { scanner.close(); }
}
