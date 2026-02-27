package Command;

import Logic.GameData;
import Objects.Player;
import Objects.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GoToTest {
    Player player;
    Room room1;
    Room room2;
    GoTo goTo;
    GameData gameData;


    @BeforeEach
    void setUp() {
        player = new Player("Marcus", null, true);
        room1 = new Room("A", "a", "a", true, 0);
        room2 = new Room("B", "b", "b", true, 0);

        gameData = new GameData();

        room1.getExits().add("B");
        room2.getExits().add("A");

        gameData.getRooms().add(room1);
        gameData.getRooms().add(room2);

        player.setCurrRoom(room1);
        goTo = new GoTo(player, gameData);
    }

    @Test
    void execute() {
        goTo.execute("B");
        assertEquals("B",player.getCurrRoom().getName());
    }
}