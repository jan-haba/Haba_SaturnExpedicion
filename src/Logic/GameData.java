package Logic;

import Items.*;
import Objects.*;
import Objects.Character;
import com.google.gson.Gson;


import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

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

    public GameData() {
        this.rooms = new ArrayList<>();
        this.itemsRaw = new ArrayList<>();
        this.items = new ArrayList<>();
        this.characters = new ArrayList<>();
        this.gameObjects = new ArrayList<>();
        this.gameObjectRaws = new ArrayList<>();
        this.codes = new ArrayList<>();
    }

    /**
     * method that loads all game data from JSON
     * @param resourcePath
     * @return
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

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    /**
     * method that helps find a room
     * @param roomName = name of that room
     * @return wanted room
     */
    public Room locateRoom(String roomName){
        for (Room room: rooms) {
            if (room.getName().equals(roomName)){
                return room;
            }
        }
        throw new IllegalArgumentException("Room wasnt found with this name");
    }
    public Item locateItem(String itemName){
        for (Item item: items){
            if(item.getName().equals(itemName)){
                return item;
            }
        }
        throw new IllegalArgumentException("Item wasnt found with this name");
    }

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

    private Item findItemById(String id) {
        for (Item i : items) {
            if (i.getId().equals(id))
                return i;
        }
        return null;
    }
    private Item findItemByName(String name){
        for(Item i : items){
            if (i.getName().equals(name)){
                return i;
            }
        }
        return null;
    }
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
                ControlPanel controlPanel = new ControlPanel(raw.name, raw.id, raw.description);
                gameObjects.add(controlPanel);
            }
            if(raw.type.equals("Reactor")) {
                Reactor reactor = new Reactor(raw.name, raw.id, raw.description,raw.isFixed);
                gameObjects.add(reactor);
            }
            if(raw.type.equals("EscapeModule")) {
                EscapeModule module = new EscapeModule(raw.name, raw.id, raw.description);
                gameObjects.add(module);
            }
        }
    }

    public ArrayList<GameObject> getGameObjects() {
        return gameObjects;
    }
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
    private Character findCharacterById(String id){
        for (Character character : characters) {
            if (character.getId().equals(id)) {
                return character;
            }
        }
        return null;

    }
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




    private GameObject findObjectById(String id) {
        for (GameObject obj : gameObjects) {
            if (obj.getId().equals(id)) {
                return obj;
            }
        }
        return null;
    }
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
}
class ItemRaw{
    String id;
    String name;
    String description;
    String type;
    ArrayList<String> toolsRaw;
    String roomID;
}

class GameObjectRaw {
    public String type;
    public String id;
    public String name;
    public String description;
    public ArrayList<String> itemsRaw;
    public boolean isFixed;
}

