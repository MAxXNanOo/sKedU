package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import Subject.*;

public class Detail extends JPanel {

    public Detail(StudentData studentData, Data data) {

        setLayout(null);
        JPanel sidebar = new JPanel();
        sidebar.setBounds(0, 0, 320, 160);
        sidebar.setOpaque(true);
        sidebar.setBackground(Color.gray);
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));

        // ✅ สร้าง Font ไทยที่อ่านได้
        Font thaiFont = new Font("Tahoma", Font.PLAIN, 16);

        Student student = studentData.getStudentLogin();

        if (student != null) {
            JLabel nameLabel = new JLabel("ชื่อ: " + student.getStudentName());
            nameLabel.setFont(thaiFont);
            sidebar.add(nameLabel);

            JLabel idLabel = new JLabel("รหัส: " + student.getStudentId());
            idLabel.setFont(thaiFont);
            sidebar.add(idLabel);

            JLabel majorLabel = new JLabel("สาขา: " + student.getMajor());
            majorLabel.setFont(thaiFont);
            sidebar.add(majorLabel);

            sidebar.add(Box.createVerticalStrut(10));

            if (student.getDetails() != null && !student.getDetails().isEmpty()) {
                JLabel detailLabel = new JLabel("รายละเอียดเพิ่มเติม:");
                detailLabel.setFont(thaiFont);
                sidebar.add(detailLabel);

                for (String d : student.getDetails()) {
                    JLabel dLabel = new JLabel("• " + d);
                    dLabel.setFont(thaiFont);
                    sidebar.add(dLabel);
                }
            }

            sidebar.add(Box.createVerticalStrut(10));

            // แสดงรายวิชา
            if (student.getSubjects() != null && !student.getSubjects().isEmpty()) {
                JLabel subjTitle = new JLabel("รายวิชา:");
                subjTitle.setFont(thaiFont);
                sidebar.add(subjTitle);

                for (Subject s : student.getSubjects()) {
                    JLabel subjLabel = new JLabel("• " + s.getName());
                    subjLabel.setFont(thaiFont);
                    sidebar.add(subjLabel);

                    for (Subject realSub : data.getSubjects()) {
                        if (realSub.getId().equals(s.getId())) {

                            // Lecture
                            if (!realSub.getAllLecture().isEmpty()) {
                                for (CourseComponent lec : realSub.getAllLecture()) {
                                    JLabel lecLabel = new JLabel("   Lecture Sec: " + lec.getSection() +
                                            ", Credit: " + lec.getCredit());
                                    lecLabel.setFont(thaiFont);
                                    sidebar.add(lecLabel);
                                }
                            }

                            // Lab
                            if (!realSub.getAllLab().isEmpty()) {
                                for (CourseComponent lab : realSub.getAllLab()) {
                                    JLabel labLabel = new JLabel("   Lab Sec: " + lab.getSection() +
                                            ", Credit: " + lab.getCredit());
                                    labLabel.setFont(thaiFont);
                                    sidebar.add(labLabel);
                                }
                            }
                        }
                    }
                }
            }
        } else {
            JLabel noStudent = new JLabel("ยังไม่มีนักเรียนล็อกอิน");
            noStudent.setFont(thaiFont);
            sidebar.add(noStudent);
        }

        add(sidebar);
    }
}
