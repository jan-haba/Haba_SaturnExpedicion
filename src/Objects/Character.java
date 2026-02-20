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
    private Room currRoom;
    private HashMap<Integer, String>dialogues;
    private boolean isFollower;

    public Character(String name, String description,String id, ArrayList<String> itemsRaw, Room currRoom, boolean isFollower) {
        this.name = name;
        this.description = description;
        this.id = id;
        this.itemsRaw = itemsRaw;
        this.items = new ArrayList<>();
        this.currRoom = currRoom;
        this.dialogues = new HashMap<>();
        this.isFollower = isFollower;
    }

    public void giveItem(Item item, Player player){}
    public void moveRoom(){}
    public String getDialogue(Character character){return null;}
    public String talk(int dialogueID){return null;}

    @Override
    public String toString() {
        return "Character{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", itemsRaw=" + itemsRaw +
                ", items=" + items +
                ", currRoom=" + currRoom +
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
}
