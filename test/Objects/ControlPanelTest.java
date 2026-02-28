package Objects;

import Logic.GameData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ControlPanelTest {
    Player player;
    ControlPanel controlPanel;
    Room room;
    GameData gameData;

    @BeforeEach
    void setUp() {
        player = new Player("Marcus", null, true,0,0,1);
        room = new Room("A", "a", "a", true, 0);
        controlPanel = new ControlPanel("panel","panel","panel",false);
        gameData = new GameData();


        room.addObject(controlPanel);
        gameData.getRooms().add(room);
        player.setCurrRoom(room);

    }

    @Test
    void execute() {
        assertFalse(controlPanel.isActivated());
        assertEquals("You flipped the heavy switches. The Control Panel hums to life.\n" +
                "*** Power has been restored to Escape Module 2! ***", controlPanel.execute(player));
        assertTrue(controlPanel.isActivated());

    }
}