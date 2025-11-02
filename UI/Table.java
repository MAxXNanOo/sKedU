package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.Border;
import Subject.*;

public class Table extends JPanel {
    private Student student;

    public Table(AppFrame frame, StudentData studentData, Student student, Data data) {
        this.student = student;

        setLayout(null);

        Border border = BorderFactory.createLineBorder(Color.black, 1);

        JPanel background = new JPanel();
        background.setBounds(0, 0, 1170, 400);
        background.setOpaque(true);
        background.setBackground(Color.lightGray);
        background.setBorder(border);
        background.setLayout(null); // ❌ ไม่ใช้ GridBagLayout อีกแล้ว

        // ✅ ฟอนต์ภาษาไทย
        Font thaiFontTitle = new Font("Tahoma", Font.BOLD, 24);
        Font thaiFont = new Font("Tahoma", Font.BOLD, 16);
        Font thaiFontSmall = new Font("Tahoma", Font.BOLD, 14);

        // ---------- หัวตาราง ----------
        JLabel title = new JLabel("ตารางเรียน");
        title.setOpaque(true);
        title.setBackground(new Color(100, 149, 237));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(thaiFontTitle);
        title.setForeground(Color.white);
        title.setBorder(border);
        title.setBounds(0, 0, 1170, 50);
        background.add(title);

        // ---------- คอลัมน์เวลา ----------
        JLabel timeHeader = new JLabel("วัน / เวลา");
        timeHeader.setOpaque(true);
        timeHeader.setBackground(Color.gray);
        timeHeader.setHorizontalAlignment(SwingConstants.CENTER);
        timeHeader.setFont(thaiFont);
        timeHeader.setForeground(Color.white);
        timeHeader.setBorder(border);
        timeHeader.setBounds(0, 50, 180, 60);
        background.add(timeHeader);

        String[] days = {"วันจันทร์", "วันอังคาร", "วันพุธ", "วันพฤหัสบดี", "วันศุกร์"};
        int startX = 180;
        int startY = 50;
        int cellWidth = 90; // ✅ ตามที่ต้องการ
        int cellHeight = 60;

        // ---------- แถวเวลา ----------
        for (int i = 8; i <= 18; i++) {
            JLabel timeLabel = new JLabel(i + ":00", SwingConstants.CENTER);
            timeLabel.setOpaque(true);
            timeLabel.setBackground(Color.gray);
            timeLabel.setFont(thaiFont);
            timeLabel.setForeground(Color.white);
            timeLabel.setBorder(border);
            timeLabel.setBounds(startX + (i - 8) * cellWidth, startY, cellWidth, cellHeight);
            background.add(timeLabel);
        }

        // ---------- แถววัน ----------
        for (int i = 0; i < 5; i++) {
            JLabel dayLabel = new JLabel(days[i], SwingConstants.CENTER);
            dayLabel.setOpaque(true);
            dayLabel.setBackground(Color.gray);
            dayLabel.setFont(thaiFont);
            dayLabel.setForeground(Color.white);
            dayLabel.setBorder(border);
            dayLabel.setBounds(0, startY + (i + 1) * cellHeight, 180, cellHeight);
            background.add(dayLabel);
        }

        // ---------- ตารางว่าง ----------
        JLayeredPane tableLayer = new JLayeredPane();
        tableLayer.setBounds(180, startY + cellHeight, cellWidth * 11, cellHeight * 5); // กว้าง 11 ชั่วโมง, สูง 5 วัน
        tableLayer.setOpaque(true);
        tableLayer.setBackground(new Color(250, 250, 250));
        background.add(tableLayer);

        // เส้น grid คงที่
        for (int i = 0; i <= 11; i++) { // เส้นแนวตั้ง
            JLabel vLine = new JLabel();
            vLine.setOpaque(true);
            vLine.setBackground(new Color(220, 220, 220));
            vLine.setBounds(i * cellWidth, 0, 1, 5 * cellHeight);
            tableLayer.add(vLine, Integer.valueOf(0));
        }
        for (int i = 0; i <= 5; i++) { // เส้นแนวนอน
            JLabel hLine = new JLabel();
            hLine.setOpaque(true);
            hLine.setBackground(new Color(220, 220, 220));
            hLine.setBounds(0, i * cellHeight, 11 * cellWidth, 1);
            tableLayer.add(hLine, Integer.valueOf(0));
        }

        // ---------- แสดง Lecture และ Lab ----------
        if (student != null) {
            for (Subject sub : student.getSubjects()) {
                for (CourseComponent comp : sub.getAllLecture()) {
                    int index = 0;
                    for (String dayTime : comp.getDayTimes()) {
                        String day = comp.getDays().get(index);
                        Double start = comp.getStarts().get(index);
                        Double end = comp.getEnds().get(index);
                        String room = comp.getRooms().get(index);
                        int section = comp.getSection();

                        Color dayColor = getColorByDay(day);

                        boxPanel box = new boxPanel(sub.getName() + " Lecture (" + room + ")", dayColor, thaiFontSmall);
                        int width = (int) ((end - start) * cellWidth);
                        int height = cellHeight;
                        int xPos = (int) ((start - 8) * cellWidth);
                        int yPos = getYByDay(day);

                        box.setBounds(xPos, yPos, width, height);
                        box.addMouseListener(new MouseAdapter() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                    // ตั้งฟอนต์ Tahoma ให้ข้อความใน JOptionPane
                                    UIManager.put("OptionPane.messageFont", new Font("Tahoma", Font.PLAIN, 14));
                                    UIManager.put("OptionPane.buttonFont", new Font("Tahoma", Font.PLAIN, 14));

                                    // ตัวเลือกปุ่ม
                                    Object[] options = {"ปิด", "ถอนวิชา"};

                                    // แสดง dialog
                                    int choice = JOptionPane.showOptionDialog(
                                            null,
                                            sub.getName() + " Lab\nเวลา: " + dayTime + "\nห้อง: " + room,
                                            "รายละเอียดวิชา",
                                            JOptionPane.YES_NO_OPTION,
                                            JOptionPane.INFORMATION_MESSAGE,
                                            null,
                                            options,
                                            options[0]
                                    );

                                    // ถ้ากด "ถอนวิชา"
                                    if (choice == 1) {
                                        studentData.deleteSubjectFromStudentTmp("Lec", sub.getId(), section);
                                        frame.showCustomPanel();
                                    }
                                }
                            });
                        tableLayer.add(box, Integer.valueOf(1));
                        index++;
                    }
                }

                for (CourseComponent comp : sub.getAllLab()) {
                    int index = 0;
                    for (String dayTime : comp.getDayTimes()) {
                        String day = comp.getDays().get(index);
                        Double start = comp.getStarts().get(index);
                        Double end = comp.getEnds().get(index);
                        String room = comp.getRooms().get(index);
                        int section = comp.getSection();

                        Color dayColor = getColorByDay(day);

                        boxPanel box = new boxPanel(sub.getName() + " Lab (" + room + ")", dayColor, thaiFontSmall);
                        int width = (int) ((end - start) * cellWidth);
                        int height = cellHeight;
                        int xPos = (int) ((start - 8) * cellWidth);
                        int yPos = getYByDay(day);

                        box.setBounds(xPos, yPos, width, height);
                        box.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                // ตั้งฟอนต์ Tahoma ให้ข้อความใน JOptionPane
                                UIManager.put("OptionPane.messageFont", new Font("Tahoma", Font.PLAIN, 14));
                                UIManager.put("OptionPane.buttonFont", new Font("Tahoma", Font.PLAIN, 14));

                                // ตัวเลือกปุ่ม
                                Object[] options = {"ปิด", "ถอนวิชา"};

                                // แสดง dialog
                                int choice = JOptionPane.showOptionDialog(
                                        null,
                                        sub.getName() + " Lab\nเวลา: " + dayTime + "\nห้อง: " + room,
                                        "รายละเอียดวิชา",
                                        JOptionPane.YES_NO_OPTION,
                                        JOptionPane.INFORMATION_MESSAGE,
                                        null,
                                        options,
                                        options[0]
                                );

                                // ถ้ากด "ถอนวิชา"
                                if (choice == 1) {
                                    studentData.deleteSubjectFromStudentTmp("Lab", sub.getId(), section);
                                    frame.showCustomPanel();
                                }
                            }
                        });
                        tableLayer.add(box, Integer.valueOf(1));
                        index++;
                    }
                }
            }
        }

        add(background);
    }

    // ฟังก์ชันเลือกสีตามวัน
    private Color getColorByDay(String day) {
        return switch (day) {
            case "วันจันทร์" -> new Color(255, 255, 153);
            case "วันอังคาร" -> new Color(255, 182, 193);
            case "วันพุธ" -> new Color(144, 238, 144);
            case "วันพฤหัสบดี" -> new Color(255, 204, 153);
            case "วันศุกร์" -> new Color(173, 216, 230);
            default -> new Color(211, 211, 211);
        };
    }

    private int getYByDay(String day) {
        return switch (day) {
            case "วันจันทร์" -> 0;
            case "วันอังคาร" -> 60;
            case "วันพุธ" -> 120;
            case "วันพฤหัสบดี" -> 180;
            case "วันศุกร์" -> 240;
            default -> 300;
        };
    }

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









































































































