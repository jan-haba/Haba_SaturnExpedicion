package Items;

import java.util.ArrayList;

public class Toolkit extends Item {
    private ArrayList<Item>tools =  new ArrayList<>();
    private ArrayList<String>toolsRaw;

    public Toolkit(String name, String type, String id, String description, ArrayList<String> toolsRaw) {
        super(name, type, id, description);
        this.toolsRaw = toolsRaw;
    }

    public void addItem(Item item) {
        tools.add(item);
    }

    public ArrayList<String> getToolsRaw() {
        return toolsRaw;
    }

    @Override
    public String toString() {
        return super.toString() + " | Tools: " + this.tools;
    }
}

