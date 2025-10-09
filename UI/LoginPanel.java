package UI;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class LoginPanel extends JPanel implements ActionListener {
    private AppFrame appFrame;
    private JButton goToTablePanel;
    

    public LoginPanel(AppFrame frame, int width, int height) {
        this.appFrame = frame;
        setLayout(new BorderLayout());




        JLayeredPane layerPane = new JLayeredPane();

        JLabel background = new JLabel(new ImageIcon((new ImageIcon("Icon/Office.jpg")).getImage().getScaledInstance(width, width, Image.SCALE_SMOOTH)));
        background.setBounds(0, 0, width, height);
        layerPane.add(background, JLayeredPane.DEFAULT_LAYER);

        JPanel place = new JPanel();
        place.setOpaque(true);
        place.setBackground(Color.white);
        place.setBounds(width - (int)(width*0.3),0, (int)(width*0.3), height);
        layerPane.add(place, JLayeredPane.PALETTE_LAYER);

        place.setLayout(new GridBagLayout());

        JButton loginButton = new JButton("Login");
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
        place.add(loginButton);

        add(layerPane, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }


}


// package UI;

// import javax.swing.*;
// import java.awt.*;

// public class LoginPanel extends JPanel {
//     private AppFrame appFrame;

//     public LoginPanel(AppFrame frame) {
//         this.appFrame = frame;

//         // ใช้ BorderLayout เพื่อให้ background fill เต็ม
//         setLayout(new BorderLayout());

//         // ขนาดหน้าจอ
//         Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

//         // สร้าง layered pane ที่ขนาดพอดีจอ
//         JLayeredPane layeredPane = new JLayeredPane();
//         layeredPane.setPreferredSize(screenSize);

//         // สร้าง background image label
//         ImageIcon icon = new ImageIcon("Icon/Office.jpg");

//         // Resize รูปให้พอดีกับขนาดจอ
//         Image img = icon.getImage().getScaledInstance(screenSize.width, screenSize.height, Image.SCALE_SMOOTH);
//         JLabel background = new JLabel(new ImageIcon(img));
//         background.setBounds(0, 0, screenSize.width, screenSize.height);
//         layeredPane.add(background, JLayeredPane.DEFAULT_LAYER);

//         // ปุ่ม Login (อยู่ชั้นบน)
//         JButton loginButton = new JButton("Login");
//         loginButton.setBounds(screenSize.width / 2 - 75, screenSize.height / 2 - 20, 150, 40);
//         loginButton.addActionListener(e -> appFrame.showTablePanel());
//         layeredPane.add(loginButton, JLayeredPane.PALETTE_LAYER);

//         // เพิ่ม LayeredPane ลงใน JPanel นี้
//         add(layeredPane, BorderLayout.CENTER);
//     }
// }








// package UI;

// import javax.swing.*;
// import java.awt.*;

// public class LoginPanel extends JPanel {
//     private AppFrame appFrame;

//     public LoginPanel(AppFrame frame) {
//         this.appFrame = frame;
//         setLayout(new BorderLayout());

//         // สร้าง LayeredPane
//         JLayeredPane layeredPane = new JLayeredPane();
//         add(layeredPane, BorderLayout.CENTER);

//         // สร้าง background image panel แบบ custom
//         ScalableImagePanel backgroundPanel = new ScalableImagePanel("Icon/Office.jpg");
//         backgroundPanel.setBounds(0, 0, getWidth(), getHeight());
//         layeredPane.add(backgroundPanel, JLayeredPane.DEFAULT_LAYER);

//         // สร้างปุ่ม Login
//         JButton loginButton = new JButton("Login");
//         loginButton.setSize(150, 40);
//         loginButton.addActionListener(e -> appFrame.showTablePanel());
//         layeredPane.add(loginButton, JLayeredPane.PALETTE_LAYER);

//         // Listener ปรับ layout ตามขนาด panel
//         addComponentListener(new java.awt.event.ComponentAdapter() {
//             public void componentResized(java.awt.event.ComponentEvent evt) {
//                 Dimension size = getSize();
//                 backgroundPanel.setBounds(0, 0, size.width, size.height);

//                 loginButton.setLocation(size.width / 2 - 75, size.height / 2 - 20);
//             }
//         });
//     }

//     // คลาสแสดงภาพพื้นหลังที่ขยายตามขนาด panel
//     static class ScalableImagePanel extends JPanel {
//         private final Image image;

//         public ScalableImagePanel(String path) {
//             this.image = new ImageIcon(path).getImage();
//         }

//         @Override
//         protected void paintComponent(Graphics g) {
//             super.paintComponent(g);
//             // วาดภาพโดยให้เต็มขนาด panel
//             g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
//         }
//     }
// }

