package Objects;

public class GameObject {
    protected String name;
    protected String id;
    protected String description;

    public GameObject(String name, String id, String description) {
        this.name = name;
        this.id = id;
        this.description = description;
    }

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
}
