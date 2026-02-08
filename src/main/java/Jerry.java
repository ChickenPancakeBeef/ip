import java.util.Scanner;

public class Jerry {
    // A-CodeQuality: Define constants to avoid "Magic Strings"
    private static final String LINE = "    ____________________________________________________________";
    private static final String LOGO = "     Hello! I'm Jerry\n     What can I do for you?";
    private static final String BYE_MESSAGE = "     Bye. Hope to see you again soon!";

    // Command Constants
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_MARK = "mark";
    private static final String COMMAND_UNMARK = "unmark";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";

    private static final int MAX_TASKS = 100;

    public static void main(String[] args) {
        Task[] tasks = new Task[MAX_TASKS];
        int taskCount = 0;
        Scanner in = new Scanner(System.in);

        printHorizontalLine();
        System.out.println(LOGO);
        printHorizontalLine();

        while (true) {
            String fullInput = in.nextLine();
            String commandWord = fullInput.split(" ")[0];

            if (commandWord.equals(COMMAND_BYE)) {
                break;
            }

            // A-CodeQuality: Logic delegated to a handler method
            taskCount = handleCommand(fullInput, commandWord, tasks, taskCount);
        }

        printHorizontalLine();
        System.out.println(BYE_MESSAGE);
        printHorizontalLine();
    }

    private static int handleCommand(String input, String command, Task[] tasks, int count) {
        switch (command) {
        case COMMAND_LIST:
            printTaskList(tasks, count);
            break;
        case COMMAND_MARK:
            updateTaskStatus(input, tasks, true);
            break;
        case COMMAND_UNMARK:
            updateTaskStatus(input, tasks, false);
            break;
        case COMMAND_TODO:
            return addTodo(input, tasks, count);
        case COMMAND_DEADLINE:
            return addDeadline(input, tasks, count);
        case COMMAND_EVENT:
            return addEvent(input, tasks, count);
        default:
            // For now, treat unknown commands as generic tasks or ignore
            System.out.println("     I'm sorry, I don't know what that means.");
            break;
        }
        return count;
    }

    private static void printTaskList(Task[] tasks, int count) {
        printHorizontalLine();
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < count; i++) {
            System.out.println("     " + (i + 1) + "." + tasks[i]);
        }
        printHorizontalLine();
    }

    private static void updateTaskStatus(String input, Task[] tasks, boolean isMark) {
        int index = Integer.parseInt(input.split(" ")[1]) - 1;
        if (isMark) {
            tasks[index].markAsDone();
            printFeedback("Nice! I've marked this task as done:", tasks[index]);
        } else {
            tasks[index].unmarkAsDone();
            printFeedback("OK, I've marked this task as not done yet:", tasks[index]);
        }
    }

    private static int addTodo(String input, Task[] tasks, int count) {
        String description = input.substring(5).trim();
        tasks[count] = new Todo(description);
        printAddedFeedback(tasks[count], count + 1);
        return count + 1;
    }

    private static int addDeadline(String input, Task[] tasks, int count) {
        String[] parts = input.substring(9).split(" /by ");
        tasks[count] = new Deadline(parts[0].trim(), parts[1].trim());
        printAddedFeedback(tasks[count], count + 1);
        return count + 1;
    }

    private static int addEvent(String input, Task[] tasks, int count) {
        String[] parts = input.substring(6).split(" /from | /to ");
        tasks[count] = new Event(parts[0].trim(), parts[1].trim(), parts[2].trim());
        printAddedFeedback(tasks[count], count + 1);
        return count + 1;
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
//check ide