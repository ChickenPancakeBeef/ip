import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final Path filePath;

    public Storage(Path filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws JerryException {
        ArrayList<Task> tasks = new ArrayList<>();
        File f = filePath.toFile();
        if (!f.exists()) return tasks;

        try (Scanner s = new Scanner(f)) {
            while (s.hasNext()) {
                String line = s.nextLine();
                Task task = parseFileLine(line);
                if (task != null) tasks.add(task);
            }
        } catch (IOException e) {
            throw new JerryException("Could not load tasks from file.");
        }
        return tasks;
    }

    private Task parseFileLine(String line) {
        String[] p = line.split(" \\| ");
        if (p.length < 3) return null;

        Task task = null;
        switch (p[0]) {
            case "T": task = new Todo(p[2]); break;
            case "D": task = new Deadline(p[2], p[3]); break;
            case "E": task = new Event(p[2], p[3], p[4]); break;
        }

        if (task != null && p[1].equals("1")) {
            task.markAsDone();
        }
        return task;
    }

    public void save(TaskList tasks) throws JerryException {
        try {
            File file = filePath.toFile();
            if (!file.getParentFile().exists()) file.getParentFile().mkdirs();

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