import java.nio.file.Paths;

public class Jerry {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    public Jerry(String filePath) {
        ui = new Ui();
        storage = new Storage(Paths.get(filePath));
        TaskList loadedTasks;
        try {
            loadedTasks = new TaskList(storage.load());
        } catch (JerryException e) {
            ui.showError("Loading error. Starting with empty list.");
            loadedTasks = new TaskList();
        }
        this.tasks = loadedTasks;
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullInput = ui.readCommand();
                ui.showLine();

                Command c = Parser.parse(fullInput);
                c.execute(tasks, ui, storage);

                isExit = c.isExit();
                storage.save(tasks);
            } catch (JerryException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
        ui.showBye();
    }
    public static void main(String[] args) {
        new Jerry("data/jerry.txt").run();
    }
}