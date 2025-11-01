package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import Subject.*;

//used vs Copilot
public class Table extends JPanel{
    StudentData studentData;
    public Table(StudentData studentData, Data data){
        this.studentData = studentData;

        setLayout(null);
        JPanel background = new JPanel();
        background.setBounds(0, 0, 1080, 400);
        background.setOpaque(true);
        background.setBackground(Color.lightGray);
            
            GridBagConstraints grid = new GridBagConstraints();
            background.setLayout(new GridBagLayout());
            grid.fill = GridBagConstraints.BOTH;
            grid.weightx = 1;
            grid.weighty = 1;

            grid.gridx = 0;
            grid.gridy = 0;
            grid.gridwidth = 12;
            JLabel label = new JLabel("Table");
            label.setOpaque(true);
            label.setBackground(new Color(100,149,237));
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setFont(new Font("Arial", Font.BOLD, 24));
            background.add(label, grid);

            grid.gridx = 0;
            grid.gridy = 1;
            grid.gridwidth = 1;
            JLabel section = new JLabel("Day / Time");
            section.setOpaque(true);
            section.setBackground(Color.gray);
            section.setHorizontalAlignment(SwingConstants.CENTER);
            section.setFont(new Font("Arial", Font.BOLD, 16));
            background.add(section, grid);

            for(int i=8 ; i<=18 ; i++){
                grid.gridx = i-7;
                grid.gridy = 1;
                JLabel time = new JLabel(i + ":00");
                time.setOpaque(true);
                time.setBackground(Color.gray);
                time.setFont(new Font("Arial", Font.BOLD, 16));
                background.add(time, grid);
            }
            String[] days = {"Mon","Tue","Wed","Thu","Fri"};
            for(int i=0 ; i<5 ; i++){
                grid.gridx = 0;
                grid.gridy = i+2;
                JLabel day = new JLabel(days[i]);
                day.setOpaque(true);
                day.setBackground(Color.gray);
                day.setFont(new Font("Arial", Font.BOLD, 16));
                day.setHorizontalAlignment(SwingConstants.CENTER);
                background.add(day, grid);
            }

        add(background);
    }  
}