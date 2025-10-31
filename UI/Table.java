package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import Subject.*;

public class Table extends JPanel{
    private String Id;
    private String Name;


    public Table(String Id, String Name, StudentData studentData){

        setLayout(null);
        JPanel background = new JPanel();
        background.setBounds(0, 0, 600, 220);
        background.setOpaque(true);
        background.setBackground(Color.cyan);


        add(background);
    }
}