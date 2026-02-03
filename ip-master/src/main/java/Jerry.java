import java.util.Scanner;

public class Jerry {
    private static final String HORIZONTAL_LINE = "    ____________________________________________________________";

    public static void main(String[] args) {
        String botName = "Jerry";
        Task[] tasks = new Task[100]; // Fixed size array as per Level-2 instructions
        int taskCount = 0;

        printGreeting(botName);

        Scanner in = new Scanner(System.in);

        while (true) {
            String input = in.nextLine();

            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                printList(tasks, taskCount);
            } else if (input.startsWith("mark ")) {
                handleMarkUnmark(input, tasks, true);
            } else if (input.startsWith("unmark ")) {
                handleMarkUnmark(input, tasks, false);
            } else if (input.startsWith("todo ")) {
                tasks[taskCount] = new Todo(input.substring(5));
                taskCount++;
                printAddedFeedback(tasks[taskCount - 1], taskCount);
            } else if (input.startsWith("deadline ")) {
                // Splits input into description and the '/by' timing
                String[] parts = input.substring(9).split(" /by ");
                tasks[taskCount] = new Deadline(parts[0], parts[1]);
                taskCount++;
                printAddedFeedback(tasks[taskCount - 1], taskCount);
            } else if (input.startsWith("event ")) {
                // Splits input into description, '/from', and '/to' timings
                String[] parts = input.substring(6).split(" /from | /to ");
                tasks[taskCount] = new Event(parts[0], parts[1], parts[2]);
                taskCount++;
                printAddedFeedback(tasks[taskCount - 1], taskCount);
            }
        }

        printGoodbye();
    }

    private static void printGreeting(String name) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("     Hello! I'm " + name);
        System.out.println("     What can I do for you?");
        System.out.println(HORIZONTAL_LINE);
    }

    private static void printGoodbye() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println(HORIZONTAL_LINE);
    }

    private static void printList(Task[] tasks, int count) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < count; i++) {
            System.out.println("     " + (i + 1) + "." + tasks[i]);
        }
        System.out.println(HORIZONTAL_LINE);
    }

    private static void handleMarkUnmark(String input, Task[] tasks, boolean isMark) {
        int index = Integer.parseInt(input.split(" ")[1]) - 1;
        if (isMark) {
            tasks[index].markAsDone();
            System.out.println(HORIZONTAL_LINE);
            System.out.println("     Nice! I've marked this task as done:");
        } else {
            tasks[index].unmarkAsDone();
            System.out.println(HORIZONTAL_LINE);
            System.out.println("     OK, I've marked this task as not done yet:");
        }
        System.out.println("       " + tasks[index]);
        System.out.println(HORIZONTAL_LINE);
    }

    private static void printAddedFeedback(Task task, int count) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + task);
        System.out.println("     Now you have " + count + " tasks in the list.");
        System.out.println(HORIZONTAL_LINE);
    }
}