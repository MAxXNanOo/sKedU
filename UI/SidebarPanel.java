package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import Subject.*;

public class SidebarPanel extends JPanel {
    private AppFrame appFrame;
    private JButton goToLoginPanel;
    private JButton goToTablePanel;
    private JButton goToSearchPanel;


    //check side bar
    private Timer sidebarTimer = null;
    private boolean isExpanded = false;

    Color backgroundColor = new Color(255, 255, 255);
    Color sidebarColor = new Color(47, 51, 55);
    Color chooseIconColor = new Color(3, 169, 107);



    public SidebarPanel(AppFrame frame, int width, int height, int panelIndex, StudentData studentData, Data data) {
        this.appFrame = frame;

        setLayout(null);
        JPanel sidebar = new JPanel();
        sidebar.setBounds(0, 0, (int)(width*0.05), height);
        sidebar.setOpaque(true);
        sidebar.setBackground(sidebarColor);

            GridBagConstraints grid = new GridBagConstraints();
            sidebar.setLayout(new GridBagLayout());
            grid.fill = GridBagConstraints.BOTH;
            grid.weightx = 1;
            grid.weighty = 1;
            grid.gridx = 0;

                JPanel profilePanel = new JPanel();
                grid.gridy = 0;
                profilePanel.setOpaque(true);
                profilePanel.setBackground(sidebarColor);
                sidebar.add(profilePanel, grid);
                    addText(profilePanel, "Icon/Pofile.png", studentData.getStudentLogin().getStudentName(), 0);


                JPanel tableIconPanel = new JPanel();
                grid.gridy = 1;
                tableIconPanel.setOpaque(true);
                tableIconPanel.setBackground(sidebarColor);
                sidebar.add(tableIconPanel, grid);
                    addText(tableIconPanel, "Icon/tablePanelIcon.png", "ตารางเรียน", 0);
                    

                JPanel searchIconPanel = new JPanel();
                grid.gridy = 2;
                searchIconPanel.setOpaque(true);
                searchIconPanel.setBackground(sidebarColor);
                sidebar.add(searchIconPanel, grid);
                    addText(searchIconPanel, "Icon/SearchPanelIcon.png", "รายวิชาที่เปิดให้ลงทะเบียน", 0);

                JPanel customIconPanel = new JPanel();
                grid.gridy = 3;
                // customIconPanel.setOpaque(true);
                customIconPanel.setBackground(sidebarColor);
                sidebar.add(customIconPanel, grid);
                    addText(customIconPanel, "Icon/CustomPanelIcon.png", "ลงทะเบียนเรียน", 0);


                JPanel [] free = new JPanel[10];
                int g = 4;
                for(int i=g ; i<8+g ; i++){
                    free[i-g] = new JPanel();
                    grid.gridy = i;
                    free[i-g].setOpaque(true);
                    free[i-g].setBackground(sidebarColor);
                    sidebar.add(free[i-g], grid);
                }



        if(panelIndex == 0)
            tableIconPanel.setBackground(chooseIconColor);
        else if(panelIndex == 1)
            searchIconPanel.setBackground(chooseIconColor);
        else if(panelIndex == 2)
            customIconPanel.setBackground(chooseIconColor);

        sidebar.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                animateSidebar(sidebar, true,width);

                addText(profilePanel, "Icon/Pofile.png", studentData.getStudentLogin().getStudentName(), 1);
                addText(tableIconPanel, "Icon/tablePanelIcon.png", "ตารางเรียน", 1);
                addText(searchIconPanel, "Icon/SearchPanelIcon.png", "รายวิชาที่เปิดให้ลงทะเบียน", 1);
                addText(customIconPanel, "Icon/CustomPanelIcon.png", "ลงทะเบียนเรียน", 1);
            }
            public void mouseExited(MouseEvent e){
                Point mousePos = MouseInfo.getPointerInfo().getLocation();
                SwingUtilities.convertPointFromScreen(mousePos, sidebar);
                if (!sidebar.contains(mousePos)) {
                    animateSidebar(sidebar, false, width);

                    addText(profilePanel, "Icon/Pofile.png", studentData.getStudentLogin().getStudentName(), 0);
                    addText(tableIconPanel, "Icon/tablePanelIcon.png", "ตารางเรียน", 0);
                    addText(searchIconPanel, "Icon/SearchPanelIcon.png", "รายวิชาที่เปิดให้ลงทะเบียน", 0);
                    addText(customIconPanel, "Icon/CustomPanelIcon.png", "ลงทะเบียนเรียน", 0);
                } 
            }
        });
            profilePanel.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    appFrame.showLogin();
                }
                public void mouseEntered(MouseEvent e) {
                    animateSidebar(sidebar, true,width);
                    
                    addText(profilePanel, "Icon/Pofile.png", studentData.getStudentLogin().getStudentName(), 1);
                    addText(tableIconPanel, "Icon/tablePanelIcon.png", "ตารางเรียน", 1);
                    addText(searchIconPanel, "Icon/SearchPanelIcon.png", "รายวิชาที่เปิดให้ลงทะเบียน", 1);
                    addText(customIconPanel, "Icon/CustomPanelIcon.png", "ลงทะเบียนเรียน", 1);
                }
                public void mouseExited(MouseEvent e){
                    Point mousePos = MouseInfo.getPointerInfo().getLocation();
                    SwingUtilities.convertPointFromScreen(mousePos, sidebar);
                    if (!sidebar.contains(mousePos)) {
                        animateSidebar(sidebar, false, width);
                        
                        addText(profilePanel, "Icon/Pofile.png", studentData.getStudentLogin().getStudentName(), 0);
                        addText(tableIconPanel, "Icon/tablePanelIcon.png", "ตารางเรียน", 0);
                        addText(searchIconPanel, "Icon/SearchPanelIcon.png", "รายวิชาที่เปิดให้ลงทะเบียน", 0);
                        addText(customIconPanel, "Icon/CustomPanelIcon.png", "ลงทะเบียนเรียน", 0);
                    } 
                }
            });
            tableIconPanel.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    appFrame.showTablePanel();
                }
                public void mouseEntered(MouseEvent e) {
                    animateSidebar(sidebar, true,width);
                    
                    addText(profilePanel, "Icon/Pofile.png", studentData.getStudentLogin().getStudentName(), 1);
                    addText(tableIconPanel, "Icon/tablePanelIcon.png", "ตารางเรียน", 1);
                    addText(searchIconPanel, "Icon/SearchPanelIcon.png", "รายวิชาที่เปิดให้ลงทะเบียน", 1);
                    addText(customIconPanel, "Icon/CustomPanelIcon.png", "ลงทะเบียนเรียน", 1);
                }
                public void mouseExited(MouseEvent e){
                    Point mousePos = MouseInfo.getPointerInfo().getLocation();
                    SwingUtilities.convertPointFromScreen(mousePos, sidebar);
                    if (!sidebar.contains(mousePos)) {
                        animateSidebar(sidebar, false, width);
                        
                        addText(profilePanel, "Icon/Pofile.png", studentData.getStudentLogin().getStudentName(), 0);
                        addText(tableIconPanel, "Icon/tablePanelIcon.png", "ตารางเรียน", 0);
                        addText(searchIconPanel, "Icon/SearchPanelIcon.png", "รายวิชาที่เปิดให้ลงทะเบียน", 0);
                        addText(customIconPanel, "Icon/CustomPanelIcon.png", "ลงทะเบียนเรียน", 0);
                    } 
                }
            });
            searchIconPanel.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    appFrame.showSearchPanel();
                }
                public void mouseEntered(MouseEvent e) {
                    animateSidebar(sidebar, true,width);
                    
                    addText(profilePanel, "Icon/Pofile.png", studentData.getStudentLogin().getStudentName(), 1);
                    addText(tableIconPanel, "Icon/tablePanelIcon.png", "ตารางเรียน", 1);
                    addText(searchIconPanel, "Icon/SearchPanelIcon.png", "รายวิชาที่เปิดให้ลงทะเบียน", 1);
                    addText(customIconPanel, "Icon/CustomPanelIcon.png", "ลงทะเบียนเรียน", 1);
                }
                public void mouseExited(MouseEvent e){
                    Point mousePos = MouseInfo.getPointerInfo().getLocation();
                    SwingUtilities.convertPointFromScreen(mousePos, sidebar);
                    if (!sidebar.contains(mousePos)) {
                        animateSidebar(sidebar, false, width);
                        
                        addText(profilePanel, "Icon/Pofile.png", studentData.getStudentLogin().getStudentName(), 0);
                        addText(tableIconPanel, "Icon/tablePanelIcon.png", "ตารางเรียน", 0);
                        addText(searchIconPanel, "Icon/SearchPanelIcon.png", "รายวิชาที่เปิดให้ลงทะเบียน", 0);
                        addText(customIconPanel, "Icon/CustomPanelIcon.png", "ลงทะเบียนเรียน", 0);
                    } 
                }
            });
            customIconPanel.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    appFrame.showCustomPanel();
                }
                public void mouseEntered(MouseEvent e) {
                    animateSidebar(sidebar, true,width);
                    
                    addText(profilePanel, "Icon/Pofile.png", studentData.getStudentLogin().getStudentName(), 1);
                    addText(tableIconPanel, "Icon/tablePanelIcon.png", "ตารางเรียน", 1);
                    addText(searchIconPanel, "Icon/SearchPanelIcon.png", "รายวิชาที่เปิดให้ลงทะเบียน", 1);
                    addText(customIconPanel, "Icon/CustomPanelIcon.png", "ลงทะเบียนเรียน", 1);
                }
                public void mouseExited(MouseEvent e){
                    Point mousePos = MouseInfo.getPointerInfo().getLocation();
                    SwingUtilities.convertPointFromScreen(mousePos, sidebar);
                    if (!sidebar.contains(mousePos)) {
                        animateSidebar(sidebar, false, width);
                        
                        addText(profilePanel, "Icon/Pofile.png", studentData.getStudentLogin().getStudentName(), 0);
                        addText(tableIconPanel, "Icon/tablePanelIcon.png", "ตารางเรียน", 0);
                        addText(searchIconPanel, "Icon/SearchPanelIcon.png", "รายวิชาที่เปิดให้ลงทะเบียน", 0);
                        addText(customIconPanel, "Icon/CustomPanelIcon.png", "ลงทะเบียนเรียน", 0);
                    } 
                }
            });



        add(sidebar);
    }


        //Chat gpt
    //sidebar animation 
    public void animateSidebar(JPanel sidebar, boolean expand, int width) {
        int expandedWidth = 200;
        int collapsedWidth = (int)(width*0.05);
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





    public void addText(JPanel panel, String path, String text, int choose){
        panel.removeAll();

        if(choose == 0){
            panel.setLayout(new BorderLayout());
            JLabel tableIcon = new JLabel(new ImageIcon(
                (new ImageIcon(path))
                .getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH)
            ));

            // tableIcon.setBounds(50,0,32,32);
            JLabel tableText = new JLabel(" ");
            tableText.setForeground(Color.WHITE);
            tableText.setFont(new Font("Tahoma", Font.BOLD, 14));
            panel.add(tableIcon, BorderLayout.CENTER);
            panel.add(tableText, BorderLayout.EAST);
            // panel.setBounds(0,0,(int)(1280*0.05), 720);
        }
        else if(choose == 1){
            panel.setLayout(new BorderLayout());
            JLabel tableIcon = new JLabel(new ImageIcon(
                (new ImageIcon(path))
                .getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH)
            ));
            JLabel tableText = new JLabel(text);
            tableText.setForeground(Color.WHITE);
            tableText.setFont(new Font("Tahoma", Font.BOLD, 14));
            panel.add(tableIcon, BorderLayout.WEST);
            panel.add(tableText, BorderLayout.EAST);
        }
    }
}