import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Jerry {
    private static final String LINE = "    ____________________________________________________________";
    private static final String LOGO = "     Hello! I'm Jerry\n     What can I do for you?";
    private static final String BYE_MESSAGE = "     Bye. Hope to see you again soon!";

    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_MARK = "mark";
    private static final String COMMAND_UNMARK = "unmark";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_DELETE = "delete";

    // Path setup: data/jerry.txt
    private static final Path FILE_PATH = Paths.get("data", "jerry.txt");

    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();

        // HOOK: Load tasks from hard disk at startup
        loadTasks(tasks);

        Scanner in = new Scanner(System.in);

        printHorizontalLine();
        System.out.println(LOGO);
        printHorizontalLine();

        while (true) {
            String fullInput = in.nextLine().trim();
            if (fullInput.isEmpty()) continue;

            String commandWord = fullInput.split(" ")[0];

            if (commandWord.equals(COMMAND_BYE)) {
                break;
            }

            try {
                handleCommand(fullInput, commandWord, tasks);
            } catch (JerryException e) {
                printHorizontalLine();
                System.out.println("     ☹ OOPS!!! " + e.getMessage());
                printHorizontalLine();
            } catch (NumberFormatException e) {
                printHorizontalLine();
                System.out.println("     ☹ OOPS!!! Please provide a valid task number.");
                printHorizontalLine();
            } catch (IndexOutOfBoundsException e) {
                printHorizontalLine();
                System.out.println("     ☹ OOPS!!! The format for that command is incomplete.");
                printHorizontalLine();
            }
        }

        printHorizontalLine();
        System.out.println(BYE_MESSAGE);
        printHorizontalLine();
    }

    private static void handleCommand(String input, String command, ArrayList<Task> tasks) throws JerryException {
        switch (command) {
            case COMMAND_LIST:
                printTaskList(tasks);
                break;
            case COMMAND_MARK:
                updateTaskStatus(input, tasks, true);
                saveTasks(tasks); // HOOK: Save after change
                break;
            case COMMAND_UNMARK:
                updateTaskStatus(input, tasks, false);
                saveTasks(tasks); // HOOK: Save after change
                break;
            case COMMAND_TODO:
                addTodo(input, tasks);
                saveTasks(tasks); // HOOK: Save after change
                break;
            case COMMAND_DEADLINE:
                addDeadline(input, tasks);
                saveTasks(tasks); // HOOK: Save after change
                break;
            case COMMAND_EVENT:
                addEvent(input, tasks);
                saveTasks(tasks); // HOOK: Save after change
                break;
            case COMMAND_DELETE:
                deleteTask(input, tasks);
                saveTasks(tasks); // HOOK: Save after change
                break;
            default:
                throw new JerryException("I'm sorry, but I don't know what that means :-(");
        }
    }

    // --- LEVEL 7: STORAGE METHODS ---

    private static void saveTasks(ArrayList<Task> tasks) {
        try {
            File file = FILE_PATH.toFile();
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs(); // Create 'data' folder if missing
            }

            FileWriter fw = new FileWriter(file);
            for (Task t : tasks) {
                fw.write(t.toFileFormat() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("     ☹ OOPS!!! Error saving tasks: " + e.getMessage());
        }
    }

    private static void loadTasks(ArrayList<Task> tasks) {
        File f = FILE_PATH.toFile();
        if (!f.exists()) {
            return; // First-time run: no file to load
        }

        try (Scanner s = new Scanner(f)) {
            while (s.hasNext()) {
                String line = s.nextLine();
                String[] p = line.split(" \\| ");
                Task task = null;

                // Stretch Goal: Basic corruption check
                if (p.length < 3) continue;

                switch (p[0]) {
                    case "T":
                        task = new Todo(p[2]);
                        break;
                    case "D":
                        task = new Deadline(p[2], p[3]);
                        break;
                    case "E":
                        task = new Event(p[2], p[3], p[4]);
                        break;
                }

                if (task != null) {
                    if (p[1].equals("1")) {
                        task.markAsDone();
                    }
                    tasks.add(task);
                }
            }
        } catch (Exception e) {
            System.out.println("     ☹ OOPS!!! Error loading file. Starting fresh.");
        }
    }

    // --- EXISTING HELPER METHODS ---

    private static void printTaskList(ArrayList<Task> tasks) {
        printHorizontalLine();
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("     " + (i + 1) + "." + tasks.get(i));
        }
        printHorizontalLine();
    }

    private static void updateTaskStatus(String input, ArrayList<Task> tasks, boolean isMark) throws JerryException {
        String[] parts = input.split(" ");
        if (parts.length < 2) {
            throw new JerryException("Please specify a task number.");
        }

        int index = Integer.parseInt(parts[1]) - 1;
        if (index < 0 || index >= tasks.size()) {
            throw new JerryException("That task number doesn't exist in your list!");
        }

        if (isMark) {
            tasks.get(index).markAsDone();
            printFeedback("Nice! I've marked this task as done:", tasks.get(index));
        } else {
            tasks.get(index).unmarkAsDone();
            printFeedback("OK, I've marked this task as not done yet:", tasks.get(index));
        }
    }

    private static void addTodo(String input, ArrayList<Task> tasks) throws JerryException {
        String description = input.substring(COMMAND_TODO.length()).trim();
        if (description.isEmpty()) {
            throw new JerryException("The description of a todo cannot be empty.");
        }
        Task newTask = new Todo(description);
        tasks.add(newTask);
        printAddedFeedback(newTask, tasks.size());
    }

    private static void addDeadline(String input, ArrayList<Task> tasks) throws JerryException {
        if (!input.contains(" /by ")) {
            throw new JerryException("A deadline must contain ' /by ' followed by the time.");
        }
        String[] parts = input.substring(COMMAND_DEADLINE.length()).split(" /by ", 2);
        if (parts[0].trim().isEmpty()) {
            throw new JerryException("The description of a deadline cannot be empty.");
        }
        Task newTask = new Deadline(parts[0].trim(), parts[1].trim());
        tasks.add(newTask);
        printAddedFeedback(newTask, tasks.size());
    }

    private static void addEvent(String input, ArrayList<Task> tasks) throws JerryException {
        if (!input.contains(" /from ") || !input.contains(" /to ")) {
            throw new JerryException("An event must contain ' /from ' and ' /to ' delimiters.");
        }
        String[] parts = input.substring(COMMAND_EVENT.length()).split(" /from | /to ", 3);
        if (parts[0].trim().isEmpty()) {
            throw new JerryException("The description of an event cannot be empty.");
        }
        Task newTask = new Event(parts[0].trim(), parts[1].trim(), parts[2].trim());
        tasks.add(newTask);
        printAddedFeedback(newTask, tasks.size());
    }

    private static void deleteTask(String input, ArrayList<Task> tasks) throws JerryException {
        String[] parts = input.split(" ");
        if (parts.length < 2) {
            throw new JerryException("Please specify which task number to delete.");
        }
        int index = Integer.parseInt(parts[1]) - 1;
        if (index < 0 || index >= tasks.size()) {
            throw new JerryException("I can't delete that; task " + (index + 1) + " doesn't exist!");
        }

        Task removedTask = tasks.remove(index);
        printHorizontalLine();
        System.out.println("     Noted. I've removed this task:");
        System.out.println("       " + removedTask);
        System.out.println("     Now you have " + tasks.size() + " tasks in the list.");
        printHorizontalLine();
    }

    private static void printFeedback(String message, Task task) {
        printHorizontalLine();
        System.out.println("     " + message);
        System.out.println("       " + task);
        printHorizontalLine();
    }

    private static void printAddedFeedback(Task task, int total) {
        printHorizontalLine();
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + task);
        System.out.println("     Now you have " + total + " tasks in the list.");
        printHorizontalLine();
    }

    private static void printHorizontalLine() {
        System.out.println(LINE);
    }
}