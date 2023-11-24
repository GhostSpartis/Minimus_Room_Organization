package presistence;

import model.Item;
import model.Room;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

//borrowed from JsonSerializationDemo https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonReader {


    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Room readRoom(String source) throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseRoom(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses workroom from JSON object and returns it
    private Room parseRoom(JSONObject jsonObject) {
        Room room = new Room();
        addItems(room, jsonObject);
        return room;
    }

    // MODIFIES: room
    // EFFECTS: parses items from JSON object and adds them to the room
    private void addItems(Room r, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("items");
        for (Object json : jsonArray) {
            JSONObject nextItem = (JSONObject) json;
            addItem(r, nextItem);
        }
    }

    // MODIFIES: room
    // EFFECTS: parses an item from JSON object and adds it to the Room
    private void addItem(Room r, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String category = jsonObject.getString("category");
        int itmRating = jsonObject.getInt("rating");
        int price = jsonObject.getInt("price");
        Boolean saleStatus = jsonObject.getBoolean("on sale");
        String description = jsonObject.getString("description");
        Item item = new Item(name, category, itmRating, price,saleStatus,description);
        r.addItem(item);
    }
}
