package UI;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import Subject.*;

public class LoginPanel extends JPanel implements ActionListener {
    private AppFrame appFrame;
    private JButton goToTablePanel;
    

    public LoginPanel(AppFrame frame, int width, int height, StudentData studentData) {
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

            JLabel logo = new JLabel(new ImageIcon((new ImageIcon("Icon/sKedU.png")).getImage().getScaledInstance(303, 123, Image.SCALE_SMOOTH)));
            // logo.setBounds(0, 0, 303, 123);
            grid.gridx = 0;
            grid.gridy = 0;
            grid.insets = new Insets(-(int)(height*0.1), 0, 0, 0);
            place.add(logo, grid);

            JTextField usernameField = new JTextField(20);
            usernameField.setText("username");
            grid.gridx = 0;
            grid.gridy = 1;
            // usernameField.setPreferredSize(new Dimension(100,30));
            grid.insets = new Insets(20,0,0,0);
            place.add(usernameField, grid);

            JTextField passwordField = new JTextField(20);
            passwordField.setText("password");
            grid.gridx = 0;
            grid.gridy = 2;
            grid.insets = new Insets(20,0,0,0);
            place.add(passwordField, grid);


            JButton loginButton = new JButton("Login");
            grid.gridx = 0;
            grid.gridy = 3;
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
            loginButton.addActionListener(e -> { 
                String username =  usernameField.getText();
                String password =  passwordField.getText();
                if (studentData.validateLogin(username, password)) {
                    System.out.println("Login successful!");
                    appFrame.showTablePanel();
                } else {
                    System.out.println("Invalid username or password.");
                }

            });
            place.add(loginButton, grid);

        add(layerPane, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }


}
