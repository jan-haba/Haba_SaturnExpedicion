package Objects;

import Items.Item;
import Items.Suit;

import java.util.Arrays;

/**
 * Represents the main character of the game.
 * Manages the player's inventory, current location, win/loss state,
 * time loop mechanics, and equipment status.
 */
public class Player {
    private Item[] inventory;
    private String name;
    private Room currRoom;
    private boolean isSuitEquiped;
    private int winState;
    private int timeRemaining;
    private int loopCount;

    public Player(String name, Room currRoom, boolean isSuitEquiped, int winState, int timeRemaining, int loopCount) {
        this.inventory = new Item[4];
        this.name = name;
        this.currRoom = currRoom;
        this.isSuitEquiped = isSuitEquiped;
        this.winState = 0;
        this.loopCount = loopCount;
        this.timeRemaining = timeRemaining;
    }

    /**
     * Checks if the player has any Suit item in their inventory.
     * @return true if a suit is found, false otherwise
     */
    public boolean hasSuit(){
        for (Item item : inventory) {
            if (item instanceof Suit) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the player's inventory contains an item with a specific ID.
     * @param itemID the unique ID of the item to look for
     * @return true if the item is in the inventory, false otherwise
     */
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

    /**
     * Searches the inventory for an item by its exact name.
     * @param itemName the display name of the item
     * @return the Item object if found, or null if it's not in the inventory
     */
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

    /**
     * Removes an item from the player's inventory by its name.
     * Only removes the first instance found.
     * @param itemName the name of the item to remove
     */
    public void removeItem(String itemName){
        for (int i = 0; i < inventory.length; i++) {
            if (this.inventory[i] != null) {
                if (inventory[i].getName().equalsIgnoreCase(itemName)) {
                    inventory[i] = null;
                    break;
                }
            }
        }
    }

    /**
     * Generates a formatted string representing the player's current inventory.
     * Displays the names of the items or indicates if a slot is empty.
     * @return a formatted string of the inventory contents
     */
    public String openInventory() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Item item: inventory){
            String line = "";
            if (item != null){
                line = item.getName();
                stringBuilder.append("- ").append(line).append("\n");
            }else {
                stringBuilder.append("- [Empty]").append("\n");
            }
        }
        return stringBuilder.toString();
    }

    /**
     * Attempts to add an item to the first available slot in the player's inventory.
     * @param item the item to pick up
     * @return a success message if picked up, or an error message if the inventory is full
     */
    public String pickUp(Item item) {
        for (int i = 0; i < inventory.length; i++) {
            if (inventory[i] == null){
                inventory[i] = item;
                return "Item " + item.getName() + "has been picked up";
            }
        }
        return "Inventory is full";
    }

    /**
     * Removes an item from the inventory (simulating dropping it).
     * @param item the item to drop
     * @return a success message if dropped, or an error message if it wasn't found
     */
    public String dropDown(Item item) {
        for (int i = 0; i < inventory.length; i++) {
            if (inventory[i].getName().equalsIgnoreCase(item.getName())){
                inventory[i] = null;
                return "Item '" + item.getName() + "' was dropped.";
            }
        }
        return "Item wasn't found in the inventory.";
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

    public int getTimeRemaining() { return timeRemaining; }
    public void setTimeRemaining(int timeRemaining) { this.timeRemaining = timeRemaining; }

    public int getLoopCount() { return loopCount; }
    public void incrementLoop() { this.loopCount++; }

    public boolean isSuitEquiped() {
        return isSuitEquiped;
    }
    public void setSuitEquiped(boolean suitEquiped) {
        isSuitEquiped = suitEquiped;
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
    public void setInventory(Item[] inventory) {
        this.inventory = inventory;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getWinState() {
        return winState;
    }
    public void setWinState(int winState) {
        this.winState = winState;
    }
}