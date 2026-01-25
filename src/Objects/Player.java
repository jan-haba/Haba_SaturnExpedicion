package Objects;

import Items.Item;
import java.util.Arrays;

public class Player {
    private Item[] inventory;
    private String name;
    private Room currRoom;
    private boolean isSuitEquiped;

    public Player(String name, Room currRoom, boolean isSuitEquiped) {
        this.inventory = new Item[4];
        this.name = name;
        this.currRoom = currRoom;
        this.isSuitEquiped = isSuitEquiped;
    }
    public Player() {
    }

    public String pickUp(Item item) {return null;}
    public String dropDown(Item item) {return null;}
    public String openInventory() {return null;}
    public void equipSuit(){};
    public boolean hasItem(String itemName){return true;}
    public String use(Item item){return null;}
    public String doAction(){return null;}

    @Override
    public String toString() {
        return "Player{" +
                "inventory=" + Arrays.toString(inventory) +
                ", name='" + name + '\'' +
                ", currRoom=" + currRoom +
                ", isSuitEquiped=" + isSuitEquiped +
                '}';
    }

    public Room getCurrRoom() {
        return currRoom;
    }

    public void setCurrRoom(Room currRoom) {
        this.currRoom = currRoom;
    }
}

