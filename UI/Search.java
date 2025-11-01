package UI;

import Subject.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class Search extends JPanel {
    private static final int SUBJECT_PANEL_HEIGHT = 80; 
    private JTextField codeField;
    private JButton searchButton;
    private JPanel resultContainer;
    private Data data;
    private JLabel statusLabel; // ข้อความสถานะด้านล่าง

    public Search(StudentData studentData, Data data) {
        this.data = data;
        setLayout(new BorderLayout());
        setBackground(new Color(200, 230, 255));
        setBorder(BorderFactory.createEmptyBorder(0, (int)(1280 * 0.05), 0, 0));

        // Top panel (รหัส + field + ปุ่ม)
        JPanel topPanel = new JPanel(new GridBagLayout());
        topPanel.setOpaque(false);
        GridBagConstraints grid = new GridBagConstraints();
        grid.fill = GridBagConstraints.BOTH;
        grid.weightx = 1;

                JPanel panel1 = new JPanel();
                JLabel title = new JLabel("ค้นหารหัสวิชา");
                title.setFont(new Font("Tahoma", Font.BOLD, 14));
                panel1.setOpaque(false);
                panel1.add(title);
                grid.gridx = 0;
                grid.gridy = 0;
                topPanel.add(panel1, grid);

                JPanel panel2 = new JPanel();
                panel2.setOpaque(false);
                codeField = new JTextField(20);
                codeField.setFont(new Font("Tahoma", Font.PLAIN, 14));
                panel2.add(codeField);
                grid.gridx = 1;
                topPanel.add(panel2, grid);

                JPanel panel3 = new JPanel();
                panel3.setOpaque(false);
                searchButton = new JButton("Search");
                searchButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
                panel3.add(searchButton);
                grid.gridx = 2;
                topPanel.add(panel3, grid);

        add(topPanel, BorderLayout.NORTH);











        // Result container
        resultContainer = new JPanel(new GridBagLayout());
        resultContainer.setBackground(Color.WHITE);
                JScrollPane scrollPane = new JScrollPane(resultContainer);
                        TitledBorder border = BorderFactory.createTitledBorder("ผลลัพธ์การค้นหา");
                        border.setTitleFont(new Font("Tahoma", Font.BOLD, 14));
                        scrollPane.setBorder(border);

                        // Status label ด้านล่าง scrollPane
                        statusLabel = new JLabel(" ");
                        statusLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
                        statusLabel.setForeground(Color.GRAY);
                        statusLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0)); 

                        // Panel กลางรวม scrollPane + statusLabel
                        JPanel middlePanel = new JPanel(new BorderLayout());
                middlePanel.add(scrollPane, BorderLayout.CENTER);
        middlePanel.add(statusLabel, BorderLayout.SOUTH);

        add(middlePanel, BorderLayout.CENTER);

        // Event
        searchButton.addActionListener(e -> performSearch());
        codeField.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { performSearch(); }
            public void removeUpdate(DocumentEvent e) { performSearch(); }
            public void changedUpdate(DocumentEvent e) { performSearch(); }
        });
    }











    private void performSearch() {
        String codePrefix = codeField.getText().trim().toUpperCase();
        resultContainer.removeAll();

        if (codePrefix.isEmpty()) {
            statusLabel.setText("กรุณากรอกรหัสวิชา");
            statusLabel.setForeground(Color.GRAY);
            resultContainer.revalidate();
            resultContainer.repaint();
            return;
        }

        List<Subject> matchedSubjects = data.getSubjects().stream()
                .filter(s -> s.getId().toUpperCase().startsWith(codePrefix))
                .sorted(Comparator.comparing(Subject::getId))
                .collect(Collectors.toList());

        if (matchedSubjects.isEmpty()) {
            statusLabel.setText("ไม่พบวิชาที่ตรงกับ: " + codePrefix);
            statusLabel.setForeground(Color.RED);
            resultContainer.revalidate();
            resultContainer.repaint();
            return;
        }

        // ถ้ามีผลลัพธ์
        statusLabel.setText("ผลลัพธ์สำหรับ: " + codePrefix);
        statusLabel.setForeground(Color.BLACK);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTH;

        int row = 0;
        for (Subject subject : matchedSubjects) {
            JPanel subjectPanel = createSubjectPanel(subject, codePrefix);
            gbc.gridy = row++;
            resultContainer.add(subjectPanel, gbc);

            JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
            separator.setForeground(Color.LIGHT_GRAY);
            gbc.gridy = row++;
            resultContainer.add(separator, gbc);
        }

        // เติมพื้นที่ว่างด้านล่าง
        gbc.gridy = row;
        gbc.weighty = 1;
        resultContainer.add(Box.createVerticalGlue(), gbc);

        resultContainer.revalidate();
        resultContainer.repaint();
    }

    private JPanel createSubjectPanel(Subject subject, String highlightPrefix) {
        JPanel panel = new JPanel(new BorderLayout(10, 0));
        panel.setBackground(Color.WHITE);
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setOpaque(false);

        JLabel codeLabel = new JLabel();
        codeLabel.setFont(new Font("Tahoma", Font.BOLD, 14));

        String id = subject.getId();
        if (!highlightPrefix.isEmpty() && id.toUpperCase().startsWith(highlightPrefix)) {
            String prefix = id.substring(0, highlightPrefix.length());
            String rest = id.substring(highlightPrefix.length());
            codeLabel.setText("<html><span style='background-color: #add8e6;'>" + prefix + "</span>" + rest + "</html>");
        } else {
            codeLabel.setText(id);
        }
        leftPanel.add(codeLabel);

        JLabel nameLabel = new JLabel(subject.getName());
        nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        leftPanel.add(nameLabel);

        panel.add(leftPanel, BorderLayout.WEST);

        JLabel creditLabel = new JLabel("หน่วยกิตรวม: " + subject.getTotalCredit()
                + " | หมู่บรรยาย: " + subject.getAllLecture().size()
                + " | หมู่แลป: " + subject.getAllLab().size());
        creditLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        creditLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        panel.add(creditLabel, BorderLayout.EAST);

       
        panel.addMouseListener(new MouseAdapter() {
                    //Max ทำนะจ๊ะ
                @Override
public void mouseClicked(MouseEvent e) {
    // สร้างข้อความส่วนหัว
    String top = "<html>"
            + "<div style='font-family: Tahoma; font-size: 14pt;'>"
            + "<b>รหัสวิชา:</b> <span style='color: #1a73e8;'>" + subject.getId() + "</span><br>"
            + "<b>ชื่อวิชา:</b> " + subject.getName() + "<br>"
            + "<b>หน่วยกิตรวม:</b> " + subject.getTotalCredit() + "<br>"
            + "</div></html>";

    // สร้างข้อความรายละเอียด lecture
    String courseLecture = "<html><div style='font-family: Tahoma; font-size: 14pt;'>";
    if (subject.getAllLecture().isEmpty()) {
        courseLecture += "<b>ไม่มีหมู่บรรยาย</b><br>";
    } else {
        for (CourseComponent lec : subject.getAllLecture()) {
            courseLecture += "<b>หมู่บรรยายที่ " + lec.getSection() + ":</b><br>"
                    + "&nbsp;&nbsp;&nbsp;&nbsp;หน่วยกิต: " + lec.getCredit() + "<br>"
                    + "&nbsp;&nbsp;&nbsp;&nbsp;วันเวลา: " + lec.getDayTimes() + "<br>"
                    + "&nbsp;&nbsp;&nbsp;&nbsp;ห้อง: " + lec.getRooms() + "<br>"
                    + "&nbsp;&nbsp;&nbsp;&nbsp;อาจารย์: " + lec.getTeacherNames() + "<br><br>";
        }
    }
    courseLecture += "</div></html>";

    // สร้างข้อความรายละเอียด lab
    String courseLab = "<html><div style='font-family: Tahoma; font-size: 14pt;'>";
    if (subject.getAllLab().isEmpty()) {
        courseLab += "<b>ไม่มีหมู่แลป</b><br>";
    } else {
        for (CourseComponent lab : subject.getAllLab()) {
            courseLab += "<b>หมู่แลปที่ " + lab.getSection() + ":</b><br>"
                    + "&nbsp;&nbsp;&nbsp;&nbsp;หน่วยกิต: " + lab.getCredit() + "<br>"
                    + "&nbsp;&nbsp;&nbsp;&nbsp;วันเวลา: " + lab.getDayTimes() + "<br>"
                    + "&nbsp;&nbsp;&nbsp;&nbsp;ห้อง: " + lab.getRooms() + "<br>"
                    + "&nbsp;&nbsp;&nbsp;&nbsp;อาจารย์: " + lab.getTeacherNames() + "<br><br>";
        }
    }
    courseLab += "</div></html>";

    // สร้าง JPanel หลัก
    JPanel messagePanel = new JPanel(new BorderLayout());
    JLabel topLabel = new JLabel(top);
    messagePanel.add(topLabel, BorderLayout.NORTH);

    // รวม lecture + lab เป็น JPanel เดียว
    JPanel detailPanel = new JPanel();
    detailPanel.setLayout(new BoxLayout(detailPanel, BoxLayout.Y_AXIS));

    JLabel lectureLabel = new JLabel(courseLecture);
    JLabel labLabel = new JLabel(courseLab);

    detailPanel.add(lectureLabel);
    detailPanel.add(Box.createVerticalStrut(10)); // เว้นบรรทัด
    detailPanel.add(labLabel);

    // ใส่ JScrollPane
    JScrollPane scrollPane = new JScrollPane(detailPanel);
    scrollPane.setPreferredSize(new Dimension(400, 300));
    messagePanel.add(scrollPane, BorderLayout.CENTER);

    // แสดง JOptionPane
    JOptionPane.showMessageDialog(Search.this, messagePanel, "รายละเอียดวิชา", JOptionPane.PLAIN_MESSAGE);
}


    @Override
    public void mouseEntered(MouseEvent e) { panel.setBackground(new Color(230, 245, 255)); }
    @Override
    public void mouseExited(MouseEvent e) { panel.setBackground(Color.WHITE); }
});

        return panel;
    }
}