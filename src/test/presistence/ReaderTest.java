package presistence;

import model.Item;
import model.Room;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class ReaderTest {

    @Test
    void testRoomFileDNE() throws IOException {
        JsonReader reader = new JsonReader();
        try {
            Room rm = reader.readRoom("./data/noSuchFile.json");
            fail("IOException expected");
        } catch (IOException e) {
        }
    }

    @Test
    void testReaderEmptyWorkRoom() throws IOException{
        JsonReader reader = new JsonReader();
        try {
            Room rm = reader.readRoom("./data/testReaderEmptyRoom.json");
            assertEquals(0, rm.getNumberOfItemsInRoom());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderWithOneItem() throws IOException{
        JsonReader reader = new JsonReader();
        try {
            Room rm = reader.readRoom("./data/testReaderOneItem.json");
            assertEquals(1, rm.getNumberOfItemsInRoom());
            assertEquals("TestItem", rm.getItem(0).getName());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }


    @Test
    void testReaderWithMultipleItems() throws IOException{
        JsonReader reader = new JsonReader();
        try {
            Room rm = reader.readRoom("./data/testReaderMultipleItems.json");
            assertEquals(2, rm.getNumberOfItemsInRoom());
            assertEquals("TestItem1", rm.getItem(0).getName());
            assertEquals("TestItem2", rm.getItem(1).getName());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testIOException() {
        JsonReader reader = new JsonReader();
        try {
            reader.readRoom("./path/not/real/path/testCarListings.txt");
        } catch (IOException e) {
            // expected
        }
    }



}