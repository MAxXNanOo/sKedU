// package UI;


// import javax.swing.*;
// import java.awt.*;
// import java.awt.event.*;

// public class LoginPanel extends JPanel implements ActionListener {
//     private AppFrame appFrame;
//     private JButton goToTablePanel;

//     public LoginPanel(AppFrame frame) {
//         this.appFrame = frame;

//         // setLayout(new GridBagLayout());
//         // GridBagConstraints grid =   new GridBagConstraints();
//         // grid.fill = GridBagConstraints.BOTH;

    
//         // JLabel Bg = new JLabel(new ImageIcon("Icon/Office.jpg"));
//         // grid.weightx=1;
//         // grid.weighty=1;
//         // grid.gridx = 0;
//         // grid.gridy = 0;
//         // grid.gridwidth = 2;
//         // add(Bg,grid);
        
//         // goToTablePanel = new JButton("Login");
//         // goToTablePanel.addActionListener(e -> {appFrame.showTablePanel();});
//         // grid.gridx = 2;
//         // grid.gridy = 0;
//         // grid.gridwidth = 1;
//         // add(goToTablePanel, grid);


//         JLayeredPane layerPane = new JLayeredPane();
//         layerPane.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());

//         JLabel background = new JLabel(new ImageIcon("Icon/Office.jpg"));
//         background.setOpaque(true);
//         background.setBackground(Color.blue);
//         layerPane.add(background, JLayeredPane.DEFAULT_LAYER);

//         JLabel place = new JLabel();
//         place.setOpaque(true);
//         place.setBackground(Color.GREEN);
//         layerPane.add(place, JLayeredPane.PALETTE_LAYER);

//         add(layerPane, BorderLayout.CENTER);
//     }

//     @Override
//     public void actionPerformed(ActionEvent e) {
        
//     }
// }


package UI;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {
    private AppFrame appFrame;

    public LoginPanel(AppFrame frame) {
        this.appFrame = frame;

        // ใช้ BorderLayout เพื่อให้ background fill เต็ม
        setLayout(new BorderLayout());

        // ขนาดหน้าจอ
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        // สร้าง layered pane ที่ขนาดพอดีจอ
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(screenSize);

        // สร้าง background image label
        ImageIcon icon = new ImageIcon("Icon/Office.jpg");

        // Resize รูปให้พอดีกับขนาดจอ
        Image img = icon.getImage().getScaledInstance(screenSize.width, screenSize.height, Image.SCALE_SMOOTH);
        JLabel background = new JLabel(new ImageIcon(img));
        background.setBounds(0, 0, screenSize.width, screenSize.height);
        layeredPane.add(background, JLayeredPane.DEFAULT_LAYER);

        // ปุ่ม Login (อยู่ชั้นบน)
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(screenSize.width / 2 - 75, screenSize.height / 2 - 20, 150, 40);
        loginButton.addActionListener(e -> appFrame.showTablePanel());
        layeredPane.add(loginButton, JLayeredPane.PALETTE_LAYER);

        // เพิ่ม LayeredPane ลงใน JPanel นี้
        add(layeredPane, BorderLayout.CENTER);
    }
}
