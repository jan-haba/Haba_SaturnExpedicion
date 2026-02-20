package Objects;

import java.util.ArrayList;

import Items.Item;

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
    public void addCharacter(Character character){
        if(this.characters == null){
            this.characters = new ArrayList<>();
        }
        this.characters.add(character);

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

        sb.append("\n==================================================\n");
        sb.append(" LOCATION: ").append(name.toUpperCase()).append("\n");
        sb.append("==================================================\n");
        sb.append(description).append("\n");

        if (items != null && !items.isEmpty()) {
            sb.append("\nITEMS:\n");
            for (Item item : items) {
                sb.append("  - ").append(item.getName()).append("\n");
            }
        }

        if (gameObjects != null && !gameObjects.isEmpty()) {
            sb.append("\nOBJECTS:\n");
            for (GameObject obj : gameObjects) {
                sb.append("  [ ").append(obj.getName()).append(" ]\n");
            }
        }

        if (characters != null && !characters.isEmpty()) {
            sb.append("\nCHARACTERS:\n");
            for (Character character : characters) {
                sb.append("  ( ").append(character.getName()).append(" )\n");
            }
        }

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

    public Object findObject(String objectName){
        if (items != null) {
            for (Item item : items) {
                if (item.getName().equalsIgnoreCase(objectName)) {
                    return item;
                }
            }
        }
        if (gameObjects != null) {
            for (GameObject gameObject : gameObjects) {
                if (gameObject.getName().equalsIgnoreCase(objectName)) {
                    return gameObject;
                }
            }
        }
        return null;
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

    public ArrayList<String> getCharactersRaw() {
        return charactersRaw;
    }

    public ArrayList<Character> getCharacters() {
        return characters;
    }
}
