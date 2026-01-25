package Objects;

import java.util.ArrayList;
import Items.Item;

public class Room {
    private String name;
    private String id;
    private String description;
    private ArrayList<Item>items;
    private ArrayList<Objects.Character>characters;
    private ArrayList<Object>objects;
    private ArrayList<String>exits;
    private boolean isAccessible;
    private int requiredCode;

    public String getDescription(){return null;}
    public void removeItem(Item item){};
    public void removeCharacter(Objects.Character character){};
    public void unlockRoom(){}
    public boolean tryUnlock(int inputCode){return true;}
    public Room getExit(String directon){return null;}
    public void addItem(Item item){}
    public void addCharacter(Character character){}
    public void addObject(Object object){}

    public Room(String name, String id, String description, boolean isAccessible, int requiredCode) {
        this.name = name;
        this.id = id;
        this.description = description;
        this.items = new ArrayList<>();
        this.characters = new ArrayList<>();
        this.objects = new ArrayList<>();
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
                ", items=" + items +
                ", characters=" + characters +
                ", objects=" + objects +
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
