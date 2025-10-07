package UI;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginPanel extends JPanel implements ActionListener {
    private AppFrame appFrame;
    private JButton goToTablePanel;

    public LoginPanel(AppFrame frame) {
        this.appFrame = frame;

        setLayout(new BorderLayout());

        goToTablePanel = new JButton("Login");
        goToTablePanel.addActionListener(e -> {appFrame.showTablePanel();});
        add(goToTablePanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
}


