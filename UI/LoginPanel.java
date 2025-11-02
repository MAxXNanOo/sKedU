package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import Subject.*;

public class LoginPanel extends JPanel implements ActionListener {
    private AppFrame appFrame;
    private JButton goToTablePanel;

    static class RoundedPanel extends JPanel {
        private int cornerRadius;

        public RoundedPanel(int radius) {
            super();
            this.cornerRadius = radius;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);
            g2.dispose();
        }
    }

    class RoundedButton extends JButton {
        private int cornerRadius;

        public RoundedButton(String text, int radius) {
            super(text);
            this.cornerRadius = radius;
            setContentAreaFilled(false);
            setFocusPainted(false);
            setBorderPainted(false);
            setForeground(Color.white);
            setFont(new Font("Tahoma", Font.BOLD, 25));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);
            g2.dispose();
            super.paintComponent(g);
        }
    }

    class RoundedTextField extends JTextField {
        private int cornerRadius;

        public RoundedTextField(int columns, int radius) {
            super(columns);
            this.cornerRadius = radius;
            setOpaque(false);
            setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
            setFont(new Font("Tahoma", Font.PLAIN, 18));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground() != null ? getBackground() : Color.white);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);
            g2.dispose();
            super.paintComponent(g);
        }

        @Override
        protected void paintBorder(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(Color.gray);
            g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, cornerRadius, cornerRadius);
            g2.dispose();
        }
    }


    public LoginPanel(AppFrame frame, int width, int height, StudentData studentData, Data data) {
        this.appFrame = frame;
        setLayout(new BorderLayout());

        JLayeredPane layerPane = new JLayeredPane();

        JLabel background = new JLabel(new ImageIcon((new ImageIcon("Icon/Office.jpg")).getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH)));
        background.setBounds(0, 0, width, height);
        layerPane.add(background, JLayeredPane.DEFAULT_LAYER);

        int panelWidth = (int) (width * 0.35);
        int panelHeight = (int) (height * 0.8);
        int panelX = (width - panelWidth) / 2;
        int panelY = (height - panelHeight) / 2;

        RoundedPanel place = new RoundedPanel(30);
        Color semiTransparentWhite = new Color(255, 255, 255, 190);
        place.setBackground(semiTransparentWhite);
        place.setOpaque(false);
        place.setBounds(panelX, panelY, panelWidth, panelHeight);
        layerPane.add(place, JLayeredPane.PALETTE_LAYER);


        place.setLayout(new GridBagLayout());
        GridBagConstraints grid = new GridBagConstraints();
        grid.weightx = 1;
        grid.weighty = 0;

        JLabel logo = new JLabel(new ImageIcon((new ImageIcon("Icon/croplogo.png")).getImage().getScaledInstance(350, 210, Image.SCALE_SMOOTH)));
        grid.gridx = 0;
        grid.gridy = 0;
        grid.insets = new Insets(0, 0, 50, 0);
        grid.anchor = GridBagConstraints.NORTH;
        place.add(logo, grid);

        ImageIcon smallLogoIcon = new ImageIcon(new ImageIcon("Icon/KU_Logo.png").getImage().getScaledInstance(70, 80, Image.SCALE_SMOOTH));
        JLabel smallLogo = new JLabel(smallLogoIcon);
        int logoX = width - 80 - 20;
        int logoY = 20;
        smallLogo.setBounds(logoX, logoY, 80, 80);
        layerPane.add(smallLogo, JLayeredPane.PALETTE_LAYER);


        RoundedTextField usernameField = new RoundedTextField(19, 15);
        usernameField.setText("username");
        usernameField.setBackground(Color.white);
        usernameField.setForeground(Color.GRAY);
        grid.gridy = 1;
        grid.insets = new Insets(10, 0, 10, 0);
        place.add(usernameField, grid);

        RoundedTextField passwordField = new RoundedTextField(19, 15);
        passwordField.setText("password");
        passwordField.setBackground(Color.white);
        passwordField.setForeground(Color.GRAY);
        grid.gridy = 2;
        place.add(passwordField, grid);

        usernameField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (usernameField.getText().equals("username")) {
                    usernameField.setText("");
                    usernameField.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (usernameField.getText().isEmpty()) {
                    usernameField.setText("username");
                    usernameField.setForeground(Color.GRAY);
                }
            }
        });

        passwordField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (passwordField.getText().equals("password")) {
                    passwordField.setText("");
                    passwordField.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (passwordField.getText().isEmpty()) {
                    passwordField.setText("password");
                    passwordField.setForeground(Color.GRAY);
                }
            }
        });


        RoundedButton loginButton = new RoundedButton("Login", 20);
        loginButton.setBackground(new Color(2, 179, 113));
        loginButton.setPreferredSize(new Dimension((int) (width * 0.09), (int) (height * 0.05)));
        grid.gridy = 3;
        grid.insets = new Insets(20, 0, 0, 0);
        
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            
            if (username.equals("username") || password.equals("password")) {
                JOptionPane.showMessageDialog(appFrame, "Please enter your valid username and password.", "Login Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (studentData.validateLogin(username, password)) {
                System.out.println("Login successful!");
                appFrame.showTablePanel();
            } else {
                System.out.println("Invalid username or password.");
                JOptionPane.showMessageDialog(appFrame, "Invalid username or password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
        });

        place.add(loginButton, grid);

        add(layerPane, BorderLayout.CENTER);
        
        setFocusable(true);
        
        SwingUtilities.invokeLater(() -> requestFocusInWindow());
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

}
















