package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TablePanel extends JPanel implements ActionListener{
    private AppFrame appFrame;
    private JButton goToLoginPanel;
    private JButton goToSearchPanel;
    private JButton goToCustomPanel;

    public TablePanel(AppFrame frame){
        this.appFrame = frame;

        ImageIcon orgImg = new ImageIcon("Icon/Pofile.png");
        Image sclImg = orgImg.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
        goToLoginPanel = new JButton(new ImageIcon(sclImg));
        goToLoginPanel.addActionListener(e -> {appFrame.showLogin();});

        add(goToLoginPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        appFrame.showLogin();
    }

}
