package Command;

import Items.Item;
import Objects.GameObject;
import Objects.Player;

/**
 * command used for examination of item or object
 */

public class Examine implements Command{
    private Player player;

    public Examine(Player player) {
        this.player = player;
    }

    /**
     * method execute
     * @param command name of the item/game object
     * @return objects description or message that the object does not exist
     */

    @Override
    public String execute(String command) {
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
     * just exit
     * @return
     */
    @Override
    public boolean exit() {
        return false;
    }
}
