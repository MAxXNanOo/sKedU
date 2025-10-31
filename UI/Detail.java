package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import Subject.*;

public class Detail extends JPanel{


    public Detail(StudentData studentData){

        setLayout(null);
        JPanel sidebar = new JPanel();
        sidebar.setBounds(0, 0, 320, 160);
        sidebar.setOpaque(true);
        sidebar.setBackground(Color.cyan);



        add(sidebar);
    }
}
