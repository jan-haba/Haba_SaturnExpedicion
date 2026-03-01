package Logic;

import Items.*;
import Objects.*;
import Objects.Character;
import com.google.gson.Gson;


import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Handles the loading, instantiation, and linking of all game data from a JSON file.
 * This class acts as the central repository for rooms, items, game objects, and characters.
 */
public class GameData {
    public ArrayList<Room>rooms;
    public ArrayList<Item>items;
    public ArrayList<ItemRaw>itemsRaw;
    public ArrayList<GameObjectRaw>gameObjectRaws;
    public ArrayList<GameObject>gameObjects;
    public ArrayList<Character>characters;
    public ArrayList <Integer>codes;
    private String gameState = "EXPLORING";
    private Character activeCharacter = null;
    private ArrayList<String> commands;
    private HashMap<String, String> clues;
    private ArrayList<String> logo;
    private HashMap<String, String>UI;

    public GameData() {
        this.rooms = new ArrayList<>();
        this.itemsRaw = new ArrayList<>();
        this.items = new ArrayList<>();
        this.characters = new ArrayList<>();
        this.gameObjects = new ArrayList<>();
        this.gameObjectRaws = new ArrayList<>();
        this.codes = new ArrayList<>();
        this.commands = new ArrayList<>();

    }

    /**
     * Loads and parses the game data from a specified JSON resource path using Gson.
     * @param resourcePath the path to the JSON file in the resources folder
     * @return a populated GameData instance
     * @throws RuntimeException if the file is not found or parsing fails
     */
    public static GameData loadGameDataFromResources(String resourcePath) {
        Gson gson = new Gson();
        try (InputStream is = Main.class.getResourceAsStream(resourcePath)) {
            if (is == null) {
                throw new IllegalStateException("Resource wasnt found: " + resourcePath);
            }
            return gson.fromJson(
                    new InputStreamReader(is, StandardCharsets.UTF_8),
                    GameData.class
            );
        } catch (Exception e) {
            throw new RuntimeException("Trouble when loading JSON: " + e.getMessage());
        }
    }
// ======LOCATING METHODS=========
    /**
     * Searches for a room by its exact name.
     * @param roomName the display name of the room
     * @return the requested Room object
     * @throws IllegalArgumentException if no room with that name exists
     */
    public Room locateRoom(String roomName){
        for (Room room: rooms) {
            if (room.getName().equals(roomName)){
                return room;
            }
        }
        throw new IllegalArgumentException("Room wasn't found with this name: " + roomName);
    }

    /**
     * Searches for an item by its exact name.
     * @param itemName the display name of the item
     * @return the requested Item object
     * @throws IllegalArgumentException if no item with that name exists
     */
    public Item locateItem(String itemName){
        for (Item item: items){
            if(item.getName().equals(itemName)){
                return item;
            }
        }
        throw new IllegalArgumentException("Item wasn't found with this name: " + itemName);
    }

    /**
     * Finds item by id
     * @param id id of the item
     * @return found item
     * @throws IllegalArgumentException if no item with that id exists
     */
    private Item findItemById(String id) {
        for (Item i : items) {
            if (i.getId().equals(id))
                return i;
        }
        throw new IllegalArgumentException("Item wasn't found with this id: " + id);
    }

    /**
     * Finds character by id
     * @param id of the character
     * @return found character
     * @throws IllegalArgumentException if no character with that id exists
     */
    private Character findCharacterById(String id){
        for (Character character : characters) {
            if (character.getId().equals(id)) {
                return character;
            }
        }
        throw new IllegalArgumentException("Character wasn't found with this id: " + id);
    }

    /**
     * Finds game object by its id
     * @param id of the object
     * @return found object
     * @throws IllegalArgumentException if no game object with that id exists
     */
    private GameObject findObjectById(String id) {
        for (GameObject obj : gameObjects) {
            if (obj.getId().equals(id)) {
                return obj;
            }
        }
        return null;
    }
// ======CONVERTING METHODS=========
    /**
     * Iterates through the raw item data parsed from JSON and instantiates the proper
     * Item subclasses (Note, Suit, Card, Toolkit, or standard Item).
     */
    public void convertItems(){
        for (ItemRaw itemRaw : itemsRaw){
            Item item;
            switch (itemRaw.type){
                case "Note":
                    Note note = new Note(itemRaw.name, itemRaw.type,itemRaw.id,itemRaw.description,0,itemRaw.roomID );
                    codes.add(note.setCode());
                    item = note;
                    break;
                case "Suit":
                    Suit suit = new Suit(itemRaw.name,itemRaw.type,itemRaw.id, itemRaw.description);
                    item = suit;
                    break;
                case "Card":
                    Card card = new Card(itemRaw.name, itemRaw.type, itemRaw.id, itemRaw.description);
                    item = card;
                    break;
                case "Toolkit":
                    Toolkit toolkit = new Toolkit(itemRaw.name, itemRaw.type, itemRaw.id, itemRaw.description, itemRaw.toolsRaw);
                    item = toolkit;

                    break;
                default:
                    Item ajtem = new Item(itemRaw.name,itemRaw.type,itemRaw.id, itemRaw.description);
                    item = ajtem;
                    break;
            }
            items.add(item);
        }
    }

