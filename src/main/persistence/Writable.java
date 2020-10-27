package persistence;
// from CPSC 210 EdX JsonSerializationDemo

import org.json.JSONObject;

public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
