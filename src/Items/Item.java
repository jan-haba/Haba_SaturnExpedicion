package Items;

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
}
