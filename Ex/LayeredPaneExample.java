package Ex;

import javax.swing.*;
import java.awt.*;

public class LayeredPaneExample extends JFrame {

    public LayeredPaneExample() {
        setTitle("JLayeredPane Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // เต็มจอ

        // ✅ สร้าง JLayeredPane
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());

        // ✅ สร้างพื้นหลัง (Label ที่มีภาพหรือสี)
        JLabel background = new JLabel();
        background.setOpaque(true);
        background.setBackground(Color.GRAY);
        background.setBounds(0, 0, 1920, 1080); // ต้อง setBounds
        layeredPane.add(background, JLayeredPane.DEFAULT_LAYER);

        // ✅ ปุ่มลอยบนพื้นหลัง
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(800, 400, 200, 50); // ตำแหน่ง x, y และขนาด
        layeredPane.add(loginButton, JLayeredPane.PALETTE_LAYER); // ชั้นบน

        // ✅ เพิ่ม JLayeredPane ลงใน JFrame
        setContentPane(layeredPane);
        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LayeredPaneExample::new);
    }
}

