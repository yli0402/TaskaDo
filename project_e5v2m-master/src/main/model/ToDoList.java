package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//a list that contains tasks
public class ToDoList implements Writable {
    private List<Task> myList;
    private String name;

    //MODIFIES: this
    //EFFECTS: initializes name and each newly created list as an empty queue.
    public ToDoList(String name) {
        this.name = name;
        myList = new ArrayList<Task>();
    }

    //EFFECTS: gets and returns name
    public String getName() {
        return this.name;
    }

    //MODIFIES: this
    //EFFECTS: add new tasks to this ArrayList
    public void add(Task task) {
        myList.add(task);
    }

    //EFFECTS: view all tasks of this ArrayList
    public boolean viewAll() {
        if (myList.size() == 0) {
            System.out.println("You have nothing in your ToDoList");
            return false;
        } else {
            int counter = 1;
            for (Task task:myList) {
                System.out.println(task);
                counter++;
            }
            System.out.println();
            return true;
        }
    }

    //REQUIRES: my list is not empty
    //MODIFIES: this
    //EFFECTS: delete tasks from list
    public void delete(int index) {
        myList.remove(index);
    }

    //EFFECTS: return length of list
    public int length() {
        return myList.size();
    }

    // EFFECTS: returns an unmodifiable list of tasks in this to-do list
    public List<Task> getTasks() {
        return Collections.unmodifiableList(myList);
    }

    //MODIFIES: this
    // EFFECTS: set a new todoList
    public void setTasksInList(List<Task> tasksInList) {
        this.myList = tasksInList;
    }

    //partially copied from toJson(method)->WorkRoom(class)->JsonSerializationDemo(project)
    //EFFECTS: overrides toJson method from Writable interface,  puts JSON array as JSON object
    // and returns Json.
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("tasks", tasksToJson());
        return json;
    }

    //partially copied from toJson(method)->WorkRoom(class)->JsonSerializationDemo(project)
    // EFFECTS: returns tasks in this ToDoList as a JSON array
    private JSONArray tasksToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Task t : myList) {
            jsonArray.put(t.toJson());
        }

        return jsonArray;
    }

}
