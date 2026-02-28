package Items;

import Objects.Player;

import java.util.ArrayList;

/**
 * Represents a toolkit or container item that can hold other items (tools).
 * Players can examine the toolkit to see its contents and extract items from it.
 */
public class Toolkit extends Item {
    private ArrayList<Item>tools =  new ArrayList<>();
    private ArrayList<String>toolsRaw;

    public Toolkit(String name, String type, String id, String description, ArrayList<String> toolsRaw) {
        super(name, type, id, description);
        this.toolsRaw = toolsRaw;
    }

    /**
     * Executes an interaction with the toolkit, which attempts to extract a specific tool from it.
     * @param player the player interacting with the toolkit
     * @param string the name of the tool the player wants to extract
     * @return the extracted Item, or null if it wasn't found inside
     */
    @Override
    public Item execute(Player player, String string) {
        return getItem(string);
    }
    @Override
    public String getDescription() {
        StringBuilder sb = new StringBuilder(super.getDescription());
        if (this.tools == null || this.tools.isEmpty()) {
            sb.append("\nIt is currently empty.");
        } else {
            sb.append("\nInside you can see:");
            for (Item tool : this.tools) {
                sb.append("\n - ").append(tool.getName());
            }
        }
        return sb.toString();
    }

    /**
     * Adds an item (tool) into the toolkit.
     * @param item the item to be added
     */
    public void addItem(Item item) {
        tools.add(item);
    }

    /**
     * Searches for a tool by name inside the toolkit. If found, removes it from
     * the toolkit and returns it.
     * @param name the name of the tool to retrieve
     * @return the requested tool, or null if it's not inside
     */
    public Item getItem(String name) {
        for (Item item : tools) {
            if (item.getName().equalsIgnoreCase(name)) {
                Item tool = item;
                tools.remove(item);
                return tool;
            }
        }
        return null;
    }

    public ArrayList<String> getToolsRaw() {
        return toolsRaw;
    }

    @Override
    public String toString() {
        return super.toString() + " | Tools: " + this.tools;
    }
}