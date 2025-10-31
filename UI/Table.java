package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import Subject.*;

public class Table extends JPanel{
    private String Id;
    private String Name;


    public Table(StudentData studentData){

        setLayout(null);
        JPanel background = new JPanel();
        background.setBounds(0, 0, 1080, 400);
        background.setOpaque(true);
        background.setBackground(Color.gray);


        add(background);
    }
}