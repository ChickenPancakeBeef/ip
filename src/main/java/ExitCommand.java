/**
 * Represents a command to terminate the chatbot application.
 * This command signals the main execution loop to stop.
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command.
     * In the case of an exit, no specific task or storage modifications are required.
     *
     * @param tasks   The list of tasks (unused in this command).
     * @param ui      The user interface (unused in this command).
     * @param storage The storage handler (unused in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        // No execution logic needed for exit as the loop termination is handled by isExit()
    }

    /**
     * Indicates that this command should terminate the application.
     *
     * @return true, indicating the program should exit.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}