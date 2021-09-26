package persistence;

import model.InvalidInputException;
import model.Task;
import model.ToDoList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

//partially copied from JsonReader(class)->persistence(package)->JsonSerializationDemo(project)
// EFFECTS: Represents a reader that reads ToDoList from JSON data stored in file
public class JsonReader {
    private String dataStore = "./data/JsonStoreForJsonRW.json";

    // MODIFIES:this
    // EFFECTS: constructs reader to read from dataStore file
    public JsonReader(String dataStore) {
        this.dataStore = dataStore;
    }

    // EFFECTS: reads ToDoList from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ToDoList read() throws IOException, InvalidInputException {
        String jsonData = readFile(dataStore);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseToDoList(jsonObject);
    }

    // EFFECTS: reads dataStore file as string and returns it
    private String readFile(String dataStore) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(dataStore), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses ToDoList from JSON object and returns it
    private ToDoList parseToDoList(JSONObject jsonObject) throws InvalidInputException {
        String name = jsonObject.getString("name");
        ToDoList l = new ToDoList(name);
        addTasks(l, jsonObject);
        return l;
    }

    // MODIFIES: l
    // EFFECTS: parses thingies from JSON object and adds them to ToDoList
    private void addTasks(ToDoList l, JSONObject jsonObject) throws InvalidInputException {
        JSONArray jsonArray = jsonObject.getJSONArray("tasks");
        for (Object json : jsonArray) {
            JSONObject nextTask = (JSONObject) json;
            add(l, nextTask);
        }
    }

    // MODIFIES: l
    // EFFECTS: parses Task from JSON object and adds it to ToDoList
    private void add(ToDoList l, JSONObject jsonObject) throws InvalidInputException {
        String taskName = jsonObject.getString("name");
        String taskDescription = jsonObject.getString("description");
        boolean taskIsUrgent = jsonObject.getBoolean("isUrgent");
        int taskDueInDays = jsonObject.getInt("dueInDays");
        String taskTags = jsonObject.getString("tags");
        Task task = new Task(taskName, taskDescription,
                taskIsUrgent, taskDueInDays, taskTags);
        l.add(task);
    }
}
