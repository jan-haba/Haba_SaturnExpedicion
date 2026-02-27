package Command;

/**
 * Command for ending the game.
 */
public class End implements Command{

    /**
     * Executes the end command.
     * @param command any additional arguments (ignored for this command)
     * @return a message notifying the player about exiting the game
     */
    @Override
    public String execute(String command) {
        return "Exiting game";
    }

    /**
     * Signals the console loop to terminate.
     * @return true to indicate the game should exit
     */
    @Override
    public boolean exit() {
        return true;
    }
}
