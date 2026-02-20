package Objects;

public class ControlPanel extends GameObject {
    public ControlPanel(String name, String id, String description) {
        super(name, id, description);
    }

    @Override
    public String toString() {
        return "ControlPanel{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
