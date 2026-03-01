package Command;

import Items.Item;
import Logic.GameData;
import Objects.Player;
import Objects.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DropDownTest {
    Player player;
    Item item;
    Room room;
    DropDown dropDown;
    GameData gameData;

    @BeforeEach
    void setUp() {
        player = new Player("Marcus", null, true,0,0,1 ,null);
        room = new Room("A", "a", "a", true, 0);
        item = new Item("Item","Item","item","item of the item the itemest item of the all");

        gameData = new GameData();

        gameData.getRooms().add(room);
        player.setCurrRoom(room);
        player.pickUp(item);

        dropDown = new DropDown(player);
    }

    @Test
    void execute() {
        dropDown.execute("item");
        assertTrue(player.getCurrRoom().getItems().contains(item));
        assertFalse(player.hasItem("item"));
    }
}