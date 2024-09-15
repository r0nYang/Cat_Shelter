package persistence;

import org.json.JSONObject;

// Represents an interface that provides an abstract toJson method
public interface Writable {
    //EFFECTS: returns this as a JSON object
    JSONObject toJson();
}
