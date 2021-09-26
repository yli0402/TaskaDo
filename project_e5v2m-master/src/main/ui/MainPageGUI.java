package ui;

import model.Task;
import model.ToDoList;
import persistence.JsonList;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

//set up main functions of GUI in main page
public class MainPageGUI extends JPanel implements ActionListener {
    private static final String JSON_STORE = "./data/todolist.json";
    private ToDoList toDoList;
    private JsonList reader;
    private JTextArea text;
    private JButton saveButton = new JButton("Save the application Data");
    private JButton loadButton = new JButton("Load the application Data");
    private JButton addButton   = new JButton("Create a Task");
    private JButton deleteButton = new JButton("Delete a Task");

    // EFFECTS: set buttons panels
    public MainPageGUI(ToDoList todolist, JTextArea text) {
        reader = new JsonList();
        this.toDoList = todolist;
        this.text = text;
        addButton.addActionListener(this);
        add(addButton);
        saveButton.addActionListener(this);
        add(saveButton);
        loadButton.addActionListener(this);
        add(loadButton);
        deleteButton.addActionListener(this);
        add(deleteButton);
    }

    @Override
    // EFFECTS: invoke the functions to handle the action.
    //If the error comes up, then Exception has been catch.
    public void actionPerformed(ActionEvent event) {
        if ((JButton)event.getSource() == addButton) {
            addTask();
        } else if ((JButton)event.getSource() == deleteButton) {
            deleteTask();
        } else if ((JButton)event.getSource() == saveButton) {
            try {
                helper1();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if ((JButton)event.getSource() == loadButton) {
            try {
                reader.loadData();
            } catch (Exception e) {
                e.printStackTrace();
            }
            helper2();
        }
    }

    //EFFECTS: a helper of method "actionPerformed"
    private void helper1() throws IOException {
        reader.setTodoLists(toDoList);
        reader.saveData();
    }

    //EFFECTS: a helper of method "actionPerformed"
    private void helper2() {
        toDoList = reader.getTodoList();
        text.setText(getTaskString(toDoList));
    }

    // EFFECTS: print the text of the task
    private String getTaskString(ToDoList toDoList) {
        String text = "";
        int index = -1;
        for (Task t: toDoList.getTasks()) {
            index += 1;
            text +=  "Task Index: " + index + "  Name: " + t.getName() + "  Description:" + t.getDescription()
                    + " Due in days: " + t.getDueInDays()
                    + " Is urgent: " + t.getIsUrgent()
                    + " tags: " + t.getTags() + "\n\n";
        }
        return text;
    }

    // EFFECTS: create a new task.
    private void addTask() {
        Window window = SwingUtilities.windowForComponent(MainPageGUI.this);
        AddNew add1 = new AddNew(window, "Create task", null);
        add1.setVisible(true);
        add1.setLocationRelativeTo(window);
        if (add1.getTask() == null) {
            return;
        }
        toDoList.add(add1.getTask());
        text.setText(getTaskString(toDoList));
    }



    // EFFECTS: delete a task
    private void deleteTask() {
        int id = getInputID();
        if (id != -1 && id < toDoList.getTasks().size()) {
            Task task = toDoList.getTasks().get(id);
            if (task == null) {
                text.setText("Please enter a valid ID.");
                return;
            }
            toDoList.delete(id);
            text.setText(getTaskString(toDoList));
            deletePicture();
        }
        return;
    }

    // EFFECTS: show a picture after deleting
    private void deletePicture() {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("./data/delete.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        JFrame frame  = new JFrame();
        frame.setLayout(new FlowLayout());
        frame.setSize(800,650);
        JLabel lbl = new JLabel();
        lbl.setIcon(new ImageIcon(image));
        frame.setVisible(true);
        frame.add(lbl);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    // EFFECTS: get id input in order to delete task
    private int getInputID() {
        String idStr = JOptionPane.showInputDialog(new JFrame(),"Input #id to be deleted: ");
        return Integer.parseInt(idStr);
    }
}