// package UI;

// import javax.swing.*;
// import java.awt.*;
// import java.awt.event.*;
// import javax.swing.border.Border;
// import Subject.*;

// public class Table extends JPanel {
//     private Student student;

//     public Table(AppFrame frame, StudentData studentData,Student student, Data data) {
//         this.student = student;

//         setLayout(null);


//         Border border = BorderFactory.createLineBorder(Color.black, 1);

//         JPanel background = new JPanel();
//         background.setBounds(0, 0, 1170, 400);
//         background.setOpaque(true);
//         background.setBackground(Color.lightGray);
//         background.setBorder(border);

//             GridBagConstraints grid = new GridBagConstraints();
//             background.setLayout(new GridBagLayout());
//             grid.fill = GridBagConstraints.BOTH;
//             grid.weightx = 1;
//             grid.weighty = 1;

//             // ✅ ฟอนต์ภาษาไทย
//             Font thaiFontTitle = new Font("Tahoma", Font.BOLD, 24);
//             Font thaiFont = new Font("Tahoma", Font.BOLD, 16);
//             Font thaiFontSmall = new Font("Tahoma", Font.BOLD, 14);

//             // ---------- หัวตาราง ----------
//             grid.gridx = 0;
//             grid.gridy = 0;
//             grid.gridwidth = 13;
//             JLabel title = new JLabel("ตารางเรียน");
//             title.setOpaque(true);
//             title.setBackground(new Color(100, 149, 237));
//             title.setHorizontalAlignment(SwingConstants.CENTER);
//             title.setFont(thaiFontTitle);
//             title.setForeground(Color.white);
//             title.setBorder(border);
//             background.add(title, grid);

