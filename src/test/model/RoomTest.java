package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class RoomTest {

    private Room roomTest1;
    private Room roomTest2;
    private Item item;
    private Item item2;

    @BeforeEach
    public void runBefore() {

        roomTest1 = new Room();
        roomTest2 = new Room();
        item = new Item("Laptop","Electronics", 1, 1200, true,
                "test description about how cool and stuff the item is");
        item2 = new Item("The Lorax life-size statue","Collectibles", 10, 10000,
                false, "impossibly valuable");
    }

    @Test
    public void testAddItem() {

        roomTest1.addItem(item);
        assertTrue(roomTest1.hasItem(item));
    }

    @Test
    public void testNumberOfItemsInRoom() {

        roomTest1.addItem(item);
        assertEquals(1, roomTest1.getNumberOfItemsInRoom());
        roomTest1.addItem(item);
        assertEquals(2, roomTest1.getNumberOfItemsInRoom());
    }

    @Test
    public void testRemoveItemFromRoom() {

        roomTest1.addItem(item);
        roomTest1.addItem(item2);
        roomTest1.removeItem(item);
        assertFalse(roomTest1.hasItem(item));
    }

    @Test
    public void testItemLocator() {

        roomTest1.addItem(item2);
        roomTest1.addItem(item2);
        roomTest1.addItem(item);
        roomTest1.addItem(item2);
        roomTest1.addItem(item2);
        assertEquals(item, roomTest1.getItem(2));

    }

    @Test
    public void testRoomDisplay() {

        roomTest1.addItem(item);
        roomTest1.addItem(item2);
        assertEquals(roomTest1.getItems(),roomTest1.getItems());
    }
}
