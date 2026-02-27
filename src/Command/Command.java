package Command;

/**
 * Interface for Commands.
 */
public interface Command {
     String execute(String command);
     boolean exit();
}
