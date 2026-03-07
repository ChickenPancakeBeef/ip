/**
 * Represents a command to add a 'Todo' task to the task list.
 * This is the simplest form of task, requiring only a description.
 */
public class AddTodoCommand extends Command {
    private final String desc;

    /**
     * Initializes an AddTodoCommand with the specified description.
     * * @param desc The description of the todo task.
     */
    public AddTodoCommand(String desc) {
        this.desc = desc;
    }

    /**
     * Executes the logic to create a new Todo and add it to the task list.
     * Validates that the description provided is not empty.
     * * @param tasks   The list of tasks where the new todo will be added.
     * @param ui      The user interface for displaying confirmation or error messages.
     * @param storage The storage handler to save the updated task list to the local file.
     * @throws JerryException If the description is empty.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JerryException {
        if (desc.isEmpty()) {
            throw new JerryException("The description of a todo cannot be empty.");
        }

        Task t = new Todo(desc);
        tasks.addTask(t);

        ui.showMessage("Got it. I've added this task:\n       " + t);
        ui.showMessage("Now you have " + tasks.getSize() + " tasks in the list.");
    }
}