/**
 * Represents a command to add a deadline task to the task list.
 * This command parses user input to extract the task description and the deadline date.
 */
public class AddDeadlineCommand extends Command {
    private final String arguments;

    /**
     * Initializes an AddDeadlineCommand with the raw arguments provided by the user.
     *
     * @param arguments The string containing the description and the deadline date (e.g., "return book /by 2026-12-01").
     */
    public AddDeadlineCommand(String arguments) {
        this.arguments = arguments;
    }

    /**
     * Executes the logic to parse arguments, create a new Deadline, and add it to the task list.
     * Validates that the input contains the required "/by" separator and a non-empty description.
     *
     * @param tasks   The list of tasks where the new deadline will be added.
     * @param ui      The user interface for displaying confirmation or error messages.
     * @param storage The storage handler to save the updated task list to the local file.
     * @throws JerryException If the arguments are malformed or if the date format is invalid.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JerryException {
        if (!arguments.contains(" /by ")) {
            throw new JerryException("A deadline must contain ' /by '.");
        }

        String[] parts = arguments.split(" /by ", 2);
        if (parts[0].trim().isEmpty()) {
            throw new JerryException("The description of a deadline cannot be empty.");
        }

        // The Deadline constructor now handles the date parsing logic
        Task d = new Deadline(parts[0].trim(), parts[1].trim());
        tasks.addTask(d);

        ui.showMessage("Got it. I've added this task:\n       " + d);
        ui.showMessage("Now you have " + tasks.getSize() + " tasks in the list.");
    }
}