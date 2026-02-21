package Objects;

import Items.Item;
import Items.Suit;

import java.util.Arrays;

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

    public boolean hasItem(String itemID){
        for (int i = 0; i < inventory.length; i++) {
            if (this.inventory[i] != null) {
                if (inventory[i].getId().equalsIgnoreCase(itemID)){
                    return true;
                }
            }
        }
        return false;
    }
    public Item getItem(String itemName){
        for (int i = 0; i < inventory.length; i++) {
            if (this.inventory[i] != null) {
                if (inventory[i].getName().equalsIgnoreCase(itemName)){
                    return inventory[i];
                }
            }
        }
        return null;

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
                return "Item " + item.getName() + " picked up";
            }
        }
        return "Inventory is full";
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

    @Override
    public String toString() {
        return "Player{" +
                "inventory=" + Arrays.toString(inventory) +
                ", name='" + name + '\'' +
                ", currRoom=" + currRoom +
                ", isSuitEquiped=" + isSuitEquiped +
                '}';
    }

    public boolean isSuitEquiped() {
        return isSuitEquiped;
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

