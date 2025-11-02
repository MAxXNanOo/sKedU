package UI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import Subject.*;

public class Detail extends JPanel {

    public Detail(Student student, Data data) {

        setLayout(new BorderLayout());
        setBackground(Color.LIGHT_GRAY);

        // ===== Header =====
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(Color.GRAY);
        headerPanel.setPreferredSize(new Dimension(400, 50));

        JLabel headerLabel = new JLabel("รายละเอียดนักศึกษา", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        headerLabel.setForeground(Color.BLACK);
        headerPanel.add(headerLabel);

        add(headerPanel, BorderLayout.NORTH);

        // ===== Content =====
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(Color.lightGray);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        if (student == null) {
            JLabel notLoggedIn = new JLabel("ยังไม่มีนักเรียนล็อกอิน");
            notLoggedIn.setFont(new Font("Tahoma", Font.PLAIN, 14));
            notLoggedIn.setForeground(Color.BLACK);
            contentPanel.add(notLoggedIn);
        } 
        else {
            ArrayList<String> details = student.getDetails();

            if (details == null || details.isEmpty()) {
                JLabel noDetail = new JLabel("ยังไม่มีรายละเอียด");
                noDetail.setFont(new Font("Tahoma", Font.PLAIN, 14));
                noDetail.setForeground(Color.BLACK);
                contentPanel.add(noDetail);
            } 
            else {
                for (String detail : details) {
                    if(detail.trim().isEmpty()) continue;
                    JLabel label = new JLabel(detail);
                    label.setFont(new Font("Tahoma", Font.PLAIN, 14));
                    label.setForeground(Color.BLACK);
                    label.setAlignmentX(Component.LEFT_ALIGNMENT);

                    JPanel panel = new JPanel(new BorderLayout());
                    panel.setBackground(Color.WHITE);
                    panel.setBorder(BorderFactory.createCompoundBorder(
                            BorderFactory.createLineBorder(Color.LIGHT_GRAY),
                            BorderFactory.createEmptyBorder(5, 10, 5, 10)
                    ));
                    panel.add(label, BorderLayout.CENTER);

                    contentPanel.add(panel);
                    contentPanel.add(Box.createVerticalStrut(5));
                }
            }
        }

        // ===== Scroll =====
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        add(scrollPane, BorderLayout.CENTER);
    }
}
