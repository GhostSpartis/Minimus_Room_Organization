package presistence;

import org.json.JSONObject;

//borrowed from JsonSerializationDemo https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}

