package Command;

import Objects.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TimeTest {
    Player player;
    Time time;

    @BeforeEach
    void setUp() {
        player = new Player("Markus",null,true,0,361,67);
        time = new Time(player);
    }

    @Test
    void execute() {
        String result = time.execute(null);
        assertTrue(result.contains("06:01"));
        assertTrue(result.contains("67"));
    }
}