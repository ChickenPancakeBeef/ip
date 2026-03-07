/**
 * Represents a command to add an event task to the task list.
 * This command parses user input to extract the task description, start time, and end time.
 */
public class AddEventCommand extends Command {
    private final String arguments;

    /**
     * Initializes an AddEventCommand with the raw arguments provided by the user.
     * * @param arguments The string containing the description, start time, and end time
     * (e.g., "project meeting /from Mon 2pm /to 4pm").
     */
    public AddEventCommand(String arguments) {
        this.arguments = arguments;
    }

    /**
     * Executes the logic to parse arguments, create a new Event, and add it to the task list.
     * Validates that the input contains both "/from" and "/to" separators and a description.
     * * @param tasks   The list of tasks where the new event will be added.
     * @param ui      The user interface for displaying confirmation or error messages.
     * @param storage The storage handler to save the updated task list to the local file.
     * @throws JerryException If the arguments are malformed or missing required parts.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JerryException {
        if (!arguments.contains(" /from ") || !arguments.contains(" /to ")) {
            throw new JerryException("An event must contain ' /from ' and ' /to '.");
        }

        // Split by either separator to get description, start, and end
        String[] parts = arguments.split(" /from | /to ", 3);
        if (parts[0].trim().isEmpty()) {
            throw new JerryException("The description of an event cannot be empty.");
        }

        Task e = new Event(parts[0].trim(), parts[1].trim(), parts[2].trim());
        tasks.addTask(e);

        ui.showMessage("Got it. I've added this task:\n       " + e);
        ui.showMessage("Now you have " + tasks.getSize() + " tasks in the list.");
    }
}