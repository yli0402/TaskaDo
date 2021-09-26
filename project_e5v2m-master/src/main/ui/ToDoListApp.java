package ui;

import model.InvalidInputException;
import model.Task;
import model.ToDoList;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

//partially copied from ToDoListApp(class)->ui(package)->JsonSerializationDemo(project)
public class ToDoListApp {
    private static final String JSON_STORE = "./data/todolist.json";
    private Scanner input;
    private ToDoList toDoList;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    //MODIFIES: this
    //EFFECTS: constructor
    public ToDoListApp() throws FileNotFoundException, InvalidInputException {
        input = new Scanner(System.in);
        toDoList = new ToDoList("My TaskaDoList");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runToDoList();
    }

    //MODIFIES: this
    //EFFECTS: runs todolist
    private void runToDoList() throws InvalidInputException {
        boolean keep = true;
        String command = "";
        System.out.println("\nWelcome to use TaskaDo:)");

        while (keep) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keep = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> add");
        System.out.println("\td -> delete");
        System.out.println("\ts -> save it to file");
        System.out.println("\tl -> load it from file");
        System.out.println("\tv -> view all");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) throws InvalidInputException {
        if (command.equals("a")) {
            add();
        } else if (command.equals("d")) {
            delete();
        } else if (command.equals("s")) {
            saveToDoList();
        } else if (command.equals("l")) {
            loadToDoList();
        } else if (command.equals("v")) {
            toDoList.viewAll();
        }  else {
            System.out.println("Selection not valid...");
        }
    }

    //MODIFIES: this
    //EFFECTS: shows delete function in menu and calls delete method
    public void delete() {
        toDoList.viewAll();
        System.out.println("Which one do you want to delete?(enter the number here)");
        int myDelete = input.nextInt();
        toDoList.delete(myDelete - 1);
    }

    //MODIFIES: this
    //EFFECTS: shows add function in menu and calls add method
    public void add() throws InvalidInputException {
        String name1 = input.nextLine();
        System.out.println("What is the name of the NEW task: ");
        String name = input.nextLine();
        System.out.println("What is the description: ");
        String description = input.nextLine();
        System.out.println("How many days until it will be due? ");
        int days = input.nextInt();
        System.out.println("Is this an urgent? (y/n)");
        String urgent = input.next().toLowerCase();
        boolean isUrgent = false;
        if (urgent.equals("y")) {
            isUrgent = true;
        }
        System.out.println("What is the tag(one key word): ");
        String tag = input.next();
        Task task = new Task(name, description, isUrgent, days, tag);
        toDoList.add(task);
        System.out.println(name + " has been added successfully");
    }

    //partially copied from saveWorkRoom(method)->ToDoListApp(class)->ui(package)->JsonSerializationDemo(project)
    // EFFECTS: saves the ToDoList to file.
    private void saveToDoList() {
        try {
            jsonWriter.open();
            jsonWriter.write(toDoList);
            jsonWriter.close();
            System.out.println("Saved " + " to " + JSON_STORE);
            System.out.println("Congrats! The to-do List has been saved successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save file: " + JSON_STORE);
        }

    }

    //partially copied from loadWorkRoom(method)->ToDoListApp(class)->ui(package)->JsonSerializationDemo(project)
    // MODIFIES: this
    // EFFECTS: loads ToDoList from file
    public void loadToDoList() {
        try {
            toDoList = jsonReader.read();
            System.out.println("Loaded " + " from " + JSON_STORE);
            System.out.println("Congrats! The to-do List has been loaded successfully.");
        } catch (IOException | InvalidInputException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
