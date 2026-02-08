package Command;

import Items.Item;
import Objects.Player;
import Objects.Room;

public class DropDown implements Command{
    private Player player;
    private Room room;

    public DropDown(Player player){
        this.player = player;
    }

    @Override
    public String execute(String command) {
        room = player.getCurrRoom();
        Item itemFound = null;
        for (Item item : player.getInventory()){
            if (item.getName().equalsIgnoreCase(command)){
                itemFound = item;
                break;
            }
        }
        if (itemFound != null){
            room.addItem(itemFound);
            player.dropDown(itemFound);
            return "Item was dropped down";
        }else{
            return "Item wasnt found";
        }
    }

    @Override
    public boolean exit() {
        return false;
    }
}