    /**
     * Iterates through raw game object data and instantiates the proper
     * GameObject subclasses (Storage, ControlPanel, Reactor, EscapeModule).
     */
    public void convertObjects() {
        for (GameObjectRaw raw : gameObjectRaws) {
            if (raw.type.equals("Storage")) {
                ArrayList<Item> storageInventory = new ArrayList<>();
                if (raw.itemsRaw != null) {
                    for (String itemId : raw.itemsRaw) {
                        Item realItem = findItemById(itemId);
                        if (realItem != null) {
                            storageInventory.add(realItem);
                        }
                    }
                }
                Storage storage = new Storage(raw.name, raw.id, raw.description, storageInventory);
                gameObjects.add(storage);
            }
            if(raw.type.equals("ControlPanel")) {
                ControlPanel controlPanel = new ControlPanel(raw.name, raw.id, raw.description, raw.isActivated, raw.puzzles);
                gameObjects.add(controlPanel);
            }
            if(raw.type.equals("Reactor")) {
                Reactor reactor = new Reactor(raw.name, raw.id, raw.description,raw.isFixed, raw.isFireExtinguished, raw.isCardInserted, raw.isCalibrated, raw.isWelded);
                gameObjects.add(reactor);
            }
            if(raw.type.equals("EscapeModule")) {
                EscapeModule module = new EscapeModule(raw.name, raw.id, raw.description);
                gameObjects.add(module);
                module.setControlPanel((ControlPanel) findObjectById("control_panel"));
            }
        }
    }
// ======LINKING METHODS=========
    /**
     * Links fully instantiated Item objects to their respective Rooms based on raw string IDs.
     */
    public void linkItemsToRooms() {
        for (Room r : rooms) {
            if (r.getItemsRaw() != null) {
                for (String id : r.getItemsRaw()) {
                    Item found = findItemById(id);
                    if (found != null) {
                        r.addItem(found);
                    }
                }
            }
        }
    }



    /**
     * Links fully instantiated GameObjects to their respective Rooms based on raw string IDs.
     */
    public void linkObjectsToRooms() {
        for (Room r : rooms) {
            if (r.getGameObjectsRaw() != null) {
                for (String objectId : r.getGameObjectsRaw()) {
                    GameObject found = findObjectById(objectId);
                    if (found != null) {
                        r.addObject(found);
                    }
                }
            }
        }
    }

    /**
     * Links Characters to their respective Rooms based on raw string IDs.
     */
    public void linkCharactersToRooms(){
        for (Room r : rooms){
            if (r.getCharactersRaw() != null){
                for (String charID : r.getCharactersRaw()){
                    Character found = findCharacterById(charID);
                    if(found != null){
                        r.addCharacter(found);
                    }
                }
            }
        }
    }

    /**
     * Links Items to Characters' inventories based on raw string IDs.
     */
    public void linkItemsToCharacters(){
        for (Character character : characters){
            if (character.getItemsRaw()!= null){
                for (String id : character.getItemsRaw()){
                    Item found = findItemById(id);
                    if (found != null){
                        character.addItem(found);
                    }
                }
            }
        }
    }

    /**
     * Links specific Items (tools) to Toolkit objects based on raw string IDs.
     */
    public void linkToolsToToolkits() {
        for (Item toolkit: items) {
            if (toolkit instanceof Toolkit) {
                Toolkit tk = (Toolkit) toolkit;
                if (tk.getToolsRaw() != null) {
                    for (String id : tk.getToolsRaw()) {
                        Item found = findItemById(id);
                        if (found != null) {
                            tk.addItem(found);
                        }
                    }
                }
            }
        }

    }

    /**
     * Maps the dynamically generated access codes from Note items
     * directly to the doors/rooms they are meant to unlock.
     */
    public void setCodesToRooms(){
        for (Item item: items){
            if (item instanceof Note){
                Note note = (Note) item;
                for (Room room : rooms){
                    if (!room.isAccessible() && room.getRequiredCode() == 0){
                        if (note.getRoomID().equalsIgnoreCase(room.getId())){
                            room.setRequiredCode(note.getCode());
                        }
                    }
                }
            }
        }
    }

    public String logo() {
        if (logo == null || logo.isEmpty()) {
            return "SATURN EXPEDITION\n";
        }
        StringBuilder sb = new StringBuilder();
        for (String line : logo) {
            sb.append(line).append("\n");
        }
        return sb.toString();
    }




    public ArrayList<Character> getCharacters() {
        return characters;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public Character getActiveCharacter() {
        return activeCharacter;
    }

    public void setActiveCharacter(Character activeCharacter) {
        this.activeCharacter = activeCharacter;
    }

    public String getGameState() {
        return gameState;
    }

    public void setGameState(String gameState) {
        this.gameState = gameState;
    }

    public ArrayList<String> getCommands() {
        return commands;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public ArrayList<GameObject> getGameObjects() {
        return gameObjects;
    }

    public HashMap<String, String> getClues() {
        return clues;
    }
    public ArrayList<String> getLOGO() {
        return logo;
    }

    public HashMap<String, String> getUI() {
        return UI;
    }
}

/**
 * Temporary data transfer object (DTO) used by Gson to parse items before
 * they are fully instantiated into specific Item subclasses.
 */
class ItemRaw{
    String id;
    String name;
    String description;
    String type;
    ArrayList<String> toolsRaw;
    String roomID;
}

/**
 * Temporary data transfer object (DTO) used by Gson to parse game objects before
 * they are fully instantiated into specific GameObject subclasses.
 */
class GameObjectRaw {
    String type;
    String id;
    String name;
    String description;
    ArrayList<String> itemsRaw;
    boolean isActivated;
    boolean isFixed;
    HashMap<String,String>puzzles;
    boolean isFireExtinguished ;
    boolean isCardInserted;
    boolean isCalibrated;
    boolean isWelded ;
}

