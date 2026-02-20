package Command;

import Items.Item;
import Items.Toolkit;
import Objects.GameObject;
import Objects.Player;
import Objects.Room;
import Objects.Storage;

import java.lang.reflect.Array;
import java.util.ArrayList;

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
            return player.pickUp(itemFound);
        }
        return "Item wasn't found anywhere.";
    }

    @Override
    public boolean exit() {
        return false;
    }
}
