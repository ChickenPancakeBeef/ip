/**
 * Represents a command to mark or unmark a task as completed.
 * This command updates the status of a specific task in the list and notifies the user.
 */
public class MarkCommand extends Command {
    private final int index;
    private final boolean isMark;

    /**
     * Initializes a MarkCommand with the specified task index and action type.
     *
     * @param index  The 0-indexed position of the task in the task list.
     * @param isMark True to mark the task as done, false to unmark it.
     */
    public MarkCommand(int index, boolean isMark) {
        this.index = index;
        this.isMark = isMark;
    }

    /**
     * Executes the mark or unmark logic on the specified task.
     * Validates that the index is within the bounds of the current task list.
     *
     * @param tasks   The list of tasks containing the target task.
     * @param ui      The user interface for displaying the status update confirmation.
     * @param storage The storage handler (unused in this specific command).
     * @throws JerryException If the provided task index is invalid or out of bounds.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JerryException {
        if (index < 0 || index >= tasks.getSize()) {
            throw new JerryException("That task number doesn't exist!");
        }

        Task t = tasks.getTask(index);
        if (isMark) {
            t.markAsDone();
            ui.showMessage("Nice! I've marked this task as done:\n       " + t);
        } else {
            t.unmarkAsDone();
            ui.showMessage("OK, I've marked this task as not done yet:\n       " + t);
        }
    }
}