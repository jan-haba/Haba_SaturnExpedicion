package Objects;

import Items.Item;

import java.util.ArrayList;
import java.util.HashMap;

public class Character {
    private String name;
    private String description;
    private ArrayList<Item>items;
    private Room currRoom;
    private HashMap<Integer, String>dialogues;
    private boolean isFollower;

    public void giveItem(Item item, Player player){}
    public void moveRoom(){}
    public String getDialogue(Character character){return null;}
    public String talk(int dialogueID){return null;}
}
