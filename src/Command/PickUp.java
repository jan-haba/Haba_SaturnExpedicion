package Command;

import Items.Item;
import Objects.Player;
import Objects.Room;

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
            if (item.getName().equals(command)){
                itemFound = item;
                break;
            }
        }
        if (itemFound != null){
            player.pickUp(itemFound);
            room.getItems().remove(itemFound);
            return "Item was picked up";
        }else {
            return "Item wasnt found";
        }
    }

    @Override
    public boolean exit() {
        return false;
    }
}
