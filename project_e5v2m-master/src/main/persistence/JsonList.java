package persistence;

import model.InvalidInputException;
import model.Task;
import model.ToDoList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

//partially copied from JsonReader(class)->persistence(package)->JsonSerializationDemo(project)
//load and save data to json
public class JsonList {
    private final String dataStore = "./data/ToDoList.json";
    private ToDoList todoList;

    //Effect: set a new list
    public void setTodoLists(ToDoList todoLists) {
        this.todoList = todoLists;
    }

    //Effect: get the list
    public ToDoList getTodoList() {
        return todoList;
    }

    //Effect: save file
    public static void savetoFile(JSONObject obj, String file) throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter(file));
        pw.print(obj);
        pw.close();
    }

    //Effect: save the todoList data.
    public void saveData() throws IOException {
        JSONObject myListJson = new JSONObject();
        JSONArray tasks = new JSONArray();
        for (Task task: todoList.getTasks()) {
            JSONObject t1 = new JSONObject();
            t1.put("name", task.getName());
            t1.put("description", task.getDescription());
            t1.put("isUrgent", task.getIsUrgent());
            t1.put("dueInDays", task.getDueInDays());
            t1.put("tags", task.getTags());
            tasks.put(t1);
        }
        myListJson.put("tasks", tasks);
        savetoFile(myListJson,dataStore);
    }

    //Effect: read data from json.
    public static String readData(String filepath) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filepath)));
        String strLine;
        while (true) {
            strLine = br.readLine();

            if (strLine == null) {
                break;
            }
            sb.append(strLine + "\n");
        }
        br.close();
        return sb.toString();
    }

    //Effect: load the list. If the input of days is negative, then InvalidInputException has been catch.
    public void loadData() throws IOException {
        String data = readData(dataStore);
        JSONObject jsonTodoList = new JSONObject(data);
        JSONArray tasksJson = jsonTodoList.getJSONArray("tasks");
        List<Task> l = new ArrayList<>();
        for (int j = 0; j < tasksJson.length(); j++) {
            JSONObject obj1 = tasksJson.getJSONObject(j);
            try {
                Task task = new Task(obj1.getString("name"), obj1.getString("description"),
                        obj1.getBoolean("isUrgent"),  obj1.getInt("dueInDays"), obj1.getString("tags"));
                l.add(task);
            } catch (InvalidInputException e) {
                e.printStackTrace();
            }
        }
        ToDoList todoList = new ToDoList("myTaskado");
        todoList.setTasksInList(l);
        this.todoList = todoList;
    }
}