package UI;

import javax.swing.*;
import java.awt.*;
import Subject.*;
import java.awt.event.*;

public class SearchPanel extends JPanel {
    private AppFrame appFrame;
    private SidebarPanel sidebar;

    public SearchPanel(AppFrame frame, int width, int height, StudentData studentData, Data data) {
        this.appFrame = frame;
        setLayout(null);

        JLayeredPane layer = new JLayeredPane();
        layer.setBounds(0, 0, width, height);

        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBounds(0, 0, width, height);
        mainPanel.setBackground(Color.WHITE);

        // Search component
        Search search = new Search(studentData, data, frame, 1);
        mainPanel.add(search, BorderLayout.CENTER);

        layer.add(mainPanel, JLayeredPane.DEFAULT_LAYER);

        // Sidebar
        sidebar = new SidebarPanel(frame, width, height, 1, studentData, data);
        sidebar.setOpaque(false);
        sidebar.setBounds(0, 0, 200, height);
        layer.add(sidebar, JLayeredPane.PALETTE_LAYER);

        add(layer, BorderLayout.CENTER);
    }
}
