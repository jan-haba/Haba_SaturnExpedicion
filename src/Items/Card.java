package Items;

/**
 * Represents a keycard item in the game.
 * Cards are typically used to grant access to restricted areas or to operate specific machinery
 * (e.g., Reactor Card, E.M. Card).
 */
public class Card extends Item {
    public Card(String name, String type, String id, String description) {
        super(name, type, id, description);
    }
}