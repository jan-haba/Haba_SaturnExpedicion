package Items;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {
    Item item;

    @BeforeEach
    void setUp() {
        item = new Item("a","a","a","aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
    }

    @Test
    void getName() {
        assertEquals("a", item.getName());
    }
}