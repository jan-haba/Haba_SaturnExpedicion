package Objects;

public class EscapeModule extends GameObject {
    public EscapeModule(String name, String id, String description) {
        super(name, id, description);
    }

    @Override
    public String toString() {
        return "EscapeModule{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
