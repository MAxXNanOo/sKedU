package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import Subject.*;

//used vs Copilot
public class Table extends JPanel{
    private Student student;


    public Table(StudentData studentData, Data data){
        this.student = studentData.getStudentLogin();

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


            JPanel filter = new JPanel();
            grid.gridx = 1;
            grid.gridy = 2;
            grid.gridwidth = 11;
            grid.gridheight = 5;
            filter.setOpaque(false);
            filter.setBackground(Color.white);


                //----------------------------------------------
                for(Subject sub : student.getSubjects()){
                    for(CourseComponent comp : sub.getAllLab()){
                        int index = 0;
                        for(String dayTime : comp.getDayTimes()){
                            String day = comp.getDays().get(index);
                            Double start = comp.getStarts().get(index);
                            Double end = comp.getEnds().get(index);
                            String room = comp.getRooms().get(index);

                            boxPanel box = new boxPanel(sub.getName() + " Lab (" + room + ")", new Color(144,238,144));
                            int weight = (int)((end - start) * 100);
                            int height = 90;
                            int xPos = 125 + (int)((start - 8) * 100);
                            int yPos;

                            if(day.equals("วันจันทร์")){
                                yPos = 75;
                            }
                            else if(day.equals("วันอังคาร")){
                                yPos = 75 + 90;
                            }
                            else if(day.equals("วันพุธ")){
                                yPos = 75 + 90 * 2;
                            }
                            else if(day.equals("วันพฤหัสบดี")){
                                yPos = 75 + 90 * 3;
                            }
                            else if(day.equals("วันศุกร์")){
                                yPos = 75 + 90 * 4;
                            }
                            else{
                                yPos = 75 + 90 * 5;
                            }

                            box.setBounds(xPos, yPos, weight, height);
                            filter.add(box);
                            index++;
                        }
                    }
                }

            background.add(filter, grid);

        add(background);
    }

    public class boxPanel extends JPanel{
        public boxPanel(String text, Color color){
            setOpaque(true);
            setBackground(color);
            JLabel label = new JLabel(text);
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setFont(new Font("Arial", Font.BOLD, 14));
            add(label);
        }
    }
}