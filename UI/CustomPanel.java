package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class CustomPanel extends JPanel implements ActionListener{
    private AppFrame appFrame;
    private JButton goToLoginPanel;
    private JButton goToTablePanel;
    private JButton goToSearchPanel;

    public CustomPanel(AppFrame frame, int width, int height){
        this.appFrame = frame;
        setLayout(new BorderLayout());


        JLayeredPane layer = new JLayeredPane();

        JPanel background = new JPanel();
        background.setBounds(0, 0, width, height);
        background.setOpaque(true);
        background.setBackground(Color.WHITE);
        layer.add(background, JLayeredPane.DEFAULT_LAYER);

        JPanel sidebar = new JPanel();
        sidebar.setBounds(0, 0, (int)(width*0.05), height);
        sidebar.setOpaque(true);
        sidebar.setBackground(new Color(47, 51, 55));
        //ใน sidebar
        {
            GridBagConstraints grid = new GridBagConstraints();
            sidebar.setLayout(new GridBagLayout());
            grid.anchor = GridBagConstraints.NORTH;
            grid.insets = new Insets(10, 0, 0,0);
            grid.weightx = 1;
            grid.weighty = 1;

            
            JLabel profile = new JLabel(new ImageIcon((new ImageIcon("Icon/Pofile.png")).getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH)));
            grid.gridx = 0;
            grid.gridy = 0;
            profile.addMouseListener(new MouseAdapter() {
                public void mouseReleased(MouseEvent e){ appFrame.showLogin();}
                public void mouseEntered(MouseEvent e) { sidebar.setBounds(0, 0, (int)(width*0.15), height);}
            });
            sidebar.add(profile, grid);

            JLabel ligthbar = new JLabel();
            
        }

            
        


        sidebar.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                sidebar.setBounds(0, 0, (int)(width*0.15), height);
            }
            public void mouseExited(MouseEvent e){
                sidebar.setBounds(0, 0, (int)(width*0.05), height);
            }
        });
        layer.add(sidebar, JLayeredPane.PALETTE_LAYER);






        add(layer, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }


}
