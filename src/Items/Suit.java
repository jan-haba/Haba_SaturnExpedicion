package Items;

/**
 * Represents a wearable suit item in the game.
 * Suits are typically used to protect the player from hazardous environments
 * (e.g., radiation, vacuum) and can be put on using the equip command.
 */
public class Suit extends Item {
    public Suit(String name, String type, String id, String description) {
        super(name, type, id, description);
    }
}