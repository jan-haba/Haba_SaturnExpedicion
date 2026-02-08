package Command;

import Items.Item;
import Objects.GameObject;
import Objects.Player;
import Objects.Room;
import Objects.Storage;

public class PickUp implements Command{
    private Player player;
    private Room room;

    public PickUp(Player player) {
        this.player = player;
    }

    @Override
    public String execute(String command) {
        room = player.getCurrRoom();
        Item itemFound = null;
        for (Item item : room.getItems()){
            if (item.getName().equalsIgnoreCase(command)){
                itemFound = item;
                room.getItems().remove(itemFound);
                break;
            }
        }
        if(itemFound == null) {
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
            return "Item " + itemFound.getName() + " was picked up.";
        }
        return "Item wasn't found anywhere.";
    }

    @Override
    public boolean exit() {
        return false;
    }
}
