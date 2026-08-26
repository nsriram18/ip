import java.util.ArrayList;
import java.time.format.DateTimeParseException;
public class Ramly {
    private static final String FILE_PATH = "./data/ramly.txt";
    public static void main(String[] args) {
        Ui ui = new Ui();
        Parser parser = new Parser();
        ui.showWelcome();

        Storage storage = new Storage(FILE_PATH);
        TaskList tasks = new TaskList(storage.load());
        int count = tasks.size();

        while (true) {
            String input = ui.readCommand();
            ui.showLine();

            if (input.equals("bye")) {
                ui.show("I will take my leave now. Pleasure assisting you!");
                ui.showLine();
                break;
            } else if (input.equals("list")) {
                if (count != 0) {
                    ui.show("Here are the tasks in your list:");
                    for (int i = 0; i < count; i++) {
                        ui.show(i + 1 + "." + tasks.get(i));
                    }
                } else {
                    ui.show("You have no tasks in the list! Add some tasks to view them!");
                }
                ui.showLine();
            } else if (input.startsWith("mark")) {
                try {
                    int taskIndex = Integer.parseInt(input.substring(5).trim()) - 1;
                    tasks.get(taskIndex).mark();
                    storage.save(tasks);
                    ui.show("Nice! I've marked this task as done:");
                    ui.show(" " + tasks.get(taskIndex));
                } catch (NumberFormatException e) {
                    RamlyException n = new RamlyException();
                    ui.show(n.notANumber());
                } catch (IndexOutOfBoundsException e) {
                    RamlyException n = new RamlyException();
                    ui.show(n.invalidNumber());
                } finally {
                    ui.showLine();
                }

            } else if (input.startsWith("unmark")) {
                try {
                    int taskIndex = Integer.parseInt(input.substring(7).trim()) - 1;
                    tasks.get(taskIndex).unmark();
                    storage.save(tasks);
                    ui.show("Orite, I've marked this task as not done yet:");
                    ui.show(" " + tasks.get(taskIndex));
                } catch (NumberFormatException e) {
                    RamlyException n = new RamlyException();
                    ui.show(n.notANumber());
                } catch (IndexOutOfBoundsException e) {
                    RamlyException n = new RamlyException();
                    ui.show(n.invalidNumber());
                } finally {
                    ui.showLine();
                }

            } else if (input.startsWith("todo")) {
                try {
                    Task t = new Todo(parser.parseTodo(input));
                    tasks.add(t);
                    storage.save(tasks);
                    count++;
                    ui.show("Received! I've added this task:");
                    ui.show(" " + t);
                    ui.show("Now you have " + count +  " tasks in the list.");
                } catch (StringIndexOutOfBoundsException e) {
                    RamlyException n = new RamlyException("todo");
                    ui.show(n.emptyString());
                } finally {
                    ui.showLine();
                }

            } else if (input.startsWith("deadline")) {
                try {
                    String[] parts = parser.parseDeadline(input);
                    Task t = new Deadline(parts[0].trim(), parts[1].trim());
                    tasks.add(t);
                    storage.save(tasks);
                    count++;
                    ui.show("Received! I've added this task:");
                    ui.show(" " + t);
                    ui.show("Now you have " + count +  " tasks in the list.");
                } catch (StringIndexOutOfBoundsException e) {
                    RamlyException n = new RamlyException("deadline");
                    ui.show(n.emptyString());
                } catch (ArrayIndexOutOfBoundsException e) {
                    RamlyException n = new RamlyException("deadline");
                    ui.show(n.correctFormat());
                } catch (DateTimeParseException e) {
                    ui.show("Please enter the deadline date as yyyy-MM-dd or d/M/yyyy HHmm.");
                } finally {
                    ui.showLine();
                }

            } else if (input.startsWith("event")) {
                try {
                    String[] parts = parser.parseEvent(input);
                    Task t = new Event(parts[0].trim(), parts[1].trim(), parts[2].trim());
                    tasks.add(t);
                    storage.save(tasks);
                    count++;
                    ui.show("Received! I've added this task:");
                    ui.show(" " + t);
                    ui.show("Now you have " + count + " tasks in the list.");
                } catch (StringIndexOutOfBoundsException e) {
                    RamlyException n = new RamlyException("event");
                    ui.show(n.emptyString());
                } catch (ArrayIndexOutOfBoundsException e) {
                    RamlyException n = new RamlyException("event");
                    ui.show(n.correctFormat());
                } finally {
                    ui.showLine();
                }
            } else if (input.startsWith("delete")) {
                try {
                    int taskIndex = Integer.parseInt(input.substring(7).trim()) - 1;
                    Task t = tasks.get(taskIndex);
                    tasks.remove(taskIndex);
                    storage.save(tasks);
                    ui.show("Noted. I've removed this task:");
                    ui.show(" " + t);
                    count--;
                    ui.show("Now you have " + count + " tasks in the list.");
                } catch (NumberFormatException e) {
                    RamlyException n = new RamlyException();
                    ui.show(n.notANumber());
                } catch (IndexOutOfBoundsException e) {
                    RamlyException n = new RamlyException();
                    ui.show(n.invalidNumber());
                } finally {
                    ui.showLine();
                }

            } else {
                RamlyException n = new RamlyException();
                ui.show(n.randomWord());
                ui.showLine();
            }

        }
        ui.close();
    }

}
