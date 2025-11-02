package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import Subject.*;

public class Detail extends JPanel {

    public Detail(AppFrame frame, Student student, Data data, int placeNum) {

        setLayout(new BorderLayout());
        setBackground(Color.LIGHT_GRAY);

        // ===== Header =====
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(Color.WHITE);
        headerPanel.setPreferredSize(new Dimension(400, 50));

        JLabel headerLabel = new JLabel("รายละเอียดนักศึกษา", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        headerLabel.setForeground(Color.BLACK);
        headerPanel.add(headerLabel);

        add(headerPanel, BorderLayout.NORTH);

        // ===== Content =====
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        if (student == null) {
            JLabel notLoggedIn = new JLabel("ยังไม่มีนักเรียนล็อกอิน");
            notLoggedIn.setFont(new Font("Tahoma", Font.PLAIN, 14));
            notLoggedIn.setForeground(Color.BLACK);
            notLoggedIn.setAlignmentX(Component.LEFT_ALIGNMENT);
            contentPanel.add(notLoggedIn);
        } else {
            ArrayList<String> details = student.getDetails();

            if (details == null || details.isEmpty()) {
                JLabel noDetail = new JLabel("ยังไม่มีรายละเอียด");
                noDetail.setFont(new Font("Tahoma", Font.PLAIN, 14));
                noDetail.setForeground(Color.BLACK);
                noDetail.setAlignmentX(Component.LEFT_ALIGNMENT);
                contentPanel.add(noDetail);
            } else {
                for (String detail : details) {
                    if (detail.trim().isEmpty()) continue;

                    JLabel label = new JLabel(detail);
                    label.setFont(new Font("Tahoma", Font.PLAIN, 14));
                    label.setForeground(Color.BLACK);
                    label.setHorizontalAlignment(SwingConstants.LEFT);
                    label.setAlignmentX(Component.LEFT_ALIGNMENT);

                    // 🧩 Panel สำหรับแต่ละรายวิชา
                    JPanel panel = new JPanel(new BorderLayout());
                    panel.setBackground(Color.WHITE);
                    panel.setBorder(BorderFactory.createCompoundBorder(
                            BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
                            BorderFactory.createEmptyBorder(5, 10, 5, 10)
                    ));
                    panel.add(label, BorderLayout.WEST);
                    panel.setAlignmentX(Component.LEFT_ALIGNMENT);

                    // 🎯 ถ้า placeNum == 2 → สามารถคลิกลบได้
                    if (placeNum == 2) {
                        panel.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                UIManager.put("OptionPane.messageFont", new Font("Tahoma", Font.PLAIN, 14));
                                UIManager.put("OptionPane.buttonFont", new Font("Tahoma", Font.PLAIN, 14));

                                int confirm = JOptionPane.showConfirmDialog(
                                        Detail.this,
                                        "ต้องการลบ \"" + detail + "\" หรือไม่?",
                                        "ยืนยันการลบ",
                                        JOptionPane.OK_CANCEL_OPTION,
                                        JOptionPane.WARNING_MESSAGE
                                );

                                if (confirm == JOptionPane.OK_OPTION) {
                                    student.deleteDetail(detail);
                                    JOptionPane.showMessageDialog(
                                            Detail.this,
                                            "ลบ \"" + detail + "\" เรียบร้อยแล้ว",
                                            "ผลการลบ",
                                            JOptionPane.INFORMATION_MESSAGE
                                    );
                                    frame.showCustomPanel();
                                }
                            }

                            @Override
                            public void mouseEntered(MouseEvent e) {
                                panel.setBackground(new Color(230, 230, 250));
                            }

                            @Override
                            public void mouseExited(MouseEvent e) {
                                panel.setBackground(Color.WHITE);
                            }
                        });
                    }

                    // 🔹 ถ้า placeNum == 0 → ตรวจสอบเงื่อนไขแล้วเพิ่มปุ่ม "เลื่อนวิชา" เฉพาะบางอัน
                    if (placeNum == 0 && shouldShowMoveButton(detail)) {
                        JButton moveBtn = new JButton("เลื่อนวิชา");
                        moveBtn.setFont(new Font("Tahoma", Font.PLAIN, 13));
                        moveBtn.setFocusPainted(false);
                        moveBtn.setBackground(new Color(220, 220, 255));
                        moveBtn.setBorder(BorderFactory.createCompoundBorder(
                                BorderFactory.createLineBorder(new Color(150, 150, 200), 2),
                                BorderFactory.createEmptyBorder(3, 12, 3, 12)
                        ));

                        // เพิ่มปุ่มไว้ทางขวาสุดของแต่ละ detail
                        panel.add(moveBtn, BorderLayout.EAST);

                        // กำหนดการทำงานของปุ่ม
                        moveBtn.addActionListener(e -> {
                            JOptionPane.showMessageDialog(
                                    Detail.this,
                                    "คุณเลือกเลื่อนวิชา: " + detail,
                                    "เลื่อนวิชา",
                                    JOptionPane.INFORMATION_MESSAGE
                            );
                            
                            // หรือเรียกเมธอดใน frame เช่น:
                            // frame.showMoveSubjectPanel(detail);
                        });

                        // เอฟเฟกต์ hover
                        moveBtn.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseEntered(MouseEvent e) {
                                moveBtn.setBackground(new Color(180, 200, 255));
                            }

                            @Override
                            public void mouseExited(MouseEvent e) {
                                moveBtn.setBackground(new Color(220, 220, 255));
                            }
                        });
                    }

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

    /**
     * ✅ เมธอดตรวจสอบว่ารายวิชานี้ควรมีปุ่ม "เลื่อนวิชา" หรือไม่
     * คุณสามารถเปลี่ยน logic ด้านในให้ตรงกับเงื่อนไขของระบบได้เลย
     */
    private boolean shouldShowMoveButton(String detail) {
        return detail.contains("รอเลื่อนวิชา");
    }
}
