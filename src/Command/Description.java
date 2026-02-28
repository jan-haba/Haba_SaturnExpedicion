package Command;

import Objects.Player;

/**
 * Command for looking around the current room.
 * Retrieves and displays the full description of the player's current location,
 * including visible items, interactive objects, characters, and available exits.
 */
public class Description implements Command{
    private Player player;

    public Description(Player player) {
        this.player = player;
    }

    /**
     * Executes the description command.
     * Fetches the detailed visual representation of the current room.
     * @param command any additional arguments provided by the player (ignored for this command)
     * @return the formatted description of the current room
     */
    @Override
    public String execute(String command) {
        String roomDescription = player.getCurrRoom().getRoom();
        return roomDescription;
    }

    /**
     * Determines if executing this command should exit the game.
     * @return false, as looking around does not terminate the main game loop
     */
    @Override
    public boolean exit() {
        return false;
    }
}