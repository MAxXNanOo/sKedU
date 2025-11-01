package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import Subject.*;

public class AppFrame extends JFrame {
    CardLayout cardLayout;
    JPanel cardPanel;

    Data data = new Data("KUdata\\KUKPS.csv");
    StudentData studentData = new StudentData("KUdata\\student.csv", data);

    int width, height;

    public AppFrame(int width, int height) {
        this.width = width;
        this.height = height;
        
        setTitle("sKedU");
        setSize(width, height); //16:9 เท่านั้น
        setResizable(false);
        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

// Load data
        data.setSubjects();
        studentData.setStudents();

        // data.displayAll();
        // data.Test();
        // data.findSubjectById("01131111-67");
        // data.displayDataById("01355103-67");
        // System.out.printf("-%s-", data.findSubjectById("01130171-64").getName());

        // ตั้งค่า CardLayout
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // สร้าง panel ทั้งสอง
        LoginPanel loginPanel = new LoginPanel(this, width, height, studentData, data);

        // เพิ่ม panel ลงใน cardPanel
        cardPanel.add(loginPanel, "Login Panel");

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
        TablePanel tablePanel = new TablePanel(this, width, height, studentData, data);


        cardPanel.add(tablePanel, "Table Panel");
        add(cardPanel);
        setVisible(true);



        cardLayout.show(cardPanel, "Table Panel");
        System.out.println("function : showTablePanel()");
    }
    public void showSearchPanel(){
        SearchPanel searchPanel = new SearchPanel(this, width, height, studentData, data);


        cardPanel.add(searchPanel, "Search Panel");
        add(cardPanel);
        setVisible(true);


        cardLayout.show(cardPanel, "Search Panel");
        System.out.println("function : showSearchPanel()");
    }
    public void showCustomPanel(){
        CustomPanel customPanel = new CustomPanel(this, width, height, studentData, data);


        cardPanel.add(customPanel, "Custom Panel");
        add(cardPanel);
        setVisible(true);


        cardLayout.show(cardPanel, "Custom Panel");
        System.out.println("function : showCustomPanel()");
    }
}

