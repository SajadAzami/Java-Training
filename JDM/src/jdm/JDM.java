package jdm;

import java.awt.AWTException;
import java.io.IOException;
import static java.lang.Thread.MIN_PRIORITY;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import static jdm.JDM.pass;
import static jdm.JDM.registered;

/*
 * @author Sajad Azami
 */
public class JDM {

    static ArrayList downoads = new ArrayList();
    public static String registered = "no";
    public static String pass = "sajad";

    public static void main(String[] args) throws IOException, AWTException {
        JDM jdm = new JDM();
        JDMWindow window = new JDMWindow();
//        new register().registration();
    }

}

/**
 * The registration process of the JDM
 * @author Espinas
 */
class register extends Thread {

    public boolean running = true;

    public boolean registration() {

        while (running) {
            if ("no".equals(registered)) {
                if (pass.equals(JOptionPane.showInputDialog("This is a trial Version !\n"
                        + "please enter registration code:"))) {
                    JOptionPane.showMessageDialog(null, "Registered Succesfully.", "Thank you", MIN_PRIORITY);
                    registered = "ok";
                    running = false;
                    return true;
                } else {
                    registered = "wrong";
                }
            } else if ("wrong".equals(registered)) {
                if (pass.equals(JOptionPane.showInputDialog("You Registered with wrong serial number !\n"
                        + "please enter a valid registration code:"))) {
                    JOptionPane.showMessageDialog(null, "Registered Succesfully.", "Thank you", MIN_PRIORITY);
                    registered = "ok";
                    running = false;
                    return true;
                }

            }
            try {
                Thread.sleep(1000 * 3);
            } catch (InterruptedException ex) {
            }
        }
        return false;
    }
}
