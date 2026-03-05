public class AddEventCommand extends Command {
    private final String arguments;

    public AddEventCommand(String arguments) {
        this.arguments = arguments;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JerryException {
        if (!arguments.contains(" /from ") || !arguments.contains(" /to ")) {
            throw new JerryException("An event must contain ' /from ' and ' /to '.");
        }
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