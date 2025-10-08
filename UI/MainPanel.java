package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainPanel extends JPanel implements ActionListener {
    private AppFrame appFrame;
    private JButton logoutButton;

    public MainPanel(AppFrame frame) {
        this.appFrame = frame;

        setLayout(new BorderLayout());
        JLabel label = new JLabel("Main Page", JLabel.CENTER);

        logoutButton = new JButton("Logout");
        logoutButton.addActionListener(this);

        add(label, BorderLayout.CENTER);
        add(logoutButton, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        appFrame.showLogin(); // กลับไปหน้า Login
    }
}


