public class MarkCommand extends Command {
    private final int index;
    private final boolean isMark;

    public MarkCommand(int index, boolean isMark) {
        this.index = index;
        this.isMark = isMark;
    }

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