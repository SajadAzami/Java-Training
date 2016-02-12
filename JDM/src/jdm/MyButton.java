/*
 * JButton class to create buttons
 */
package jdm;

import java.awt.Dimension;
import javax.swing.Icon;
import javax.swing.JButton;

/**
 *
 * @author Sajad Azami
 */
public class MyButton extends JButton {

    public MyButton(Icon icon, String name) {

        super.setBackground(null);
        super.setToolTipText(name);
        super.setIcon(icon);
        setPreferredSize(new Dimension(icon.getIconWidth(), icon.getIconHeight()));
    }

}
