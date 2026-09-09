package ramly.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import ramly.model.Deadline;
import ramly.model.Event;
import ramly.model.Task;
import ramly.model.TaskList;
import ramly.model.Todo;

/** Loads tasks from and saves tasks to a persistent file. */
public class Storage {
    private final String filePath;

    /** Creates storage backed by the specified file path. */
    public Storage(String filePath) {
        assert filePath != null : "Storage file path must not be null";
        assert !filePath.isBlank() : "Storage file path must not be blank";
        this.filePath = filePath;
    }

    /** Creates the storage directory and file when they do not exist. */
    private void ensureFileExists(File file) {
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Error creating file: " + e.getMessage());
        }
    }

    /** Loads all valid tasks currently stored in the file. */
    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        ensureFileExists(file);

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) {
                    continue;
                }

                Task task = parseTask(line);
                if (task == null) {
                    continue;
                }
                tasks.add(task);
            }
        } catch (IOException e) {
            System.out.println("Error reading storage file: " + e.getMessage());
        }

        return tasks;
    }

    /** Converts one serialized storage record into a task, or null for an unknown task type. */
    private Task parseTask(String line) {
        String[] parts = line.split(" \\| ");
        boolean isDone = parts[1].equals("1");
        Task task = createTask(parts);

        if (task != null && isDone) {
            task.mark();
        }
        return task;
    }

    /** Creates the task subtype identified by a serialized record. */
    private Task createTask(String[] parts) {
        String type = parts[0];
        String description = parts[2];
        switch (type) {
        case "T":
            return new Todo(description);
        case "D":
            return new Deadline(description, parts[3]);
        case "E":
            return new Event(description, parts[3], parts[4]);
        default:
            return null;
        }
    }

    /** Saves the current task list to the backing file. */
    public void save(TaskList tasks) {
        assert tasks != null : "Saved task list must not be null";
        File file = new File(filePath);
        ensureFileExists(file);

        try (FileWriter fw = new FileWriter(file)) {
            for (Task task : tasks) {
                fw.write(task.toFileString() + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }
}
