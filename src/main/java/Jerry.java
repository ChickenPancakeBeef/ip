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
            String fullInput = in.nextLine().trim();
            if (fullInput.isEmpty()) continue;

            String commandWord = fullInput.split(" ")[0];

            if (commandWord.equals(COMMAND_BYE)) {
                break;
            }

            // Level 5: Handle errors gracefully using try-catch
            try {
                taskCount = handleCommand(fullInput, commandWord, tasks, taskCount);
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

    private static int handleCommand(String input, String command, Task[] tasks, int count) throws JerryException {
        switch (command) {
        case COMMAND_LIST:
            printTaskList(tasks, count);
            break;
        case COMMAND_MARK:
            updateTaskStatus(input, tasks, count, true);
            break;
        case COMMAND_UNMARK:
            updateTaskStatus(input, tasks, count, false);
            break;
        case COMMAND_TODO:
            return addTodo(input, tasks, count);
        case COMMAND_DEADLINE:
            return addDeadline(input, tasks, count);
        case COMMAND_EVENT:
            return addEvent(input, tasks, count);
        default:
            throw new JerryException("I'm sorry, but I don't know what that means :-(");
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

    private static void updateTaskStatus(String input, Task[] tasks, int count, boolean isMark) throws JerryException {
        String[] parts = input.split(" ");
        if (parts.length < 2) {
            throw new JerryException("Please specify a task number.");
        }

        int index = Integer.parseInt(parts[1]) - 1;
        if (index < 0 || index >= count) {
            throw new JerryException("That task number doesn't exist in your list!");
        }

        if (isMark) {
            tasks[index].markAsDone();
            printFeedback("Nice! I've marked this task as done:", tasks[index]);
        } else {
            tasks[index].unmarkAsDone();
            printFeedback("OK, I've marked this task as not done yet:", tasks[index]);
        }
    }

    private static int addTodo(String input, Task[] tasks, int count) throws JerryException {
        if (input.length() <= COMMAND_TODO.length()) {
            throw new JerryException("The description of a todo cannot be empty.");
        }
        String description = input.substring(COMMAND_TODO.length()).trim();
        if (description.isEmpty()) {
            throw new JerryException("The description of a todo cannot be empty.");
        }
        tasks[count] = new Todo(description);
        printAddedFeedback(tasks[count], count + 1);
        return count + 1;
    }

    private static int addDeadline(String input, Task[] tasks, int count) throws JerryException {
        if (!input.contains(" /by ")) {
            throw new JerryException("A deadline must contain ' /by ' followed by the time.");
        }
        String[] parts = input.substring(COMMAND_DEADLINE.length()).split(" /by ", 2);
        if (parts[0].trim().isEmpty()) {
            throw new JerryException("The description of a deadline cannot be empty.");
        }
        tasks[count] = new Deadline(parts[0].trim(), parts[1].trim());
        printAddedFeedback(tasks[count], count + 1);
        return count + 1;
    }

    private static int addEvent(String input, Task[] tasks, int count) throws JerryException {
        if (!input.contains(" /from ") || !input.contains(" /to ")) {
            throw new JerryException("An event must contain ' /from ' and ' /to ' delimiters.");
        }
        String[] parts = input.substring(COMMAND_EVENT.length()).split(" /from | /to ", 3);
        if (parts[0].trim().isEmpty()) {
            throw new JerryException("The description of an event cannot be empty.");
        }
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