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

        Task[] storage = new Task[100];
        int count = 0;

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();
            System.out.println(line);

            if (input.equals("bye")) {
                System.out.println("I will take my leave now. Pleasure assisting you!");
                System.out.println(line);
                break;
            } else if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < count; i++) {
                        System.out.println(i+1 + "." + storage[i]);
                }
                System.out.println(line);
            } else if (input.startsWith("mark")) {
                int taskIndex = Integer.parseInt(input.substring(5).trim()) - 1;
                storage[taskIndex].mark();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(" " + storage[taskIndex]);
                System.out.println(line);

            } else if (input.startsWith("unmark")) {
                int taskIndex = Integer.parseInt(input.substring(7).trim()) - 1;
                storage[taskIndex].unmark();
                System.out.println("Orite, I've marked this task as not done yet:");
                System.out.println(" " + storage[taskIndex]);
                System.out.println(line);

            } else if (input.startsWith("todo")) {
                storage[count] = new Todo(input.substring(5).trim());
                count++;
                System.out.println("Received! I've added this task:");
                System.out.println(" " + storage[count-1]);
                System.out.println("Now you have " + count +  " tasks in the list.");
                System.out.println(line);
            } else if (input.startsWith("deadline")) {
                String pure = input.substring(9); // "return book /by Sunday"
                String[] parts = pure.split(" /by ", 2);
                storage[count] = new Deadline(parts[0].trim(), parts[1].trim());
                count++;
                System.out.println("Received! I've added this task:");
                System.out.println(" " + storage[count-1]);
                System.out.println("Now you have " + count +  " tasks in the list.");
                System.out.println(line);
            } else if (input.startsWith("event")) {
                String pure = input.substring(6); // "project meeting /from Mon 2pm /to 4pm"
                String[] parts = pure.split(" /from | /to ");
                storage[count] = new Event(parts[0].trim(), parts[1].trim(), parts[2].trim());
                count++;
                System.out.println("Received! I've added this task:");
                System.out.println(" " + storage[count-1]);
                System.out.println("Now you have " + count +  " tasks in the list.");
                System.out.println(line);
            }
        }
        scanner.close();
    }

}
