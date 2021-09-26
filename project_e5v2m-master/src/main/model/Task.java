package model;

import org.json.JSONObject;
import persistence.Writable;

//Represent a task having a name, description, due in days, properties and tags and finished/Not finished status.
public class Task implements Writable {
    private String name;   // task name
    private String description;  // details of the task
    private int dueInDays;  // due with in how many days
    private boolean isUrgent; // mark down if it is urgent to be done
    private String tags; // task tag allows users quickly find it

    //MODIFIES: this
    //EFFECTS: constructor; a task has given name, description
    // properties, due in days, tags and is not finished
    //throw an exception when taskDueDays is a negative value
    public Task(String taskName, String taskDescription, boolean taskIsUrgent,
                int taskDueInDays,  String taskTags) throws InvalidInputException {
        name = taskName;
        description = taskDescription;
        isUrgent =  taskIsUrgent;
        if (taskDueInDays <= 0) {
            throw new InvalidInputException();
        }
        dueInDays = taskDueInDays;
        tags = taskTags;
    }

    //EFFECTS: all following 11 methods are getters and setters
    public String getName() {
        return this.name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String newDesctiption) {
        this.description = newDesctiption;
    }

    public int getDueInDays() {
        return this.dueInDays;
    }

    public void setDueInDays(int newDueInDays) throws InvalidInputException {
        if (newDueInDays <= 0) {
            throw new InvalidInputException();
        }
        this.dueInDays = newDueInDays;
    }

    public boolean getIsUrgent() {
        return this.isUrgent;
    }

    public void setIsUrgent(boolean newIsUrgent) {
        this.isUrgent = newIsUrgent;
    }

    public String getTags() {
        return this.tags;
    }

    public void setTags(String newTag) {
        this.tags = newTag;
    }

    //EFFECTS: get string of each task
    public String toString() {
        String taskStr = "Name: " + getName()
                + "\nDescription: " + getDescription()
                + "\nDue in: " + getDueInDays() + " day(s) "
                + "\nImportant Level:" + getIsUrgent()
                + "\nTag:" + getTags();
        return taskStr;
    }

    //partially copied from toJson(method)->Thingy(class)->JsonSerializationDemo(project)
    //EFFECTS:overrides toJson method from Writable interface,
    // details to name, description, dueInDays, isUrgent,tags, isFinished of every task and finally returns json.
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("description",description);
        json.put("dueInDays",dueInDays);
        json.put("isUrgent",isUrgent);
        json.put("tags",tags);
        return json;
    }

}

