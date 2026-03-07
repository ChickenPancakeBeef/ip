import java.util.Scanner;

/**
 * Handles all interactions with the user via the command line.
 * Responsible for displaying messages, errors, and reading user input.
 */
public class Ui {
    private static final String LINE = "    ____________________________________________________________";
    private static final String LOGO = "     Hello! I'm Jerry\n     What can I do for you?";
    private static final String BYE_MESSAGE = "     Bye. Hope to see you again soon!";
    private final Scanner scanner;

    /**
     * Initializes a new Ui object with a Scanner to read system input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads the next line of input from the user.
     *
     * @return The raw input string entered by the user.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays the welcome message and logo to the user.
     */
    public void showWelcome() {
        showLine();
        System.out.println(LOGO);
        showLine();
    }

    /**
     * Prints a standardized divider line for visual separation in the console.
     */
    public void showLine() {
        System.out.println(LINE);
    }

    /**
     * Displays an error message formatted with a standardized error prefix.
     *
     * @param message The specific error details to be displayed.
     */
    public void showError(String message) {
        System.out.println("     ☹ OOPS!!! " + message);
    }

    /**
     * Displays a general message to the user with standard indentation.
     *
     * @param message The text to be printed to the console.
     */
    public void showMessage(String message) {
        System.out.println("     " + message);
    }

    /**
     * Displays the goodbye message to the user.
     */
    public void showBye() {
        showLine();
        System.out.println(BYE_MESSAGE);
        showLine();
    }
}