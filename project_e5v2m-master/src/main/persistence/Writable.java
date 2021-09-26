package persistence;

import org.json.JSONObject;

//partially copied from Writable(method)->Writable(Interface)->JsonSerializationDemo(project)
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
