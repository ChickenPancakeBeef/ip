import java.util.Scanner; // Import the Scanner class to read input

public class Jerry {
    public static void main(String[] args) {
        String botName = "Jerry";
        String horizontalLine = "    ____________________________________________________________";

        // Greet the user
        System.out.println(horizontalLine);
        System.out.println("     Hello! I'm " + botName);
        System.out.println("     What can I do for you?");
        System.out.println(horizontalLine);

        // Initialize Scanner to read from the terminal
        Scanner in = new Scanner(System.in);
        String line;

        // The loop continues until the user types "bye"
        while (true) {
            line = in.nextLine(); // Read user input

            if (line.equals("bye")) {
                break; // Exit the loop
            }

            // Echo the command inside horizontal lines
            System.out.println(horizontalLine);
            System.out.println("     " + line);
            System.out.println(horizontalLine);
        }

        // Final exit message
        System.out.println(horizontalLine);
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println(horizontalLine);
    }
}