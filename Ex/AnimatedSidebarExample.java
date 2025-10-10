package Ex;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AnimatedSidebarExample extends JFrame {
    private JPanel sidebar;
    private int sidebarExpandedWidth = 200;
    private int sidebarCollapsedWidth = 50;
    private int animationStep = 10;
    private int animationDelay = 10; // milliseconds

    private boolean isExpanded = false;
    private Timer expandTimer;
    private Timer collapseTimer;

    public AnimatedSidebarExample() {
        setTitle("Animated Sidebar Example");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null); // ใช้ layout แบบ absolute positioning

        // ----- Sidebar -----
        sidebar = new JPanel();
        sidebar.setBackground(new Color(47, 51, 55));
        sidebar.setBounds(0, 0, sidebarCollapsedWidth, getHeight());
        sidebar.setLayout(null); // จัดวางเองได้ตามใจ

        JLabel menuLabel = new JLabel("เมนู");
        menuLabel.setForeground(Color.WHITE);
        menuLabel.setBounds(10, 10, 100, 30);
        sidebar.add(menuLabel);

        add(sidebar);

        // ----- Mouse Listener -----
        sidebar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                startExpandAnimation();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // ตรวจว่า mouse หลุดออกจาก panel จริงๆ (ไม่ใช่ลูก component)
                if (!sidebar.getBounds().contains(e.getPoint())) {
                    startCollapseAnimation();
                }
            }
        });

        // ----- Timers -----
        expandTimer = new Timer(animationDelay, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                int currentWidth = sidebar.getWidth();
                if (currentWidth < sidebarExpandedWidth) {
                    sidebar.setBounds(0, 0, currentWidth + animationStep, getHeight());
                    sidebar.revalidate();
                    sidebar.repaint();
                } else {
                    expandTimer.stop();
                    isExpanded = true;
                }
            }
        });

        collapseTimer = new Timer(animationDelay, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                int currentWidth = sidebar.getWidth();
                if (currentWidth > sidebarCollapsedWidth) {
                    sidebar.setBounds(0, 0, currentWidth - animationStep, getHeight());
                    sidebar.revalidate();
                    sidebar.repaint();
                } else {
                    collapseTimer.stop();
                    isExpanded = false;
                }
            }
        });

        // ให้ sidebar อยู่ด้านบนเสมอ
        getLayeredPane().add(sidebar, JLayeredPane.PALETTE_LAYER);

        // Listener ตรวจ mouse ออกจาก window
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                if (isExpanded && !sidebar.getBounds().contains(e.getPoint())) {
                    startCollapseAnimation();
                }
            }
        });
    }

    private void startExpandAnimation() {
        collapseTimer.stop();
        expandTimer.start();
    }

    private void startCollapseAnimation() {
        expandTimer.stop();
        collapseTimer.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AnimatedSidebarExample().setVisible(true);
        });
    }
}
