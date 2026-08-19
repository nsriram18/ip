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

        String[] store = new String[100];
        int count = 0;

        Scanner scanner = new Scanner(System.in);


        while (true) {
            String input = scanner.nextLine();
            System.out.println(line);

            if (input.equals("bye")) {
                System.out.println("I will take my leave now. Pleasure assisting you!");
                System.out.println(line);
                break;
            }

            if (input.equals("list")) {
                String s = "";
                int c = 1;
                for (int i = 0; i < store.length; i++) {
                    if (store[i] == null) {
                        continue;
                    } else {
                        System.out.println(c + ". " + store[i]);
                        c++;
                    }
                }
                System.out.println(line);
            } else {
                store[count] = input;
                count++;
                System.out.println("added: " + input);
                System.out.println(line);
            }
        }
        scanner.close();
    }

}
