package Objects;

import java.util.ArrayList;
import java.util.HashMap;

import Items.Item;

public class Room {
    private String name;
    private String description;
    private ArrayList<Item>items;
    private ArrayList<Objects.Character>characters;
    private ArrayList<Object>objects;
    private HashMap<String,Room>exits;
    private boolean isAccesible;
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
}
