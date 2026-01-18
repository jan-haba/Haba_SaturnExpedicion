package Objects;

import Items.Item;

public class Player {
    private Item[] inventory;
    private String name;
    private Room currRoom;
    private boolean isSuitEquiped;

    public String move(Room room) {return null;}
    public String pickUp(Item item) {return null;}
    public String dropDown(Item item) {return null;}
    public String openInventory() {return null;}
    public void equipSuit(){};
    public boolean hasItem(String itemName){return true;}
    public String use(Item item){return null;}
    public String doAction(){return null;}
}
