public class AddTodoCommand extends Command {
    private final String desc;
    public AddTodoCommand(String desc) { this.desc = desc; }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JerryException {
        if (desc.isEmpty()) throw new JerryException("The description of a todo cannot be empty.");
        Task t = new Todo(desc);
        tasks.addTask(t);
        ui.showMessage("Got it. I've added this task:\n       " + t);
        ui.showMessage("Now you have " + tasks.getSize() + " tasks in the list.");
    }
}