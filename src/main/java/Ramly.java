import java.util.Scanner;
public class Ramly {
    public static void main(String[] args) {
        String line = "____________________________________________________________";
        String banner = " ____                 _       \n"
                + "|  _ \\ __ _ _ __ ___ | |_   _ \n"
                + "| |_) / _` | '_ ` _ \\| | | | |\n"
                + "|  _ < (_| | | | | | | | |_| |\n"
                + "|_| \\_\\__,_|_| |_| |_|_|\\__, |\n"
                + "                        |___/ \n";
        System.out.println(line);
        System.out.print(banner);
        System.out.println("Hello! I'm Ramly.");
        System.out.println("What do you have in mind today?");
        System.out.println(line);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();
            System.out.println(line);

            if (input.equals("bye")) {
                System.out.println("I will take my leave now. Pleasure assisting you!");
                System.out.println(line);
                break;
            }

            System.out.println(input);
            System.out.println(line);
        }

        scanner.close();

    }

}