//             // ---------- คอลัมน์เวลา ----------
//             grid.gridx = 0;
//             grid.gridy = 1;
//             grid.gridwidth = 2;
//             JLabel timeHeader = new JLabel("วัน / เวลา");
//             timeHeader.setOpaque(true);
//             timeHeader.setBackground(Color.gray);
//             timeHeader.setHorizontalAlignment(SwingConstants.CENTER);
//             timeHeader.setFont(thaiFont);
//             timeHeader.setForeground(Color.white);
//             timeHeader.setBorder(border);
//             background.add(timeHeader, grid);

//             grid.gridwidth = 1;
//             // ---------- แถวเวลา ----------
//             for (int i = 8; i <= 18; i++) {
//                 grid.gridx = i - 6;
//                 grid.gridy = 1;
//                 JLabel time = new JLabel(i + ":00");
//                 time.setOpaque(true);
//                 time.setBackground(Color.gray);
//                 time.setFont(thaiFont);
//                 time.setForeground(Color.white);
//                 time.setBorder(border);
//                 time.setHorizontalAlignment(SwingConstants.CENTER);
//                 background.add(time, grid);
//             }

//             // ---------- แถววัน ----------
//             grid.gridwidth = 2;
//             String[] days = {"MON", "TUE", "WED", "THU", "FRI"};
//             for (int i = 0; i < 5; i++) {
//                 grid.gridx = 0;
//                 grid.gridy = i + 2;
//                 JLabel day = new JLabel(days[i]);
//                 day.setOpaque(true);
//                 day.setBackground(Color.gray);
//                 day.setFont(thaiFont);
//                 day.setForeground(Color.white);
//                 day.setBorder(border);
//                 day.setBounds(0, 0, 180, 60);
//                 day.setHorizontalAlignment(SwingConstants.CENTER);
//                 background.add(day, grid);
//             }

