import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


import UI.*;
import table.*;

public class Main {
    public static void main(String[] args) {
        // Data data = new Data("KUdata/KUKPSForTest.csv");
        // data.setSubjects();
        
        // data.displayAll();

        SwingUtilities.invokeLater(() -> new AppFrame(1280, 720));
    }
}


