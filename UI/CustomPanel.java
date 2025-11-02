package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import Subject.*;

public class CustomPanel extends JPanel implements ActionListener{
    private AppFrame appFrame;



    public CustomPanel(AppFrame frame, int width, int height, StudentData studentData, Data data){
        this.appFrame = frame;
        setLayout(null);


        JLayeredPane layer = new JLayeredPane();
        layer.setBounds(0, 0, width, height);

        JPanel background = new JPanel();
        background.setBounds(0, 0, width, height);
        background.setOpaque(true);
        background.setBackground(Color.white);
        background.setLayout(null);
            Table table = new Table(frame, studentData, studentData.getStudentTmp(), data);
            table.setOpaque(false);
            table.setBounds(80, 25, 1170, 400);
            background.add(table);

            Search search = new Search(studentData, data, frame, 2);
            search.setBounds(80, 450, 850, 200);
            search.setOpaque(false);

            JPanel searchWrapper = new JPanel(new BorderLayout());
            searchWrapper.setOpaque(false);
            searchWrapper.setBounds(75, 400, 750, 300);
            searchWrapper.add(search, BorderLayout.CENTER);
            background.add(searchWrapper);
            searchWrapper.revalidate();
            searchWrapper.repaint();

            Detail detail = new Detail(frame, studentData.getStudentTmp(), data, 2);
            detail.setOpaque(false);
            detail.setBounds(930,450,320,160);
            background.add(detail);

            JPanel confirm = new JPanel();
            confirm.setBounds(1050,630,150,40);
            confirm.setOpaque(true);
            confirm.setBackground(Color.green);
                JLabel confirmLabel = new JLabel("Confirm");
                confirmLabel.setFont(new Font("Arial", Font.BOLD, 20));
                confirmLabel.setForeground(Color.white);
                confirmLabel.setHorizontalAlignment(SwingConstants.CENTER);
                confirmLabel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        studentData.studentConfirm();
                        appFrame.showCustomPanel();
                    }
                });
                confirm.add(confirmLabel);
            background.add(confirm);

        layer.add(background, JLayeredPane.DEFAULT_LAYER);


        SidebarPanel sidebar = new SidebarPanel(frame, width, height, 2, studentData, data);
        sidebar.setOpaque(false);
        sidebar.setBounds(0, 0, 200, height);
        layer.add(sidebar, JLayeredPane.PALETTE_LAYER);

        add(layer, BorderLayout.CENTER);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
}
