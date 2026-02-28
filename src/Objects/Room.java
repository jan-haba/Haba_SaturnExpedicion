package Objects;

import java.util.ArrayList;
import Items.Item;

/**
 * Represents a location (room) within the spaceship.
 * Contains items, interactive game objects, characters, and connections (exits) to other rooms.
 */
public class Room {
    private String name;
    private String id;
    private String description;
    private ArrayList<String>itemsRaw;
    private ArrayList<Item>items;
    private ArrayList<String>charactersRaw;
    private ArrayList<Character>characters;
    private ArrayList<String>gameObjectsRaw;
    private ArrayList<GameObject>gameObjects = new ArrayList<>();
    private ArrayList<String>exits;
    private boolean isAccessible;
    private int requiredCode;

    /**
     * Adds an item to the room's floor.
     * Safely initializes the list if it was left null by the JSON parser.
     * @param item the item to drop in the room
     */
    public void addItem(Item item){
        if (this.items == null) {
            this.items = new ArrayList<>();
        }
        this.items.add(item);
    }

    /**
     * Adds an interactive game object to the room.
     * @param objects the game object to place in the room
     */
    public void addObject(GameObject objects){
        if (this.gameObjects == null){
            this.gameObjects = new ArrayList<>();
        }
        this.gameObjects.add(objects);
    }

    /**
     * Adds an NPC character to the room.
     * @param character the character to place in the room
     */
    public void addCharacter(Character character){
        if(this.characters == null){
            this.characters = new ArrayList<>();
        }
        this.characters.add(character);

    }

    /**
     * Generates a beautifully formatted terminal-style interface displaying the room's details,
     * including its description, visible items, objects, characters, and available exits.
     * @return the formatted room description string
     */
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

    /**
     * Generates a beautifully formatted terminal-style interface displaying the room's details,
     * including its description, visible items, objects, characters, and available exits.
     * @return the formatted room description string
     */
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

    /**
     * Searches the room for a specific object or item by its name.
     * Checks loose items first, then interactive game objects.
     * @param objectName the name of the object to find
     * @return the Object found (either Item or GameObject), or null if missing
     */
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

    public int getRequiredCode() {
        return requiredCode;
    }

    public boolean isAccessible() {
        return isAccessible;
    }

    public void setRequiredCode(int requiredCode) {
        this.requiredCode = requiredCode;
    }

    public String getId() {
        return id;
    }

    public ArrayList<String> getItemsRaw() {
        return itemsRaw;
    }

    public void setAccessible(boolean accessible) {
        isAccessible = accessible;
    }
}
