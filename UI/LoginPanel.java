package UI;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginPanel extends JPanel implements ActionListener {
    private AppFrame appFrame;
    private JButton goToTablePanel;

    public LoginPanel(AppFrame frame) {
        this.appFrame = frame;

        // setLayout(new GridBagLayout());
        // GridBagConstraints grid =   new GridBagConstraints();
        // grid.fill = GridBagConstraints.BOTH;

    
        // JLabel Bg = new JLabel(new ImageIcon("Icon/Office.jpg"));
        // grid.weightx=1;
        // grid.weighty=1;
        // grid.gridx = 0;
        // grid.gridy = 0;
        // grid.gridwidth = 2;
        // add(Bg,grid);
        
        // goToTablePanel = new JButton("Login");
        // goToTablePanel.addActionListener(e -> {appFrame.showTablePanel();});
        // grid.gridx = 2;
        // grid.gridy = 0;
        // grid.gridwidth = 1;
        // add(goToTablePanel, grid);


        JLayeredPane layerPane = new JLayeredPane();
        layerPane.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());

        JLabel background = new JLabel(new ImageIcon("Icon/Office.jpg"));
        layerPane.add(background, JLayeredPane.DEFAULT_LAYER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
}


