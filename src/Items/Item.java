package Items;

import Objects.Player;

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

    public String execute(Player player, String string){
        return "this item cant be used";
    }
    public String getDescription(){
        return description;
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
