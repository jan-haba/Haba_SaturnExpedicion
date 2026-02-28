package Command;

import Items.Item;
import Items.Toolkit;
import Objects.GameObject;
import Objects.Player;
import Objects.Room;
import Objects.Storage;

/**
 * Command for picking up items in the game.
 * The player can pick up items lying on the floor, extract tools from toolkits
 * in their inventory, or retrieve items from storage objects in the room.
 */
public class PickUp implements Command{
    private Player player;
    private Room room;

    public PickUp(Player player) {
        this.player = player;
    }

    /**
     * Executes the pick up command.
     * Searches for the item in the room, then inside any toolkits in the player's inventory,
     * and finally inside any storage objects in the room.
     * @param command the name of the item to pick up
     * @return a message indicating success, failure, or a full inventory
     */
    @Override
    public String execute(String command) {
        if (command == null || command.trim().isEmpty()) {
            return "What do you want to pick up?";
        }

        room = player.getCurrRoom();
        Item itemFound = null;

        for (Item item : room.getItems()){
            if (item.getName().equalsIgnoreCase(command)){
                itemFound = item;
                break;
            }
        }
        if (itemFound != null) {
            room.getItems().remove(itemFound);
        }
        if (itemFound == null){
            for (Item item: player.getInventory()){
                if (item instanceof Toolkit){
                    Toolkit toolkit = (Toolkit) item;
                     itemFound = toolkit.execute(player,command);
                    if (itemFound != null) break;
                }
            }
        }
        if(itemFound == null && room.getGameObjects()!= null) {
            for (GameObject obj : room.getGameObjects()) {
                if (obj instanceof Storage) {
                    Storage storage = (Storage) obj;
                    itemFound = storage.findItem(command);
                    if (itemFound != null) break;
                }
            }
        }
        if (itemFound != null){
            String result = player.pickUp(itemFound);

            if (result.equalsIgnoreCase("Inventory is full")) {
                room.addItem(itemFound);
                return "Your inventory is full! The '" + itemFound.getName() + "' was left on the floor.";
            }
            return result;
        }
        return "I cannot see any '" + command + "' here to pick up.";
    }

    /**
     * Determines if executing this command should exit the game.
     * @return false, as picking up an item does not terminate the main game loop
     */
    @Override
    public boolean exit() {
        return false;
    }
}