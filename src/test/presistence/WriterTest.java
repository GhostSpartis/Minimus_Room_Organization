package presistence;

import model.Room;
import model.Item;
import presistence.JsonWriter;
import presistence.JsonReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class WriterTest {

    @Test
    void testInvalidFile() {
        try {
            Room rm = new Room();
            JsonWriter writerTest = new JsonWriter("./data/test/Written/MTRoom.json");
            writerTest.open();
            fail("Failed to Find Room");
        } catch(IOException e){
            //pass
        }
    }


    @Test
    void testWriterEmptyRoom () {
        try {
            JsonReader reader = new JsonReader();
            Room rm = new Room();
            JsonWriter writerTest = new JsonWriter("./data/testWrittenMTRoom.json");
            writerTest.open();
            writerTest.write(rm);
            writerTest.close();

            rm = reader.readRoom("./data/testWrittenMTRoom.json");
            assertEquals(0, rm.getNumberOfItemsInRoom());
        } catch(IOException e){
            fail("Failed to Find Room");
        }
    }

    @Test
    void testWriterOneItemRoom() {
        try {
            JsonReader reader = new JsonReader();
            Room rm = new Room();
            Item itm = new Item("TestItem", "TestCat",1,1,true,
                    "testdesc");
            rm.addItem(itm);
            JsonWriter writerTest = new JsonWriter("./data/testWrittenMTRoom.json");
            writerTest.open();
            writerTest.write(rm);
            writerTest.close();

            rm = reader.readRoom("./data/testWrittenMTRoom.json");
            assertEquals(1, rm.getNumberOfItemsInRoom());
            assertEquals("TestItem", rm.getItem(0).getName());
        } catch(IOException e){
            fail("Failed to Find Room");
        }
    }

    @Test
    void testWriterMultiItemRoom() {
        try {
            JsonReader reader = new JsonReader();
            Room rm = new Room();
            Item itm1 = new Item("TestItem1", "TestCat",1,1,true,
                    "testdesc");
            Item itm2 = new Item("TestItem2", "TestCat",1,1,true,
                    "testdesc");
            rm.addItem(itm1);
            rm.addItem(itm2);
            JsonWriter writerTest = new JsonWriter("./data/testWrittenMTRoom.json");
            writerTest.open();
            writerTest.write(rm);
            writerTest.close();

            rm = reader.readRoom("./data/testWrittenMTRoom.json");
            assertEquals(2, rm.getNumberOfItemsInRoom());
            assertEquals("TestItem1", rm.getItem(0).getName());
            assertEquals("TestItem2", rm.getItem(1).getName());
        } catch(IOException e){
            fail("Failed to Find Room");
        }
    }
}
