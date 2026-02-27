package Command;

import Objects.Player;

/**
 * Command for displaying the player's inventory.
 */
public class Inventory implements Command {
    private Player player;

    public Inventory(Player player) {
        this.player = player;
    }

    /**
     * Executes the inventory command.
     * @param command any additional arguments (ignored)
     * @return a confirmation message that the inventory was opened
     */
    @Override
    public String execute(String command) {
        player.openInventory();
        return "inventory opened";
    }

    /**
     * Determines if this command exits the game.
     * @return false
     */
    @Override
    public boolean exit() {
        return false;
    }
}
