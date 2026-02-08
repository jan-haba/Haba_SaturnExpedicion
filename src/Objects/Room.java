package Objects;

import java.util.ArrayList;
import java.util.Objects;

import Items.Item;
import Logic.GameEngine;

public class Room {
    private String name;
    private String id;
    private String description;
    private ArrayList<String>itemsRaw;
    private ArrayList<Item>items = new ArrayList<>();
    private ArrayList<String>charactersRaw;
    private ArrayList<Character>characters = new ArrayList<>();
    private ArrayList<String>gameObjectsRaw;
    private ArrayList<GameObject>gameObjects = new ArrayList<>();
    private ArrayList<String>exits;
    private boolean isAccessible;
    private int requiredCode;

    public void addItem(Item item){
        if (this.items == null) {
            this.items = new ArrayList<>();
        }
        this.items.add(item);
    }
    public void addObject(GameObject objects){
        if (this.gameObjects == null){
            this.gameObjects = new ArrayList<>();
        }
        this.gameObjects.add(objects);
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
                ", gameObjectsRaw=" + gameObjectsRaw +
                ", gameObjects=" + gameObjects +
                ", exits=" + exits +
                ", isAccessible=" + isAccessible +
                ", requiredCode=" + requiredCode +
                '}';
    }
    public String getRoom(){
        StringBuilder sb = new StringBuilder();
        sb.append("\n==================================================\n");
        sb.append(" LOCATION: ").append(name.toUpperCase()).append("\n");
        sb.append("==================================================\n");
        sb.append(description).append("\n\n");

        sb.append("ITEMS: ");
        if (items == null || items.isEmpty()) {
            sb.append("none\n");
        } else {
            sb.append("\n");
            for (Item item : items) {
                sb.append("  - ").append(item.getName()).append("\n");
            }
        }
        sb.append("\n");

        sb.append("OBJECTS: ");
        if (gameObjects == null || gameObjects.isEmpty()) {
            sb.append("none\n");
        } else {
            sb.append("\n");
            for (GameObject obj : gameObjects) {
                sb.append("  [ ").append(obj.getName()).append(" ]\n");
            }
        }
        sb.append("\n");

            sb.append("CHARACTERS: ");
            if (characters == null || characters.isEmpty()) {
                sb.append("none\n");
            } else {
                sb.append("\n");
                for (Character character : characters) {
                    sb.append("  ( ").append(character.getName()).append(" )\n");
                }
            }

        sb.append("\nAVAILABLE EXITS: ");
        if (exits == null || exits.isEmpty()) {
            sb.append("none (you are trapped!)");
        } else {
            sb.append("\n");
            for (String exit : exits) {
                sb.append("  ==> ").append(exit).append("\n");
            }}
        return sb.toString();}




    public String getName() {
        return name;
    }

    public ArrayList<String> getExits() {
        return exits;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public ArrayList<String> getGameObjectsRaw() {
        return gameObjectsRaw;
    }

    public ArrayList<GameObject> getGameObjects() {
        return gameObjects;
    }
}
