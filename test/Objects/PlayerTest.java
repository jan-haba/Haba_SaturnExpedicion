package Objects;

import Items.Suit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    Player player;

    @BeforeEach
    void setUp() {
        player = new Player("Marcus", null,true);
    }
    @Test
    void hasSuit() {
        assertFalse(player.hasSuit());
        player.pickUp(new Suit("Suit", "Suit","suit","its a suit"));
        assertTrue(player.hasSuit());
    }
}