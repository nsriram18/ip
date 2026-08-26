import java.util.Scanner;
import java.util.ArrayList;
import java.time.format.DateTimeParseException;
public class Ramly {
    private static final String FILE_PATH = "./data/ramly.txt";
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

        Storage storage = new Storage(FILE_PATH);
        ArrayList<Task> tasks = storage.load();
        int count = tasks.size();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();
            System.out.println(line);

            if (input.equals("bye")) {
                System.out.println("I will take my leave now. Pleasure assisting you!");
                System.out.println(line);
                break;
            } else if (input.equals("list")) {
                if (count != 0) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < count; i++) {
                        System.out.println(i + 1 + "." + tasks.get(i));
                    }
                } else {
                    System.out.println("You have no tasks in the list! Add some tasks to view them!");
                }
                System.out.println(line);
            } else if (input.startsWith("mark")) {
                try {
                    int taskIndex = Integer.parseInt(input.substring(5).trim()) - 1;
                    tasks.get(taskIndex).mark();
                    storage.save(tasks);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(" " + tasks.get(taskIndex));
                } catch (NumberFormatException e) {
                    RamlyException n = new RamlyException();
                    System.out.println(n.notANumber());
                } catch (IndexOutOfBoundsException e) {
                    RamlyException n = new RamlyException();
                    System.out.println(n.invalidNumber());
                } finally {
                    System.out.println(line);
                }

            } else if (input.startsWith("unmark")) {
                try {
                    int taskIndex = Integer.parseInt(input.substring(7).trim()) - 1;
                    tasks.get(taskIndex).unmark();
                    storage.save(tasks);
                    System.out.println("Orite, I've marked this task as not done yet:");
                    System.out.println(" " + tasks.get(taskIndex));
                } catch (NumberFormatException e) {
                    RamlyException n = new RamlyException();
                    System.out.println(n.notANumber());
                } catch (IndexOutOfBoundsException e) {
                    RamlyException n = new RamlyException();
                    System.out.println(n.invalidNumber());
                } finally {
                    System.out.println(line);
                }

            } else if (input.startsWith("todo")) {
                try {
                    Task t = new Todo(input.substring(5).trim());
                    tasks.add(t);
                    storage.save(tasks);
                    count++;
                    System.out.println("Received! I've added this task:");
                    System.out.println(" " + t);
                    System.out.println("Now you have " + count +  " tasks in the list.");
                } catch (StringIndexOutOfBoundsException e) {
                    RamlyException n = new RamlyException("todo");
                    System.out.println(n.emptyString());
                } finally {
                    System.out.println(line);
                }

            } else if (input.startsWith("deadline")) {
                try {
                    String pure = input.substring(9); // "return book /by Sunday"
                    String[] parts = pure.split(" /by ", 2);
                    Task t = new Deadline(parts[0].trim(), parts[1].trim());
                    tasks.add(t);
                    storage.save(tasks);
                    count++;
                    System.out.println("Received! I've added this task:");
                    System.out.println(" " + t);
                    System.out.println("Now you have " + count +  " tasks in the list.");
                } catch (StringIndexOutOfBoundsException e) {
                    RamlyException n = new RamlyException("deadline");
                    System.out.println(n.emptyString());
                } catch (ArrayIndexOutOfBoundsException e) {
                    RamlyException n = new RamlyException("deadline");
                    System.out.println(n.correctFormat());
                } catch (DateTimeParseException e) {
                    System.out.println("Please enter the deadline date as yyyy-MM-dd or d/M/yyyy HHmm.");
                } finally {
                    System.out.println(line);
                }

            } else if (input.startsWith("event")) {
                try {
                    String pure = input.substring(6); // "project meeting /from Mon 2pm /to 4pm"
                    String[] parts = pure.split(" /from | /to ");
                    Task t = new Event(parts[0].trim(), parts[1].trim(), parts[2].trim());
                    tasks.add(t);
                    storage.save(tasks);
                    count++;
                    System.out.println("Received! I've added this task:");
                    System.out.println(" " + t);
                    System.out.println("Now you have " + count + " tasks in the list.");
                } catch (StringIndexOutOfBoundsException e) {
                    RamlyException n = new RamlyException("event");
                    System.out.println(n.emptyString());
                } catch (ArrayIndexOutOfBoundsException e) {
                    RamlyException n = new RamlyException("event");
                    System.out.println(n.correctFormat());
                } finally {
                    System.out.println(line);
                }
            } else if (input.startsWith("delete")) {
                try {
                    int taskIndex = Integer.parseInt(input.substring(7).trim()) - 1;
                    Task t = tasks.get(taskIndex);
                    tasks.remove(taskIndex);
                    storage.save(tasks);
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(" " + t);
                    count--;
                    System.out.println("Now you have " + count + " tasks in the list.");
                } catch (NumberFormatException e) {
                    RamlyException n = new RamlyException();
                    System.out.println(n.notANumber());
                } catch (IndexOutOfBoundsException e) {
                    RamlyException n = new RamlyException();
                    System.out.println(n.invalidNumber());
                } finally {
                    System.out.println(line);
                }

            } else {
                RamlyException n = new RamlyException();
                System.out.println(n.randomWord());
                System.out.println(line);
            }

        }
        scanner.close();
    }

}
