package Objects;

import Items.Item;
import java.util.ArrayList;

/**
 * Class for Storage Rack
 */
public class Storage extends GameObject{
    private ArrayList<Item> items;

    public Storage(String name, String id, String description, ArrayList<Item> items) {
        super(name, id, description);
        this.items = items;
    }

    /**
     * Method that gets Item from Storage
     * @param itemName name of the Item we wanna get
     * @return Item we wanna get
     */
    public Item findItem(String itemName){
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            if (item.getName().equalsIgnoreCase(itemName)){
                items.remove(i);
                return item;
            }
        }
        return null;
    }

    /**
     * Method that helps put Items inside Storage Rack
     * @param item Item we wanna put inside
     * @return message that we put Item inside
     */
    public String putItem(Item item){
        if (item == null){
            return "Item doesnt exist";
        }
        this.items.add(item);
        return item.getName() + " stored in " + getName();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== ").append(name.toUpperCase()).append(" ===\n");
        sb.append("Description: ").append(description).append("\n");
        sb.append("Content: ");

        if (items == null || items.isEmpty()) {
            sb.append("Empty");
        } else {
            sb.append("\n");
            for (Item item : items) {
                sb.append("  - ").append(item.getName()).append("\n");
            }
        }
        return sb.toString();
    }
}
