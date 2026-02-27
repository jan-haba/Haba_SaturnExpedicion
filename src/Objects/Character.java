package Objects;

import Items.Item;

import java.util.ArrayList;
import java.util.HashMap;

public class Character {
    private String name;
    private String description;
    private String id;
    private ArrayList<String>itemsRaw;
    private ArrayList<Item>items;
    private String currRoomID;
    private HashMap<Integer, String>dialogues;
    private boolean isFollower;

    public Character(String name, String description,String id, ArrayList<String> itemsRaw, String currRoom, boolean isFollower) {
        this.name = name;
        this.description = description;
        this.id = id;
        this.itemsRaw = itemsRaw;
        this.items = new ArrayList<>();
        this.currRoomID = currRoom;
        this.dialogues = new HashMap<>();
        this.isFollower = isFollower;
    }
    public HashMap<Integer, String> getDialogue(){
        return dialogues;
    }

    public String printDialogues() {
        if (dialogues == null || dialogues.isEmpty()) {
            return name + " has nothing to say.";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("--- Dialogue with ").append(name).append(" ---\n");
        for (Integer key : dialogues.keySet()) {
            sb.append(" [").append(key).append("] ").append(dialogues.get(key)).append("\n");
        }
        sb.append(" [0] Exit dialogue\n");
        sb.append("Choose answer by typing number:");
        return sb.toString();
    }
    public void setFollower(boolean follower) {
        this.isFollower = follower;
    }

    public Item giveItem(String itemID) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId().equalsIgnoreCase(itemID)) {
                Item item = items.get(i);
                items.remove(i);
                return item;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Character{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", itemsRaw=" + itemsRaw +
                ", items=" + items +
                ", currRoomID=" + currRoomID +
                ", dialogues=" + dialogues +
                ", isFollower=" + isFollower +
                '}';
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public ArrayList<String> getItemsRaw() {
        return itemsRaw;
    }
    public void addItem(Item item){
        if (item!= null){
            items.add(item);
        }
    }
    public void setCurrRoomID(String currRoomID) {
        this.currRoomID = currRoomID;
    }
}
