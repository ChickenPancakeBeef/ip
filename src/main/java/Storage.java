import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles the loading and saving of tasks to a local text file.
 * This class ensures that task data persists between different sessions of the chatbot.
 */
public class Storage {
    private final Path filePath;

    /**
     * Initializes a Storage object with the specified file path.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Storage(Path filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the list of tasks from the data file.
     * If the file does not exist, an empty list is returned.
     *
     * @return An ArrayList of Task objects loaded from the file.
     * @throws JerryException If there is an error reading the file or the format is invalid.
     */
    public ArrayList<Task> load() throws JerryException {
        ArrayList<Task> tasks = new ArrayList<>();
        File f = filePath.toFile();
        if (!f.exists()) {
            return tasks;
        }

        try (Scanner s = new Scanner(f)) {
            while (s.hasNext()) {
                String line = s.nextLine();
                Task task = parseFileLine(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            throw new JerryException("Could not load tasks from file.");
        }
        return tasks;
    }

    /**
     * Parses a single line from the save file and converts it into a Task object.
     *
     * @param line A pipe-separated string representing a task.
     * @return The corresponding Task object, or null if the line is invalid.
     */
    private Task parseFileLine(String line) {
        String[] p = line.split(" \\| ");
        if (p.length < 3) {
            return null;
        }

        Task task = null;
        switch (p[0]) {
            case "T":
                task = new Todo(p[2]);
                break;
            case "D":
                try {
                    task = new Deadline(p[2], p[3]);
                } catch (JerryException e) {
                    return null;
                }
                break;
            case "E":
                task = new Event(p[2], p[3], p[4]);
                break;
            default:
                return null;
        }

        if (task != null && p[1].equals("1")) {
            task.markAsDone();
        }
        return task;
    }

    /**
     * Saves the current list of tasks to the local text file.
     * Creates the parent directory if it does not already exist.
     *
     * @param tasks The TaskList containing the tasks to be saved.
     * @throws JerryException If an error occurs during the file writing process.
     */
    public void save(TaskList tasks) throws JerryException {
        try {
            File file = filePath.toFile();
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }

            FileWriter fw = new FileWriter(file);
            for (int i = 0; i < tasks.getSize(); i++) {
                fw.write(tasks.getTask(i).toFileFormat() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            throw new JerryException("Error while saving to file.");
        }
    }
}