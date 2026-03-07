import java.util.ArrayList;

/**
 * Encapsulates the list of tasks and provides operations to modify and query it.
 * This class serves as the in-memory representation of the task data during runtime.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Initializes an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Initializes a TaskList with an existing list of tasks.
     *
     * @param tasks An ArrayList of Task objects to populate the list.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a new task to the list.
     *
     * @param task The Task object to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task from the list at the specified index.
     *
     * @param index The 0-indexed position of the task to be removed.
     * @return The Task object that was removed from the list.
     * @throws JerryException If the index is out of the valid range of the list.
     */
    public Task deleteTask(int index) throws JerryException {
        if (index < 0 || index >= tasks.size()) {
            throw new JerryException("That task number doesn't exist!");
        }
        return tasks.remove(index);
    }

    /**
     * Retrieves a task from the list at the specified index.
     *
     * @param index The 0-indexed position of the task to retrieve.
     * @return The Task object at the specified index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks currently in the list.
     *
     * @return The total count of tasks.
     */
    public int getSize() {
        return tasks.size();
    }
}