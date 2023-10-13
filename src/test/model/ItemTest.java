package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    private Item testItem1;
    private Item testItem2;

    @BeforeEach
    public void runBefore() {

        testItem1 = new Item("Laptop", "Electronics", 10, 1200, false,
                "Useful for coding, note-taking and entertainment");
        testItem2 = new Item("Nord Pop Figure", "Collectibles", 1, 12, true,
                "Bought During childhood, holds no practical use, no sentimental value");
    }

    @Test
    public void testItemCreation() {

        assertEquals("Laptop", testItem1.getName());
        assertEquals("Electronics", testItem1.getCategory());
        assertEquals(10, testItem1.getRating());
        assertEquals(1200, testItem1.getPriceInCAD());
        assertFalse(testItem1.isForSale());
        assertEquals("Useful for coding, note-taking and entertainment", testItem1.getDescription());
    }

    @Test
    public void testMultipleItems() {

        assertEquals("Laptop", testItem1.getName());
        assertEquals("Nord Pop Figure", testItem2.getName());
    }

}