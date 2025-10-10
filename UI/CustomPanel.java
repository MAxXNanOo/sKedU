package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class CustomPanel extends JPanel implements ActionListener{
    private AppFrame appFrame;
    private JButton goToLoginPanel;
    private JButton goToTablePanel;
    private JButton goToSearchPanel;

    //check side bar
    private Timer sidebarTimer = null;
    private boolean isExpanded = false;

    public CustomPanel(AppFrame frame, int width, int height, Color backgroundColor, Color sidebarColor, Color chooseIconColor){
        this.appFrame = frame;
        setLayout(new BorderLayout());


        JLayeredPane layer = new JLayeredPane();

        JPanel background = new JPanel();
        background.setBounds(0, 0, width, height);
        background.setOpaque(true);
        background.setBackground(Color.WHITE);
        layer.add(background, JLayeredPane.DEFAULT_LAYER);

        JPanel sidebar = new JPanel();
        sidebar.setBounds(0, 0, (int)(width*0.06), height);
        sidebar.setOpaque(true);
        sidebar.setBackground(sidebarColor);
        //ใน sidebar
        
            // GridBagConstraints grid = new GridBagConstraints();
            // sidebar.setLayout(new GridBagLayout());
            // grid.anchor = GridBagConstraints.NORTH;
            // grid.insets = new Insets(10, 0, 0,0);
            // grid.weightx = 1;
            // grid.weighty = 1;
            // grid.gridx = 0;

            
            // JLabel profile = new JLabel(new ImageIcon((new ImageIcon("Icon/Pofile.png")).getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH)));
            // grid.gridy = 0;
            // profile.addMouseListener(new MouseAdapter() {
            //     public void mouseReleased(MouseEvent e){ appFrame.showLogin();}
            //     public void mouseEntered(MouseEvent e) { sidebar.setBounds(0, 0, (int)(width*0.15), height);}
            // });
            // sidebar.add(profile, grid);

            // JPanel ligthbar = new JPanel();
            // grid.gridy = 1;
            // grid.weighty = 0.1;
            // ligthbar.setBounds(0, 0, 100, 20);
            // ligthbar.setOpaque(true);   
            // ligthbar.setBackground(Color.white);       
            // sidebar.add(ligthbar, grid);
            // grid.weighty = 1;


            // JLabel goToTablePanel = new JLabel(new ImageIcon((new ImageIcon("Icon/OpenBook.png")).getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH)));
            // grid.gridy = 2;
            // grid.



            GridBagConstraints grid = new GridBagConstraints();
            sidebar.setLayout(new GridBagLayout());
            grid.fill = GridBagConstraints.BOTH;
            grid.weightx = 1;
            grid.weighty = 1;
            grid.gridx = 0;

                JPanel profilPanel = new JPanel();
                grid.gridy = 0;
                profilPanel.setOpaque(true);
                profilPanel.setBackground(sidebarColor);
                sidebar.add(profilPanel, grid);

                JPanel tableIconPanel = new JPanel();
                grid.gridy = 1;
                tableIconPanel.setOpaque(true);
                tableIconPanel.setBackground(sidebarColor);
                sidebar.add(tableIconPanel, grid);

                JPanel searchIconPanel = new JPanel();
                grid.gridy = 2;
                searchIconPanel.setOpaque(true);
                searchIconPanel.setBackground(sidebarColor);
                sidebar.add(searchIconPanel, grid);

                JPanel customIconPanel = new JPanel();
                grid.gridy = 3;
                customIconPanel.setOpaque(true);
                customIconPanel.setBackground(chooseIconColor);
                sidebar.add(customIconPanel, grid);


                JPanel [] free = new JPanel[10];
                int g = 4;
                for(int i=g ; i<8+g ; i++){
                    free[i-g] = new JPanel();
                    grid.gridy = i;
                    free[i-g].setOpaque(true);
                    free[i-g].setBackground(sidebarColor);
                    sidebar.add(free[i-g], grid);
                }


        

            
        


        sidebar.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                // sidebar.setBounds(0, 0, (int)(width*0.15), height);
                animateSidebar(sidebar, true,width);
                profilPanel.setBounds(0, 0, (int)(width*0.15), profilPanel.getHeight());
            }
            public void mouseExited(MouseEvent e){
                // sidebar.setBounds(0, 0, (int)(width*0.05), height);
                // animateSidebar(sidebar, false);
                Point mousePos = MouseInfo.getPointerInfo().getLocation();
                SwingUtilities.convertPointFromScreen(mousePos, sidebar);
                if (!sidebar.contains(mousePos)) {
                    animateSidebar(sidebar, false, width);
                }
            }
        });
        layer.add(sidebar, JLayeredPane.PALETTE_LAYER);






        add(layer, BorderLayout.CENTER);
    }




    //Chat gpt
    //sidebar animation 
    public void animateSidebar(JPanel sidebar, boolean expand, int width) {
        int expandedWidth = 200;
        int collapsedWidth = (int)(width*0.06);
        int step = 10;
        int delay = 10;
        // ถ้าสถานะเหมือนเดิม ไม่ต้องทำอะไร
        if (expand == isExpanded) return;
        isExpanded = expand;

        // หยุด Timer เดิมก่อนเริ่มใหม่
        if (sidebarTimer != null && sidebarTimer.isRunning()) {
            sidebarTimer.stop();
        }

        sidebarTimer = new Timer(delay, null);
        sidebarTimer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int currentWidth = sidebar.getWidth();
                int targetWidth = expand ? expandedWidth : collapsedWidth;

                if ((expand && currentWidth < targetWidth) || (!expand && currentWidth > targetWidth)) {
                    int newWidth = expand
                            ? Math.min(currentWidth + step, targetWidth)
                            : Math.max(currentWidth - step, targetWidth);

                    sidebar.setBounds(sidebar.getX(), sidebar.getY(), newWidth, sidebar.getHeight());
                    sidebar.revalidate();
                    sidebar.repaint();
                } else {
                    sidebarTimer.stop();
                }
            }
        });
        sidebarTimer.start();
    }










    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }


}
