/**
 * Represents an exception specific to the Jerry chatbot application.
 * This class is used to signal errors related to user input, file I/O,
 * or task management within the application.
 */
public class JerryException extends Exception {

    /**
     * Initializes a new JerryException with a specific error message.
     *
     * @param message The detailed error message describing the cause of the exception.
     */
    public JerryException(String message) {
        super(message);
    }
}