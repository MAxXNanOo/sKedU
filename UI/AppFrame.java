package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import Subject.*;

public class AppFrame extends JFrame {
    CardLayout cardLayout;
    JPanel cardPanel;

    Data data = new Data("KUdata/KUKPSForTest.csv");
    StudentData studentData = new StudentData("KUdata/student.csv");

    public AppFrame(int width, int height) {
        setTitle("sKedU");
        setSize(width, height); //16:9 เท่านั้น
        setResizable(false);
        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

// Load data
        studentData.setStudents();
        data.setSubjects();

        // ตั้งค่า CardLayout
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // สร้าง panel ทั้งสอง
        LoginPanel loginPanel = new LoginPanel(this, width, height, studentData);
        SearchPanel searchPanel = new SearchPanel(this, width, height);
        TablePanel tablePanel = new TablePanel(this, width, height);
        CustomPanel customPanel = new CustomPanel(this, width, height);

        // เพิ่ม panel ลงใน cardPanel
        cardPanel.add(loginPanel, "Login Panel");
        cardPanel.add(searchPanel, "Search Panel");
        cardPanel.add(tablePanel, "Table Panel");
        cardPanel.add(customPanel, "Custom Panel");

        add(cardPanel);
        setVisible(true);

        // แสดงหน้า login ก่อน
        showLogin();
        // showCustomPanel();
    }

    public void showLogin() {
        cardLayout.show(cardPanel, "Login Panel");
        System.out.println("function : showLogin()");
    }
    public void showTablePanel() {
        // Point loc = getLocation();
        cardLayout.show(cardPanel, "Table Panel");
        System.out.println("function : showTablePanel()");
        // setLocation(loc); // คงตำแหน่งเดิม
    }
    public void showSearchPanel(){
        cardLayout.show(cardPanel, "Search Panel");
        System.out.println("function : showSearchPanel()");
    }
    public void showCustomPanel(){
        cardLayout.show(cardPanel, "Custom Panel");
        System.out.println("function : showCustomPanel()");
    }
    
}

