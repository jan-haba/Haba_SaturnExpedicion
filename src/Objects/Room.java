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
    public String getRoom() {
        StringBuilder sb = new StringBuilder();

        // 1. Záhlaví a Popis (vypisujeme vždy)
        sb.append("\n==================================================\n");
        sb.append(" LOCATION: ").append(name.toUpperCase()).append("\n");
        sb.append("==================================================\n");
        sb.append(description).append("\n");

        // 2. ITEMS - vypíše se jen, když nejsou null a nejsou prázdné
        if (items != null && !items.isEmpty()) {
            sb.append("\nITEMS:\n");
            for (Item item : items) {
                sb.append("  - ").append(item.getName()).append("\n");
            }
        }

        // 3. OBJECTS - vypíše se jen, když nejsou null a nejsou prázdné
        if (gameObjects != null && !gameObjects.isEmpty()) {
            sb.append("\nOBJECTS:\n");
            for (GameObject obj : gameObjects) {
                sb.append("  [ ").append(obj.getName()).append(" ]\n");
            }
        }

        // 4. CHARACTERS - vypíše se jen, když nejsou null a nejsou prázdné
        if (characters != null && !characters.isEmpty()) {
            sb.append("\nCHARACTERS:\n");
            for (Character character : characters) {
                sb.append("  ( ").append(character.getName()).append(" )\n");
            }
        }

        // 5. EXITS - ty vypisujeme vždy, abychom věděli, kam jít
        sb.append("\nAVAILABLE EXITS:\n");
        if (exits == null || exits.isEmpty()) {
            sb.append("  ==> none (you are trapped!)\n");
        } else {
            for (String exit : exits) {
                sb.append("  ==> ").append(exit).append("\n");
            }
        }

        sb.append("==================================================\n");

        return sb.toString();
    }




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
