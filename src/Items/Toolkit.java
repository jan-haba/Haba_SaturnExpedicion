package Items;

import java.util.ArrayList;

public class Toolkit extends Item {
    private ArrayList<Item>tools;


    public Toolkit(String name, String type, String id, String description) {
        super(name, type, id, description);
        this.tools = new ArrayList<>();
    }

    @Override
    public String toString() {
        return super.toString() + " | Tools: " + this.tools;
    }
}