//             // ---------- พื้นที่แสดงตาราง ----------
//             grid.gridwidth = 1;
//             JPanel filter = new JPanel();
//             grid.gridx = 2;
//             grid.gridy = 2;
//             grid.gridwidth = 11;
//             grid.gridheight = 5;
//             filter.setOpaque(false);
//             filter.setLayout(null);
//             background.add(filter, grid);

//                 // ---------- แสดง Lecture ----------
//                 if (student != null) {
//                     for (Subject sub : student.getSubjects()) {
//                         for (CourseComponent comp : sub.getAllLecture()) {
//                                 System.out.println("add\n");
//                             int index = 0;
//                             for (String dayTime : comp.getDayTimes()) {
//                                 String day = comp.getDays().get(index);
//                                 Double start = comp.getStarts().get(index);
//                                 Double end = comp.getEnds().get(index);
//                                 String room = comp.getRooms().get(index);
//                                 int section = comp.getSection();
//                                 // ✅ สีตามวัน
//                                 Color dayColor = getColorByDay(day);

//                                 // ✅ กล่อง Lecture
//                                 boxPanel box = new boxPanel(sub.getName() + " Lecture (" + room + ")", dayColor, thaiFontSmall);

//                                 int width = (int) ((end - start) * 90);
//                                 int height = 60;
//                                 int xPos = (int) ((start - 8) * 90);
//                                 int yPos;

//                                 switch (day) {
//                                     case "วันจันทร์" -> yPos = 0;
//                                     case "วันอังคาร" -> yPos = 60;
//                                     case "วันพุธ" -> yPos = 120;
//                                     case "วันพฤหัสบดี" -> yPos = 180;
//                                     case "วันศุกร์" -> yPos = 240;
//                                     default -> yPos = 300;
//                                 }

//                                 box.setBounds(xPos, yPos, width, height);
//                                 box.addMouseListener(new MouseAdapter() {
//                                     @Override
//                                     public void mouseClicked(MouseEvent e) {
//                                         // ตั้งฟอนต์ Tahoma ให้ข้อความใน JOptionPane
//                                         UIManager.put("OptionPane.messageFont", new Font("Tahoma", Font.PLAIN, 14));
//                                         UIManager.put("OptionPane.buttonFont", new Font("Tahoma", Font.PLAIN, 14));

//                                         // ตัวเลือกปุ่ม
//                                         Object[] options = {"ปิด", "ถอนวิชา"};

//                                         // แสดง dialog
//                                         int choice = JOptionPane.showOptionDialog(
//                                                 null,
//                                                 sub.getName() + " Lab\nเวลา: " + dayTime + "\nห้อง: " + room,
//                                                 "รายละเอียดวิชา",
//                                                 JOptionPane.YES_NO_OPTION,
//                                                 JOptionPane.INFORMATION_MESSAGE,
//                                                 null,
//                                                 options,
//                                                 options[0]
//                                         );

//                                         // ถ้ากด "ถอนวิชา"
//                                         if (choice == 1) {
//                                             studentData.deleteSubjectFromStudentTmp("Lec", sub.getId(), section);
//                                             frame.showCustomPanel();
//                                         }
//                                     }
//                                 });

