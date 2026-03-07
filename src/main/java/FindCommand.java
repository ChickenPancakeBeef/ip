import java.util.ArrayList;

/**
 * Represents a command to search for tasks in the task list that contain a specific keyword.
 * This command performs a case-insensitive search through the task descriptions.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Initializes a FindCommand with the specified search keyword.
     * The keyword is converted to lowercase to ensure case-insensitive matching.
     * * @param keyword The string to search for within the task list.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword.toLowerCase();
    }

    /**
     * Executes the search logic by iterating through the task list.
     * Displays all tasks that contain the search keyword in their string representation.
     * * @param tasks   The list of tasks to be searched.
     * @param ui      The user interface for displaying the search results or errors.
     * @param storage The storage handler (unused in this specific command).
     * @throws JerryException If the search keyword provided is empty.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JerryException {
        if (keyword.isEmpty()) {
            throw new JerryException("The search keyword cannot be empty.");
        }

        ui.showMessage("Here are the matching tasks in your list:");
        int count = 0;

        for (int i = 0; i < tasks.getSize(); i++) {
            Task task = tasks.getTask(i);
            // Check if the task description contains the keyword (case-insensitive)
            if (task.toString().toLowerCase().contains(keyword)) {
                count++;
                ui.showMessage(count + "." + task);
            }
        }

        if (count == 0) {
            ui.showMessage("No matching tasks found for: " + keyword);
        }
    }
}