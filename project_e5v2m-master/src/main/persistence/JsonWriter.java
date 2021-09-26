package persistence;

import org.json.JSONObject;
import model.ToDoList;


import java.io.*;

//partially copied from JsonWriter(class)->persistence(package)->JsonSerializationDemo(project)
// Represents a writer that writes JSON representation of ToDoList to file
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String target;

    // MODIFIES: this
    // EFFECTS: constructs writer to write to target file
    public JsonWriter(String target) {
        this.target = target;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if target file cannot
    // be opened for writing.
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(target));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of ToDoList to file
    public void write(ToDoList l) {
        JSONObject json = l.toJson();
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }
}
