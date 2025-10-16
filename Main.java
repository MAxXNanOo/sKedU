import Subject.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


import UI.*;

public class Main {
    public static void main(String[] args) {
        Data data = new Data("KUdata/KUKPSForTest.csv");
        data.readCSV();
        

        // data.displayDataById("01130171-64");
        data.displayAll();
        // System.out.printf("\n\n%s\n\n",data.getSubjects("").get(
        // System.out.println(data.getDataById("01423345-65"));
        // System.out.println("run");
        // SwingUtilities.invokeLater(() -> new AppFrame());
    }
}


