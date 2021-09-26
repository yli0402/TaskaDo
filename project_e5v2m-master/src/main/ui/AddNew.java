package ui;

import model.InvalidInputException;
import model.Task;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

//pop up window when adding
public class AddNew extends JDialog implements PropertyChangeListener {
    private JLabel name;
    private JTextField nameT = new JTextField(20);

    private JLabel description;
    private JTextField descriptionT = new JTextField(10);

    private JLabel dueInDays;
    private JTextField dueInDaysT = new JTextField(5);

    private JLabel isUrgent;
    private JTextField isUrgentT = new JTextField(20);

    private JLabel tags;
    private JTextField tagsT = new JTextField(10);

    private JOptionPane optionPane;
    private Task task;


    //EFFECTS: set labels
    private void labelSet() {
        name = new JLabel("New task name: ");
        description = new JLabel("Description: ");
        dueInDays = new JLabel("How many days until it be due?: ");
        isUrgent = new JLabel("Is this an urgent?(y/n): ");
        tags = new JLabel("Tag: ");
//        isFinished = new JLabel("Is this finished?(y/n): ");
//        nameT.addActionListener(this);
//        descriptionT.addActionListener(this);
//        dueInDaysT.addActionListener(this);
//        isUrgentT.addActionListener(this);
//        tagsT.addActionListener(this);
    }

    // EFFECTS: a window to add a new task
    public AddNew(Window parent, String title, Task task) {
        super(parent, title, ModalityType.APPLICATION_MODAL);
        labelSet();
        Object[] elements = {"Please enter the task detail.", name, nameT, description,
                descriptionT, dueInDays, dueInDaysT,
                isUrgent,isUrgentT,tags,tagsT};
        optionPane = new JOptionPane(elements,
                JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION, null, new String[]{"Cancel", "Add"},
                "Add");
        optionPane.addPropertyChangeListener(this);
        setContentPane(optionPane);
        pack();
        this.task = task;
    }

    // EFFECTS: create a task when "Add" is been clicked.
    // If the input of days is negative, then Exception has been catch.
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String prop = evt.getPropertyName();
        if (!JOptionPane.VALUE_PROPERTY.equals(prop) && !JOptionPane.INPUT_VALUE_PROPERTY.equals(prop)) {
            return;
        }
        if (optionPane.getValue().equals("Add")) {
            try {
                addTask();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        dispose();
    }

    // EFFECTS: create new task.
    //If the input of days is negative, then InvalidInputException has been catch.
    public void addTask() {
        try {
            boolean ugt = false;
            if (isUrgentT.getText().toLowerCase().charAt(0) == 'y') {
                ugt = true;
            }
            if (task == null) {
                task = new Task(nameT.getText(),descriptionT.getText(),
                        ugt, Integer.parseInt(dueInDaysT.getText()), tagsT.getText());
            } else {
                setTask(ugt);
            }
        } catch (InvalidInputException e) {
            JOptionPane.showMessageDialog(this, "Task has been added unsuccessfully:( \n"
                    + "Because you put a invalid 'days' value. ");
            return;
        }
    }

    //EFFECTS: a helper of method "addTask"
    private void setTask(boolean ugt) throws InvalidInputException {
        task.setName(nameT.getText());
        task.setDescription(descriptionT.getText());
        task.setDueInDays(Integer.parseInt(dueInDaysT.getText()));
        task.setIsUrgent(ugt);
        task.setTags(tagsT.getText());
    }

    //EFFECTS: get a task
    public Task getTask() {
        return task;
    }
}
