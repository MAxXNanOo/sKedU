package UI;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class LoginPanel extends JPanel implements ActionListener {
    private AppFrame appFrame;
    private JButton goToTablePanel;
    

    public LoginPanel(AppFrame frame, int width, int height, Color backgroundColor, Color sidebarColor, Color chooseIconColor) {
        this.appFrame = frame;
        setLayout(new BorderLayout());




        JLayeredPane layerPane = new JLayeredPane();

        JLabel background = new JLabel(new ImageIcon((new ImageIcon("Icon/Office.jpg")).getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH)));
        background.setBounds(0, 0, width, height);
        layerPane.add(background, JLayeredPane.DEFAULT_LAYER);

        JPanel place = new JPanel();
        place.setOpaque(true);
        place.setBackground(Color.white);
        place.setBounds(width - (int)(width*0.3),0, (int)(width*0.3), height);
        layerPane.add(place, JLayeredPane.PALETTE_LAYER);

        place.setLayout(new GridBagLayout());
        GridBagConstraints grid = new GridBagConstraints();
        grid.weightx=1;
        grid.weighty=0;

        // grid.anchor = GridBagConstraints.CENTER;

        JLabel logo = new JLabel(new ImageIcon((new ImageIcon("Icon/sKedU.png")).getImage().getScaledInstance(303, 123, Image.SCALE_SMOOTH)));
        // logo.setBounds(0, 0, 303, 123);
        grid.gridx = 0;
        grid.gridy = 0;
        grid.insets = new Insets(-(int)(height*0.1), 0, 0, 0);
        place.add(logo, grid);

        JButton loginButton = new JButton("Login");
        grid.gridx = 0;
        grid.gridy = 1;
        grid.insets = new Insets(50, 0, 0, 0);
        loginButton.setBorder(null);
        loginButton.setBorderPainted(false);
        loginButton.setFocusPainted(false);
        loginButton.setContentAreaFilled(false);
        loginButton.setOpaque(true);
        loginButton.setBackground(new Color(2, 179, 113));
        loginButton.setFont(new Font("Tahoma", Font.BOLD, 25));
        loginButton.setForeground(Color.white);
        loginButton.setPreferredSize(new Dimension((int)(width * 0.1), (int)(height * 0.05)));
        loginButton.addActionListener(e -> appFrame.showTablePanel());
        place.add(loginButton, grid);

        add(layerPane, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }


}
