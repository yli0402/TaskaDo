package ui;

import model.ToDoList;

import javax.swing.*;
import java.awt.*;

//designs outlook of GUI
public class TaskadoGUI extends JFrame {
    private JLabel label;
    private JTextArea displayJ;
    private ToDoList todolist;

    public TaskadoGUI() {
        todolist = new ToDoList("myTaskado");
        initialGUI();
    }

    //EFFECT: initialize the graphic ui
    private void initialGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setSize(900, 800);
        displayJ = new JTextArea();
        myBg();
        myImage();
        myTitle();
        displayJ.setPreferredSize(new Dimension(1000, 80));
        Font ft = new Font("TimesRoman",Font.BOLD, 20);
        displayJ.setFont(ft);
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        getContentPane().add(displayJ);
        getContentPane().add(new MainPageGUI(todolist, displayJ));
        pack();
        setVisible(true);
        add(label);
    }

    //EFFECTS: set up my background of my main interface
    void myBg() {
        getContentPane().setBackground(new java.awt.Color(90, 58, 125, 122));
    }

    //EFFECTS: set a title
    private void myTitle() {
        label = new JLabel("TaskaDo -> Make Your Day-To-Day Life Easier!", SwingConstants.CENTER);
        label.setBounds(50,60,900,30);
        label.setFont(new Font("TimesRoman",Font.BOLD, 20));
        add(label);
    }

    //EFFECTS: set an image
    private void myImage() {
        JFrame frame = new JFrame();
        ImageIcon icon = new ImageIcon("./data/desk.jpeg");
        JLabel label2 = new JLabel(icon);
        frame.add(label2);
        frame.setVisible(true);
        label2.setBounds(100,40,150,57);
        add(label2);
    }

}
