/**
 * Represents a simple task without any specific date or time constraints.
 */
public class Todo extends Task {

    /**
     * Initializes a new Todo task with the specified description.
     *
     * @param description The name of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string formatted for saving the todo task to a file.
     * The format used is "T | status | description".
     *
     * @return A pipe-separated string representing the todo task.
     */
    @Override
    public String toFileFormat() {
        return "T | " + super.toFileFormat();
    }

    /**
     * Returns a string representation of the todo task for display.
     * Includes the [T] prefix to identify the task type.
     *
     * @return A string containing the task type, status, and description.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}