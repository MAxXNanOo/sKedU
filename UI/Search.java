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
    private JLabel statusLabel; 
    private StudentData studentData;

    private AppFrame frame;
    private int placeNum;

    private final Font FONT_NORMAL = new Font("Tahoma", Font.PLAIN, 14);
    private final Font FONT_BOLD = new Font("Tahoma", Font.BOLD, 14);

    public Search(StudentData studentData, Data data, AppFrame frame, int placeNum) {
        this.data = data;
        this.studentData = studentData;
        this.frame = frame;
        this.placeNum = placeNum;

        setLayout(new BorderLayout());
        setBackground(new Color(200, 230, 255));
        setBorder(BorderFactory.createEmptyBorder(0, (int)(1280 * 0.05), 0, 0));

        // === Top Panel ===
        JPanel topPanel = new JPanel(new GridBagLayout());
        topPanel.setOpaque(false);
        GridBagConstraints grid = new GridBagConstraints();
        grid.fill = GridBagConstraints.BOTH;
        grid.weightx = 1;

        JPanel panel1 = new JPanel();
        JLabel title = new JLabel("ค้นหารหัสวิชา");
        title.setFont(FONT_BOLD);
        panel1.setOpaque(false);
        panel1.add(title);
        grid.gridx = 0;
        grid.gridy = 0;
        topPanel.add(panel1, grid);

        JPanel panel2 = new JPanel();
        panel2.setOpaque(false);
        codeField = new JTextField(20);
        codeField.setFont(FONT_NORMAL);
        panel2.add(codeField);
        grid.gridx = 1;
        topPanel.add(panel2, grid);

        JPanel panel3 = new JPanel();
        panel3.setOpaque(false);
        searchButton = new JButton("Search");
        searchButton.setFont(FONT_NORMAL);
        panel3.add(searchButton);
        grid.gridx = 2;
        topPanel.add(panel3, grid);

        add(topPanel, BorderLayout.NORTH);

        // === Result Container ===
        resultContainer = new JPanel(new GridBagLayout());
        resultContainer.setBackground(Color.WHITE);
        JScrollPane scrollPane = new JScrollPane(resultContainer);

        TitledBorder border = BorderFactory.createTitledBorder("ผลลัพธ์การค้นหา");
        border.setTitleFont(FONT_BOLD);
        scrollPane.setBorder(border);

        statusLabel = new JLabel(" ");
        statusLabel.setFont(FONT_NORMAL);
        statusLabel.setForeground(Color.GRAY);
        statusLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0)); 

        JPanel middlePanel = new JPanel(new BorderLayout());
        middlePanel.add(scrollPane, BorderLayout.CENTER);
        middlePanel.add(statusLabel, BorderLayout.SOUTH);

        add(middlePanel, BorderLayout.CENTER);

        // === Event ===
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
        codeLabel.setFont(FONT_BOLD);

        String id = subject.getId();
        if (!highlightPrefix.isEmpty() && id.toUpperCase().startsWith(highlightPrefix)) {
            String prefix = id.substring(0, highlightPrefix.length());
            String rest = id.substring(highlightPrefix.length());
            codeLabel.setText("<html><span style='background-color: #add8e6; font-family: Tahoma;'>" + prefix + "</span>" + rest + "</html>");
        } else {
            codeLabel.setText(id);
        }
        leftPanel.add(codeLabel);

        JLabel nameLabel = new JLabel(subject.getName());
        nameLabel.setFont(FONT_NORMAL);
        leftPanel.add(nameLabel);

        panel.add(leftPanel, BorderLayout.WEST);

        JLabel creditLabel = new JLabel("หน่วยกิตรวม: " + subject.getTotalCredit()
                + " | หมู่บรรยาย: " + subject.getAllLecture().size()
                + " | หมู่แลป: " + subject.getAllLab().size());
        creditLabel.setFont(FONT_NORMAL);
        creditLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        panel.add(creditLabel, BorderLayout.EAST);

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showSubjectDetailDialog(subject);
            }

            @Override
            public void mouseEntered(MouseEvent e) { panel.setBackground(new Color(230, 245, 255)); }
            @Override
            public void mouseExited(MouseEvent e) { panel.setBackground(Color.WHITE); }
        });

        return panel;
    }

    private void showSubjectDetailDialog(Subject subject) {
    String top = "<html>"
            + "<div style='font-family: Tahoma; font-size: 14pt;'>"
            + "<b>รหัสวิชา:</b> <span style='color: #1a73e8;'>" + subject.getId() + "</span><br>"
            + "<b>ชื่อวิชา:</b> " + subject.getName() + "<br>"
            + "<b>หน่วยกิตรวม:</b> " + subject.getTotalCredit() + "<br>"
            + "</div></html>";

    JPanel messagePanel = new JPanel(new BorderLayout());
    JLabel topLabel = new JLabel(top);
    topLabel.setFont(FONT_NORMAL);
    messagePanel.add(topLabel, BorderLayout.NORTH);

    JPanel detailPanel = new JPanel();
    detailPanel.setLayout(new BoxLayout(detailPanel, BoxLayout.Y_AXIS));

    // ==== Lecture Sections ====
    JLabel lecTitle = new JLabel("<html><b>หมู่บรรยาย:</b></html>");
    lecTitle.setFont(FONT_BOLD);
    detailPanel.add(lecTitle);

    if (subject.getAllLecture().isEmpty()) {
        JLabel noLec = new JLabel("ไม่มีหมู่บรรยาย");
        noLec.setFont(FONT_NORMAL);
        detailPanel.add(noLec);
    } else {
        for (CourseComponent lec : subject.getAllLecture()) {
            JPanel lecPanel = new JPanel();
            lecPanel.setLayout(new BoxLayout(lecPanel, BoxLayout.Y_AXIS));
            lecPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
            lecPanel.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(200, 220, 255)),
                    BorderFactory.createEmptyBorder(8, 10, 8, 10)
            ));
            lecPanel.setBackground(new Color(240, 247, 255));

            JLabel secLabel = new JLabel("หมู่บรรยายที่ " + lec.getSection());
            secLabel.setFont(FONT_BOLD);
            JLabel credit = new JLabel("หน่วยกิต: " + lec.getCredit());
            JLabel dayTime = new JLabel("วันเวลา: " + lec.getDayTimes());
            JLabel room = new JLabel("ห้อง: " + lec.getRooms());
            JLabel teacher = new JLabel("อาจารย์: " + lec.getTeacherNames());

            for (JLabel lbl : new JLabel[]{credit, dayTime, room, teacher}) {
                lbl.setFont(FONT_NORMAL);
            }

            JButton selectBtn = new JButton("เลือกหมู่บรรยายนี้");
            selectBtn.setFont(FONT_NORMAL);
            selectBtn.setBackground(new Color(210, 230, 255));
            selectBtn.setFocusPainted(false);
            selectBtn.addActionListener(ev -> {
                studentData.addSubjectToStudentLogin(studentData.getStudentTmp(), "Lec", subject.getId(), lec.getSection());
                JOptionPane.showMessageDialog(Search.this,
                        "เพิ่มหมู่บรรยายที่ " + lec.getSection() + " เรียบร้อย!",
                        "เพิ่มรายวิชา", JOptionPane.INFORMATION_MESSAGE);
                if(placeNum == 2){
                    frame.showCustomPanel();
                }
            });

            lecPanel.add(secLabel);
            lecPanel.add(credit);
            lecPanel.add(dayTime);
            lecPanel.add(room);
            lecPanel.add(teacher);
            lecPanel.add(Box.createVerticalStrut(5));
            lecPanel.add(selectBtn);
            lecPanel.add(Box.createVerticalStrut(5));

            detailPanel.add(lecPanel);
            detailPanel.add(Box.createVerticalStrut(10));
        }
    }

    detailPanel.add(Box.createVerticalStrut(15));

    // ==== Lab Sections ====
    JLabel labTitle = new JLabel("<html><b>หมู่แลป:</b></html>");
    labTitle.setFont(FONT_BOLD);
    detailPanel.add(labTitle);

    if (subject.getAllLab().isEmpty()) {
        JLabel noLab = new JLabel("ไม่มีหมู่แลป");
        noLab.setFont(FONT_NORMAL);
        detailPanel.add(noLab);
    } else {
        for (CourseComponent lab : subject.getAllLab()) {
            JPanel labPanel = new JPanel();
            labPanel.setLayout(new BoxLayout(labPanel, BoxLayout.Y_AXIS));
            labPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
            labPanel.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(200, 255, 200)),
                    BorderFactory.createEmptyBorder(8, 10, 8, 10)
            ));
            labPanel.setBackground(new Color(240, 255, 240));

            JLabel secLabel = new JLabel("หมู่แลปที่ " + lab.getSection());
            secLabel.setFont(FONT_BOLD);
            JLabel credit = new JLabel("หน่วยกิต: " + lab.getCredit());
            JLabel dayTime = new JLabel("วันเวลา: " + lab.getDayTimes());
            JLabel room = new JLabel("ห้อง: " + lab.getRooms());
            JLabel teacher = new JLabel("อาจารย์: " + lab.getTeacherNames());

            for (JLabel lbl : new JLabel[]{credit, dayTime, room, teacher}) {
                lbl.setFont(FONT_NORMAL);
            }

            JButton selectBtn = new JButton("เลือกหมู่แลปนี้");
            selectBtn.setFont(FONT_NORMAL);
            selectBtn.setBackground(new Color(210, 255, 210));
            selectBtn.setFocusPainted(false);
            selectBtn.addActionListener(ev -> {
                studentData.addSubjectToStudentLogin(studentData.getStudentTmp(), "Lab", subject.getId(), lab.getSection());
                JOptionPane.showMessageDialog(Search.this,
                        "เพิ่มหมู่แลปที่ " + lab.getSection() + " เรียบร้อย!",
                        "เพิ่มรายวิชา", JOptionPane.INFORMATION_MESSAGE);
                if(placeNum == 2){
                    frame.showCustomPanel();
                }
            });

            labPanel.add(secLabel);
            labPanel.add(credit);
            labPanel.add(dayTime);
            labPanel.add(room);
            labPanel.add(teacher);
            labPanel.add(Box.createVerticalStrut(5));
            labPanel.add(selectBtn);
            labPanel.add(Box.createVerticalStrut(5));

            detailPanel.add(labPanel);
            detailPanel.add(Box.createVerticalStrut(10));
        }
    }

    JScrollPane scrollPane = new JScrollPane(detailPanel);
    scrollPane.setPreferredSize(new Dimension(500, 400));
    messagePanel.add(scrollPane, BorderLayout.CENTER);

    JOptionPane.showMessageDialog(Search.this, messagePanel, "รายละเอียดวิชา", JOptionPane.PLAIN_MESSAGE);
}

}
