/**
 * Represents an executable command within the chatbot system.
 */
public abstract class Command {
    /**
     * Executes the specific logic of the command.
     * @param tasks   The task list to be modified.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage handler for saving data.
     * @throws JerryException If an error occurs during execution.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws JerryException;
    public boolean isExit() { return false; }
}