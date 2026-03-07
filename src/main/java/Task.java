/**
 * Represents a generic task with a description and completion status.
 * This class serves as a base for specific task types like Todo, Deadline, and Event.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Initializes a new Task with the specified description.
     * The task is initially marked as not done.
     * * @param description The name or description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the completion status of the task as an icon.
     * * @return "X" if the task is done, otherwise a space " ".
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not completed.
     */
    public void unmarkAsDone() {
        this.isDone = false;
    }

    /**
     * Returns a string formatted for saving the task to a local data file.
     * The format used is "status | description".
     * * @return A pipe-separated string representing the task's save state.
     */
    public String toFileFormat() {
        return (isDone ? "1" : "0") + " | " + description;
    }

    /**
     * Returns a string representation of the task for display in the UI.
     * * @return A string containing the status icon and the task description.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}