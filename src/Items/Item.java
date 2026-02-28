package Items;

import Objects.Player;

/**
 * Base class representing a general item in the game.
 * All specific item types (like Card, Toolkit, etc.) inherit from this class.
 * It holds the basic properties that every item shares.
 */
public class Item {
    private String name;
    private String type;
    private String id;
    private String description;


    public Item(String name, String type, String id, String description) {
        this.name = name;
        this.type = type;
        this.id = id;
        this.description = description;
    }

    /**
     * A method to be overridden by subclasses for specific item interactions (like opening a toolkit).
     * By default, a standard item does nothing when executed.
     * @param player the player interacting with the item
     * @param string an additional command or target name
     * @return null by default, or an extracted Item in specialized subclasses
     */
    public Item execute(Player player, String string){
        return null;
    }

    @Override
    public String toString() {
        return String.format("[%s] - %s (ID: %s)",
                name,
                description,
                id);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription(){
        return description;
    }
}