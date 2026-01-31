package Objects;

import java.util.ArrayList;
import java.util.Objects;

import Items.Item;

public class Room {
    private String name;
    private String id;
    private String description;
    private ArrayList<String>itemsRaw;
    private ArrayList<Item>items = new ArrayList<>();
    private ArrayList<String>charactersRaw;
    private ArrayList<Character>characters = new ArrayList<>();
    private ArrayList<String>objectsRaw;
    private ArrayList<Object>object = new ArrayList<>();
    private ArrayList<String>exits;
    private boolean isAccessible;
    private int requiredCode;

    public void addItem(Item item){
        if (this.items == null) {
            this.items = new ArrayList<>();
        }
        this.items.add(item);
    }

    public ArrayList<String> getItemsRaw() {
        return itemsRaw;
    }

    public Room(String name, String id, String description, boolean isAccessible, int requiredCode) {
        this.name = name;
        this.id = id;
        this.description = description;
        this.itemsRaw = new ArrayList<>();
        this.items = new ArrayList<>();
        this.charactersRaw = new ArrayList<>();
        this.characters = new ArrayList<>();
        this.objectsRaw = new ArrayList<>();
        this.object = new ArrayList<>();
        this.exits = new ArrayList<>();
        this.isAccessible = isAccessible;
        this.requiredCode = requiredCode;
    }

    @Override
    public String toString() {
        return "Room{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", itemsRaw=" + itemsRaw +
                ", items=" + items +
                ", charactersRaw=" + charactersRaw +
                ", characters=" + characters +
                ", objectsRaw=" + objectsRaw +
                ", object=" + object +
                ", exits=" + exits +
                ", isAccessible=" + isAccessible +
                ", requiredCode=" + requiredCode +
                '}';
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getExits() {
        return exits;
    }

}
