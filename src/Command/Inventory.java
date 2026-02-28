package Command;

import Objects.Player;

/**
 * Command for displaying the player's inventory.
 * Retrieves the items currently held by the player and formats them into a readable list.
 */
public class Inventory implements Command {
    private Player player;

    public Inventory(Player player) {
        this.player = player;
    }

    /**
     * Executes the inventory command.
     * Builds a formatted string showing the inventory header, the items, and a footer.
     * @param command any additional arguments (ignored for this command)
     * @return the formatted inventory list and a confirmation message
     */
    @Override
    public String execute(String command) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(inventory());
        stringBuilder.append("\n").append(player.openInventory());
        stringBuilder.append("==========================================\n");
        return stringBuilder + "Inventory has been opened";
    }

    /**
     * Generates the visual header for the inventory.
     * @return a formatted string containing the inventory title and borders
     */
    public String inventory(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n==========================================");
        stringBuilder.append("\n                INVENTORY                ");
        stringBuilder.append("\n==========================================");
        return stringBuilder.toString();
    }

    /**
     * Determines if executing this command should exit the game.
     * @return false, as opening the inventory does not terminate the main game loop
     */
    @Override
    public boolean exit() {
        return false;
    }
}
