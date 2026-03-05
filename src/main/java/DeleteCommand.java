public class DeleteCommand extends Command {
    private final int index;
    public DeleteCommand(int index) { this.index = index; }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JerryException {
        Task removed = tasks.deleteTask(index);
        ui.showMessage("Noted. I've removed this task:\n       " + removed);
        ui.showMessage("Now you have " + tasks.getSize() + " tasks in the list.");
    }
}