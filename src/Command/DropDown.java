package Command;

import Items.Item;
import Objects.Player;
import Objects.Room;

/**
 * Command for dropping an item from the player's inventory.
 * The dropped item is added to the current room's items.
 */
public class DropDown implements Command{
    private Player player;
    private Room room;

    public DropDown(Player player){
        this.player = player;
    }

    /**
     * Executes the drop command.
     * Searches the player's inventory for the specified item. If found,
     * removes it from the inventory and places it in the current room.
     * @param command the name of the item the player wants to drop
     * @return a message indicating whether the item was successfully dropped or not
     */
    @Override
    public String execute(String command) {
        room = player.getCurrRoom();
        Item itemFound = player.getItem(command);

        if (itemFound != null) {
            player.dropDown(itemFound);
            room.addItem(itemFound);

            return "Item '" + itemFound.getName() + "' was dropped on the floor.";
        }else{
            return "Item wasn't found in your inventory.";
        }
    }

    /**
     * Determines if executing this command should exit the game.
     * @return false, as dropping an item does not terminate the main game loop
     */
    @Override
    public boolean exit() {
        return false;
    }
}