//                                 filter.add(box);
//                                 index++;
//                             }
//                         }

//                         for(CourseComponent comp : sub.getAllLab()) {
//                             System.out.printf("run\n\n");
//                             int index = 0;
//                             for (String dayTime : comp.getDayTimes()) {
//                                 String day = comp.getDays().get(index);
//                                 Double start = comp.getStarts().get(index);
//                                 Double end = comp.getEnds().get(index);
//                                 String room = comp.getRooms().get(index);
//                                 int section = comp.getSection();

//                                 // ✅ สีตามวัน
//                                 Color dayColor = getColorByDay(day);

//                                 // ✅ กล่อง Lab
//                                 boxPanel box = new boxPanel(sub.getName() + " Lab (" + room + ")", dayColor, thaiFontSmall);

//                                 int width = (int) ((end - start) * 90);
//                                 int height = 60;
//                                 int xPos = (int) ((start - 8) * 92);
//                                 int yPos;

//                                 switch (day) {
//                                     case "วันจันทร์" -> yPos = 0;
//                                     case "วันอังคาร" -> yPos = 60;
//                                     case "วันพุธ" -> yPos = 120;
//                                     case "วันพฤหัสบดี" -> yPos = 180;
//                                     case "วันศุกร์" -> yPos = 240;
//                                     default -> yPos = 300;
//                                 }

//                                 box.setBounds(xPos, yPos, width, height);
//                                 box.addMouseListener(new MouseAdapter() {
//                                     @Override
//                                     public void mouseClicked(MouseEvent e) {
//                                         // ตั้งฟอนต์ Tahoma ให้ข้อความใน JOptionPane
//                                         UIManager.put("OptionPane.messageFont", new Font("Tahoma", Font.PLAIN, 14));
//                                         UIManager.put("OptionPane.buttonFont", new Font("Tahoma", Font.PLAIN, 14));

//                                         // ตัวเลือกปุ่ม
//                                         Object[] options = {"ปิด", "ถอนวิชา"};

//                                         // แสดง dialog
//                                         int choice = JOptionPane.showOptionDialog(
//                                                 null,
//                                                 sub.getName() + " Lab\nเวลา: " + dayTime + "\nห้อง: " + room,
//                                                 "รายละเอียดวิชา",
//                                                 JOptionPane.YES_NO_OPTION,
//                                                 JOptionPane.INFORMATION_MESSAGE,
//                                                 null,
//                                                 options,
//                                                 options[0]
//                                         );

//                                         // ถ้ากด "ถอนวิชา"
//                                         if (choice == 1) {
//                                             studentData.deleteSubjectFromStudentTmp("Lab", sub.getId(), section);
//                                             frame.showCustomPanel();
//                                         }
//                                     }
//                                 });
//                                 filter.add(box);
//                                 index++;
//                             }
//                         }
//                     }
//                 }

//         add(background);
//     }

//     // ---------- ฟังก์ชันเลือกสีตามวัน ----------
//     private Color getColorByDay(String day) {
//         return switch (day) {
//             case "วันจันทร์" -> new Color(255, 255, 153);   // เหลือง
//             case "วันอังคาร" -> new Color(255, 182, 193);   // ชมพู
//             case "วันพุธ" -> new Color(144, 238, 144);       // เขียว
//             case "วันพฤหัสบดี" -> new Color(255, 204, 153); // ส้ม
//             case "วันศุกร์" -> new Color(173, 216, 230);     // ฟ้า
//             default -> new Color(211, 211, 211);              // เทา (default)
//         };
//     }

//     // ---------- คลาสย่อยกล่องวิชา ----------
//     public class boxPanel extends JPanel {
//         public boxPanel(String text, Color color, Font font) {
//             setOpaque(true);
//             setBackground(color);
//             setBorder(BorderFactory.createLineBorder(Color.black, 1));

//             JLabel label = new JLabel(text, SwingConstants.CENTER);
//             label.setFont(font);
//             label.setForeground(Color.black);
//             add(label);
//         }
//     }
// }
