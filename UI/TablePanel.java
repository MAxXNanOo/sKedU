package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TablePanel extends JPanel implements ActionListener{
    private AppFrame appFrame;
    private JButton goToLoginPanel;
    private JButton goToSearchPanel;
    private JButton goToCustomPanel;

    public TablePanel(AppFrame frame, int width, int height, Color backgroundColor, Color sidebarColor, Color chooseIconColor){
        this.appFrame = frame;

        this.appFrame = frame;
        setLayout(null);


        JLayeredPane layer = new JLayeredPane();
        layer.setBounds(0, 0, width, height);

        JPanel background = new JPanel();
        background.setBounds(0, 0, width, height);
        background.setOpaque(true);
        background.setBackground(Color.black);
        background.setLayout(null);
            Detail detail = new Detail("014320112-65", "Data_I");
            detail.setOpaque(false);
            detail.setBounds(0,0,320,160);
            background.add(detail);

        layer.add(background, JLayeredPane.DEFAULT_LAYER);


        SidebarPanel sidebar = new SidebarPanel(frame, width, height, backgroundColor, sidebarColor, chooseIconColor, 0);
        sidebar.setOpaque(false);
        sidebar.setBounds(0, 0, 200, height);
        layer.add(sidebar, JLayeredPane.PALETTE_LAYER);





        add(layer, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        appFrame.showLogin();
    }

}
