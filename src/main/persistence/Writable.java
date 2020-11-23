package persistence;
// from CPSC 210 EdX JsonSerializationDemo

import org.json.JSONObject;

//// SOURCE: ////
// Save / Load System file structure based on example system JsonSerializationDemo provided by UBC CPSC 210 course
// This interface was sourced wholesale from this example

// Represents a data type that is able to be saved to a Json file
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
