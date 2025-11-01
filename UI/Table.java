package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.Border;
import Subject.*;

public class Table extends JPanel {
    private Student student;

    public Table(StudentData studentData, Data data) {
        this.student = studentData.getStudentLogin();

        setLayout(null);


        Border border = BorderFactory.createLineBorder(Color.black, 1);

        JPanel background = new JPanel();
        background.setBounds(0, 0, 1080, 400);
        background.setOpaque(true);
        background.setBackground(Color.lightGray);
        background.setBorder(border);

            GridBagConstraints grid = new GridBagConstraints();
            background.setLayout(new GridBagLayout());
            grid.fill = GridBagConstraints.BOTH;
            grid.weightx = 1;
            grid.weighty = 1;

            // ✅ ฟอนต์ภาษาไทย
            Font thaiFontTitle = new Font("Tahoma", Font.BOLD, 24);
            Font thaiFont = new Font("Tahoma", Font.BOLD, 16);
            Font thaiFontSmall = new Font("Tahoma", Font.BOLD, 14);

            // ---------- หัวตาราง ----------
            grid.gridx = 0;
            grid.gridy = 0;
            grid.gridwidth = 12;
            JLabel title = new JLabel("ตารางเรียน");
            title.setOpaque(true);
            title.setBackground(new Color(100, 149, 237));
            title.setHorizontalAlignment(SwingConstants.CENTER);
            title.setFont(thaiFontTitle);
            title.setForeground(Color.white);
            title.setBorder(border);
            background.add(title, grid);

            // ---------- คอลัมน์เวลา ----------
            grid.gridx = 0;
            grid.gridy = 1;
            grid.gridwidth = 1;
            JLabel timeHeader = new JLabel("วัน / เวลา");
            timeHeader.setOpaque(true);
            timeHeader.setBackground(Color.gray);
            timeHeader.setHorizontalAlignment(SwingConstants.CENTER);
            timeHeader.setFont(thaiFont);
            timeHeader.setForeground(Color.white);
            timeHeader.setBorder(border);
            background.add(timeHeader, grid);

            for (int i = 8; i <= 18; i++) {
                grid.gridx = i - 7;
                grid.gridy = 1;
                JLabel time = new JLabel(i + ":00");
                time.setOpaque(true);
                time.setBackground(Color.gray);
                time.setFont(thaiFont);
                time.setForeground(Color.white);
                time.setBorder(border);
                time.setHorizontalAlignment(SwingConstants.CENTER);
                background.add(time, grid);
            }

            // ---------- แถววัน ----------
            String[] days = {"วันจันทร์", "วันอังคาร", "วันพุธ", "วันพฤหัสบดี", "วันศุกร์"};
            for (int i = 0; i < 5; i++) {
                grid.gridx = 0;
                grid.gridy = i + 2;
                JLabel day = new JLabel(days[i]);
                day.setOpaque(true);
                day.setBackground(Color.gray);
                day.setFont(thaiFont);
                day.setForeground(Color.white);
                day.setBorder(border);
                day.setHorizontalAlignment(SwingConstants.CENTER);
                background.add(day, grid);
            }

            // ---------- พื้นที่แสดงตาราง ----------
            JPanel filter = new JPanel();
            grid.gridx = 1;
            grid.gridy = 2;
            grid.gridwidth = 11;
            grid.gridheight = 5;
            filter.setOpaque(false);
            filter.setLayout(null);
            background.add(filter, grid);

                // ---------- แสดง Lecture ----------
                if (student != null) {
                    for (Subject sub : student.getSubjects()) {
                        for (CourseComponent comp : sub.getAllLecture()) {
                            int index = 0;
                            for (String dayTime : comp.getDayTimes()) {
                                String day = comp.getDays().get(index);
                                Double start = comp.getStarts().get(index);
                                Double end = comp.getEnds().get(index);
                                String room = comp.getRooms().get(index);

                                // ✅ สีตามวัน
                                Color dayColor = getColorByDay(day);

                                // ✅ กล่อง Lecture
                                boxPanel box = new boxPanel(sub.getName() + " Lecture (" + room + ")", dayColor, thaiFontSmall);

                                int width = (int) ((end - start) * 90);
                                int height = 60;
                                int xPos = (int) ((start - 8) * 90);
                                int yPos;

                                switch (day) {
                                    case "วันจันทร์" -> yPos = 0;
                                    case "วันอังคาร" -> yPos = 60;
                                    case "วันพุธ" -> yPos = 120;
                                    case "วันพฤหัสบดี" -> yPos = 180;
                                    case "วันศุกร์" -> yPos = 240;
                                    default -> yPos = 300;
                                }

                                box.setBounds(xPos, yPos, width, height);
                                filter.add(box);
                                index++;
                            }
                        }
                    }
                }

        add(background);
    }

    // ---------- ฟังก์ชันเลือกสีตามวัน ----------
    private Color getColorByDay(String day) {
        return switch (day) {
            case "วันจันทร์" -> new Color(255, 255, 153);   // เหลือง
            case "วันอังคาร" -> new Color(255, 182, 193);   // ชมพู
            case "วันพุธ" -> new Color(144, 238, 144);       // เขียว
            case "วันพฤหัสบดี" -> new Color(255, 204, 153); // ส้ม
            case "วันศุกร์" -> new Color(173, 216, 230);     // ฟ้า
            default -> new Color(211, 211, 211);              // เทา (default)
        };
    }

    // ---------- คลาสย่อยกล่องวิชา ----------
    public class boxPanel extends JPanel {
        public boxPanel(String text, Color color, Font font) {
            setOpaque(true);
            setBackground(color);
            setBorder(BorderFactory.createLineBorder(Color.black, 1));

            JLabel label = new JLabel(text, SwingConstants.CENTER);
            label.setFont(font);
            label.setForeground(Color.black);
            add(label);
        }
    }
}
