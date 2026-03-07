/**
 * Interprets user input and converts it into executable Command objects.
 */
public class Parser {
    /**
     * Parses a raw input string into a specific Command object.
     * @param fullCommand The full line of text entered by the user.
     * @return A Command object ready for execution.
     * @throws JerryException If the command format is invalid.
     */
    public static Command parse(String fullCommand) throws JerryException {
        String[] parts = fullCommand.trim().split(" ", 2);
        String commandWord = parts[0].toLowerCase();
        String arguments = parts.length > 1 ? parts[1].trim() : "";

        switch (commandWord) {
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            case "todo":
                return new AddTodoCommand(arguments);
            case "deadline":
                return new AddDeadlineCommand(arguments);
            case "event":
                return new AddEventCommand(arguments);
            case "mark":
                return new MarkCommand(parseIndex(arguments), true);
            case "unmark":
                return new MarkCommand(parseIndex(arguments), false);
            case "delete":
                return new DeleteCommand(parseIndex(arguments));
            case "find":
                if (arguments.isEmpty()) {
                    throw new JerryException("The search keyword cannot be empty.");
                }
                return new FindCommand(arguments);
            default:
                throw new JerryException("I'm sorry, but I don't know what that means :-(");
        }
    }

    private static int parseIndex(String args) throws JerryException {
        try {
            if (args.isEmpty()) {
                throw new JerryException("Please specify a task number.");
            }
            return Integer.parseInt(args) - 1;
        } catch (NumberFormatException e) {
            throw new JerryException("Please provide a valid task number.");
        }
    }
}
