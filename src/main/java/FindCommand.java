import java.util.ArrayList;

public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword.toLowerCase();
    }

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