package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import Subject.*;

public class TablePanel extends JPanel implements ActionListener{
    private AppFrame appFrame;
    private JButton goToLoginPanel;
    private JButton goToSearchPanel;
    private JButton goToCustomPanel;

    public TablePanel(AppFrame frame, int width, int height, StudentData studentData){
        this.appFrame = frame;

        this.appFrame = frame;
        setLayout(null);


        JLayeredPane layer = new JLayeredPane();
        layer.setBounds(0, 0, width, height);

        JPanel background = new JPanel();
        background.setBounds(0, 0, width, height);
        background.setOpaque(true);
        background.setBackground(Color.WHITE);
        background.setLayout(null);
            Table table = new Table(studentData);
            table.setOpaque(false);
            table.setBounds(125, 25, 1080, 400);
            background.add(table);

            Detail detail = new Detail(studentData);
            detail.setOpaque(false);
            detail.setBounds(320,500,320,160);
            background.add(detail);

        layer.add(background, JLayeredPane.DEFAULT_LAYER);


        SidebarPanel sidebar = new SidebarPanel(frame, width, height, 0, studentData);
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
