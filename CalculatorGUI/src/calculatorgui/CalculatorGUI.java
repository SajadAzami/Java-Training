/*
 * Simple microsoft windows calculator GUI
 * Without functionality
 */
package calculatorgui;

import javax.swing.JFrame;

/**
 *
 * @author Sajad Azami
 */
public class CalculatorGUI {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CalculatorFrame frame = new CalculatorFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 261);
        frame.setResizable(true);
        frame.setVisible(true);
    }

}
