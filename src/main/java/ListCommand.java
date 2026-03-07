/**
 * Represents a command to display all tasks currently in the task list.
 * This command iterates through the list and prints each task with its index.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command by displaying all tasks to the user.
     * Iterates through the provided task list and uses the UI to display each task.
     *
     * @param tasks   The list of tasks to be displayed.
     * @param ui      The user interface for displaying the task list.
     * @param storage The storage handler (unused in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("Here are the tasks in your list:");
        for (int i = 0; i < tasks.getSize(); i++) {
            ui.showMessage((i + 1) + "." + tasks.getTask(i));
        }
    }
}