package Command;

import Items.Item;
import Objects.GameObject;
import Objects.Player;

/**
 * Command for examining items or game objects.
 * Allows the player to read the description of an item in their inventory
 * or an item/object located in the current room.
 */
public class Examine implements Command{
    private Player player;

    public Examine(Player player) {
        this.player = player;
    }

    /**
     * Executes the examine command.
     * Searches for the specified target first in the player's inventory,
     * and then in the current room.
     * @param command the name of the item or game object to examine
     * @return the description of the object, or a message indicating it wasn't found
     */
    @Override
    public String execute(String command) {
        if (command == null || command.trim().isEmpty()) {
            return "What do you want to examine?";
        }

        Item inventoryItem = player.getItem(command);
        if (inventoryItem != null) {
            return inventoryItem.getDescription();
        }

        Object target = player.getCurrRoom().findObject(command);
        if (target != null) {
            if (target instanceof Item) {
                return ((Item) target).getDescription();
            }
            if (target instanceof GameObject) {
                return ((GameObject) target).getDescription();
            }
        }
        return "I cannot see any '" + command + "' here to examine.";
    }

    /**
     * Determines if executing this command should exit the game.
     * @return false, as examining an object does not terminate the main game loop
     */
    @Override
    public boolean exit() {
        return false;
    }
}