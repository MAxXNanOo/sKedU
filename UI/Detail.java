package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Detail extends JPanel{
    private String Id;
    private String Name;


    public Detail(String Id, String Name){
        this.Id = Id;
        this.Name = Name;

        setLayout(null);
        JPanel sidebar = new JPanel();
        sidebar.setBounds(0, 0, 320, 160);
        sidebar.setOpaque(true);
        sidebar.setBackground(Color.cyan);



        add(sidebar);
    }
}