// package UI;


// import javax.swing.*;
// import java.awt.*;
// import java.awt.event.*;

// import Subject.*;

// public class LoginPanel extends JPanel implements ActionListener {
//     private AppFrame appFrame;
//     private JButton goToTablePanel;
    

//     public LoginPanel(AppFrame frame, int width, int height, StudentData studentData, Data data) {
//         this.appFrame = frame;
//         setLayout(new BorderLayout());




//         JLayeredPane layerPane = new JLayeredPane();

//         JLabel background = new JLabel(new ImageIcon((new ImageIcon("Icon/Office.jpg")).getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH)));
//         background.setBounds(0, 0, width, height);
//         layerPane.add(background, JLayeredPane.DEFAULT_LAYER);

//         JPanel place = new JPanel();
//         place.setOpaque(true);
//         place.setBackground(Color.white);
//         place.setBounds(width - (int)(width*0.3),0, (int)(width*0.3), height);
//         layerPane.add(place, JLayeredPane.PALETTE_LAYER);

//             place.setLayout(new GridBagLayout());
//             GridBagConstraints grid = new GridBagConstraints();
//             grid.weightx=1;
//             grid.weighty=0;

//             JLabel logo = new JLabel(new ImageIcon((new ImageIcon("Icon/logosKedU.png")).getImage().getScaledInstance(303, 400, Image.SCALE_SMOOTH)));
//             // logo.setBounds(0, 0, 303, 123);
//             grid.gridx = 0;
//             grid.gridy = 0;
//             grid.insets = new Insets(-(int)(height*0.1), 0, 0, 0);
//             place.add(logo, grid);

//             JTextField usernameField = new JTextField(20);
//             usernameField.setText("username");
//             grid.gridx = 0;
//             grid.gridy = 1;
//             // usernameField.setPreferredSize(new Dimension(100,30));
//             grid.insets = new Insets(20,0,0,0);
//             place.add(usernameField, grid);

//             JTextField passwordField = new JTextField(20);
//             passwordField.setText("password");
//             grid.gridx = 0;
//             grid.gridy = 2;
//             grid.insets = new Insets(20,0,0,0);
//             place.add(passwordField, grid);


//             JButton loginButton = new JButton("Login");
//             grid.gridx = 0;
//             grid.gridy = 3;
//             grid.insets = new Insets(50, 0, 0, 0);
//             loginButton.setBorder(null);
//             loginButton.setBorderPainted(false);
//             loginButton.setFocusPainted(false);
//             loginButton.setContentAreaFilled(false);
//             loginButton.setOpaque(true);
//             loginButton.setBackground(new Color(2, 179, 113));
//             loginButton.setFont(new Font("Tahoma", Font.BOLD, 25));
//             loginButton.setForeground(Color.white);
//             loginButton.setPreferredSize(new Dimension((int)(width * 0.1), (int)(height * 0.05)));
//             loginButton.addActionListener(e -> { 
//                 String username =  usernameField.getText();
//                 String password =  passwordField.getText();
//                 if (studentData.validateLogin(username, password)) {
//                     System.out.println("Login successful!");
//                     appFrame.showTablePanel();
//                 } else {
//                     System.out.println("Invalid username or password.");
//                 }

//             });
//             place.add(loginButton, grid);

//         add(layerPane, BorderLayout.CENTER);
//     }

//     @Override
//     public void actionPerformed(ActionEvent e) {
        
//     }


// }
