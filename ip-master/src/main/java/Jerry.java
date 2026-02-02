import java.util.Scanner;

public class Jerry {
    public static void main(String[] args) {
        String botName = "Jerry";
        String horizontalLine = "    ____________________________________________________________";

        // Use an array of Task objects instead of Strings
        Task[] tasks = new Task[100];
        int taskCount = 0;

        System.out.println(horizontalLine);
        System.out.println("     Hello! I'm " + botName);
        System.out.println("     What can I do for you?");
        System.out.println(horizontalLine);

        Scanner in = new Scanner(System.in);

        while (true) {
            String input = in.nextLine();

            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                System.out.println(horizontalLine);
                System.out.println("     Here are the tasks in your list:");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println("     " + (i + 1) + "." + tasks[i].toString());
                }
                System.out.println(horizontalLine);
            } else if (input.startsWith("mark ")) {
                // Get the number after "mark ", convert to int, and subtract 1 for array index
                int index = Integer.parseInt(input.substring(5)) - 1;
                tasks[index].markAsDone();

                System.out.println(horizontalLine);
                System.out.println("     Nice! I've marked this task as done:");
                System.out.println("       " + tasks[index]);
                System.out.println(horizontalLine);
            } else if (input.startsWith("unmark ")) {
                int index = Integer.parseInt(input.substring(7)) - 1;
                tasks[index].unmarkAsDone();

                System.out.println(horizontalLine);
                System.out.println("     OK, I've marked this task as not done yet:");
                System.out.println("       " + tasks[index]);
                System.out.println(horizontalLine);
            } else {
                // Default action: Add a new task
                tasks[taskCount] = new Task(input);
                taskCount++;

                System.out.println(horizontalLine);
                System.out.println("     added: " + input);
                System.out.println(horizontalLine);
            }
        }

        System.out.println(horizontalLine);
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println(horizontalLine);
    }
}