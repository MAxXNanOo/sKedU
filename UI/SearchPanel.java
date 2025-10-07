package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class SearchPanel extends JPanel implements ActionListener{
    private AppFrame appFrame;
    private JButton goToLoginPanel;
    private JButton goToTablePanel;
    private JButton goToCustomPanel;

    public SearchPanel(AppFrame frame){
        this.appFrame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    } 
}
