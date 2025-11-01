package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import Subject.*;

public class Search extends JPanel{


    public Search(StudentData studentData, Data data){

        setLayout(null);
        JPanel background = new JPanel();
        background.setBounds(0, 0, 600, 220);
        background.setOpaque(true);
        background.setBackground(Color.cyan);


        add(background);
    }
}