package Objects;

import Items.Item;
import Items.Suit;

import java.util.Arrays;
import java.util.Optional;

/**
 * class that handles player
 */
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

    public boolean hasSuit(){
        for (Item item : inventory) {
            if (item instanceof Suit) {
                return true;
            }
        }
        return false;
    }

    public void removeItem(String itemName){
        for (int i = 0; i < inventory.length; i++) {
            if (this.inventory[i] != null) {
                if (inventory[i].getName().equals(itemName)) {
                    inventory[i] = null;
                }
            }
        }
    }

    /**
     * method that lets load inventory
     * @return inventory
     */
    public void openInventory() {
        System.out.println("\n==========================================");
        System.out.println("             INVENTORY             ");
        System.out.println("==========================================");
        for (int i = 0; i < inventory.length; i++) {
            System.out.println("-" + inventory[i]);
        }
    }

    /**
     * method tha
     * @param item
     * @return
     */
    public String pickUp(Item item) {
        for (int i = 0; i < inventory.length; i++) {
            if (inventory[i] == null){
                inventory[i] = item;
                return "item was picked up";
            }
        }
        return "inventory is full";
    }
    public String dropDown(Item item) {
        for (int i = 0; i < inventory.length; i++) {
            if (inventory[i].getName().equalsIgnoreCase(item.getName())){
                inventory[i] = null;
                return "item was droped down";
            }
        }
        return "item wasnt found in the inventory";
    }
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

    public Item[] getInventory() {
        return inventory;
    }

    public void setSuitEquiped(boolean suitEquiped) {
        isSuitEquiped = suitEquiped;
    }
}

