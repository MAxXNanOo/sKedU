import Subject.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


import UI.*;

public class Main {
    public static void main(String[] args) {
        Data data = new Data("KUdata/KUKPSForTest.csv");
        data.readCSV();
        
        data.displayAll();

        SwingUtilities.invokeLater(() -> new AppFrame(1280, 720));
    }
}


