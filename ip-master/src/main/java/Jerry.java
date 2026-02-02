import java.util.Scanner;

public class Jerry {
    public static void main(String[] args) {
        String botName = "Jerry";
        String horizontalLine = "    ____________________________________________________________";

        // Storage for tasks
        String[] tasks = new String[100];
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
                // Print the list
                System.out.println(horizontalLine);
                for (int i = 0; i < taskCount; i++) {
                    System.out.println("     " + (i + 1) + ". " + tasks[i]);
                }
                System.out.println(horizontalLine);
            } else {
                // Add to list
                tasks[taskCount] = input;
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