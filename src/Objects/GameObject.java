package Objects;

/**
 * Base class representing interactive stationary objects in the game environment.
 * Specific objects (like Reactor, Control Panel, Storage) inherit from this class
 * and override its methods to provide unique interactions.
 */
public class GameObject {
    protected String name;
    protected String id;
    protected String description;

    public GameObject(String name, String id, String description) {
        this.name = name;
        this.id = id;
        this.description = description;
    }

    /**
     * Defines the interaction logic when a player uses or manipulates the object.
     * Designed to be overridden by subclasses to trigger specific events or state changes.
     * @param player the player interacting with the object
     * @return a message describing the result of the interaction, or an empty string by default
     */
    public String execute(Player player){
        return "";
    }

    @Override
    public String toString() {
        return "GameObject{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
