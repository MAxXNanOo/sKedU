package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class CustomPanel extends JPanel implements ActionListener{
    private AppFrame appFrame;



    public CustomPanel(AppFrame frame, int width, int height, Color backgroundColor, Color sidebarColor, Color chooseIconColor){
        this.appFrame = frame;
        setLayout(null);


        JLayeredPane layer = new JLayeredPane();
        layer.setBounds(0, 0, width, height);

        JPanel background = new JPanel();
        background.setBounds(0, 0, width, height);
        background.setOpaque(true);
        background.setBackground(Color.white);
        layer.add(background, JLayeredPane.DEFAULT_LAYER);

        SidebarPanel sidebar = new SidebarPanel(frame, width, height, backgroundColor, sidebarColor, chooseIconColor, 2);
        sidebar.setOpaque(false);
        sidebar.setBounds(0, 0, 200, height);
        layer.add(sidebar, JLayeredPane.PALETTE_LAYER);


        add(layer, BorderLayout.CENTER);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }


}
