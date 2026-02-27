package Command;

/**
 * command for ending the game
 */

public class End implements Command{

    /**
     * execute method
     * @param command
     * @return
     */
    @Override
    public String execute(String command) {
        return "Exiting game";
    }

    /**
     * we set exit true so that we can exit the game
     * @return
     */
    @Override
    public boolean exit() {
        return true;
    }
}
