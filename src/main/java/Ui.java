import java.util.Scanner;

public class Ui {
    private static final String LINE = "    ____________________________________________________________";
    private static final String LOGO = "     Hello! I'm Jerry\n     What can I do for you?";
    private static final String BYE_MESSAGE = "     Bye. Hope to see you again soon!";
    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showWelcome() {
        showLine();
        System.out.println(LOGO);
        showLine();
    }

    public void showLine() {
        System.out.println(LINE);
    }

    public void showError(String message) {
        System.out.println("     ☹ OOPS!!! " + message);
    }

    public void showMessage(String message) {
        System.out.println("     " + message);
    }

    public void showBye() {
        showLine();
        System.out.println(BYE_MESSAGE);
        showLine();
    }
}