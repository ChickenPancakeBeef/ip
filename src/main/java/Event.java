/**
 * Represents a task that occurs during a specific time period.
 * Includes a start time and an end time.
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Initializes a new Event task with a description and a time range.
     *
     * @param description The name of the event.
     * @param from The start time/date of the event.
     * @param to The end time/date of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string formatted for saving the event task to a file.
     * The format used is "E | status | description | from | to".
     *
     * @return A pipe-separated string representing the event task.
     */
    @Override
    public String toFileFormat() {
        return "E | " + super.toFileFormat() + " | " + from + " | " + to;
    }

    /**
     * Returns a string representation of the event task for display.
     * Includes the [E] prefix and the time range.
     *
     * @return A string containing the task type, status, description, and time range.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}