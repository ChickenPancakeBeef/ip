public class AddDeadlineCommand extends Command {
    private final String arguments;

    public AddDeadlineCommand(String arguments) {
        this.arguments = arguments;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JerryException {
        if (!arguments.contains(" /by ")) {
            throw new JerryException("A deadline must contain ' /by '.");
        }
        String[] parts = arguments.split(" /by ", 2);
        if (parts[0].trim().isEmpty()) {
            throw new JerryException("The description of a deadline cannot be empty.");
        }

        Task d = new Deadline(parts[0].trim(), parts[1].trim());
        tasks.addTask(d);
        ui.showMessage("Got it. I've added this task:\n       " + d);
        ui.showMessage("Now you have " + tasks.getSize() + " tasks in the list.");
    }
}