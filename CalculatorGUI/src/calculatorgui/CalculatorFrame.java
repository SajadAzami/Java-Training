package calculatorgui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.util.ArrayList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.JTextField;

/**
 *
 * @author Sajad Azami
 */
public class CalculatorFrame extends JFrame {

    private JMenuBar menuBar = new JMenuBar();
    private ArrayList<JButton> button = new ArrayList<>();
    private JTextField textField = new JTextField("0");
    private JPanel panel1 = new JPanel(new GridLayout(6, 4, 5, 5));
    private JPanel panelButton = new JPanel(new GridLayout(1, 1, 5, 5));
    private JPanel panelMain = new JPanel();
    private GridBagLayout layout = new GridBagLayout();

    public CalculatorFrame() {
        super("Calculator");
        setLayout(new BorderLayout());
        for (int i = 0; i < 28; i++) {
            button.add(new JButton());
        }

        //Implement the menu items
        JMenu viewMenu = new JMenu("View");
        JMenuItem standard = new JMenuItem("Standard");
        viewMenu.add(standard);
        JMenuItem scientific = new JMenuItem("Scientific");
        viewMenu.add(scientific);
        JMenuItem programmer = new JMenuItem("Programmer");
        viewMenu.add(programmer);
        JMenuItem statictis = new JMenuItem("Statictis");
        viewMenu.add(statictis);
        viewMenu.add(new JSeparator());
        JMenuItem history = new JMenuItem("History");
        viewMenu.add(history);
        JMenuItem digitGrouping = new JMenuItem("Digit Grouping");
        viewMenu.add(digitGrouping);
        viewMenu.add(new JSeparator());
        JMenuItem basic = new JMenuItem("Basic");
        viewMenu.add(basic);
        JMenuItem unitConversion = new JMenuItem("Unit conversion");
        viewMenu.add(unitConversion);
        JMenuItem dateCalculation = new JMenuItem("Date calculation");
        viewMenu.add(dateCalculation);
        JMenuItem worksheets = new JMenuItem("Worksheets");
        viewMenu.add(worksheets);
        menuBar.add(viewMenu);

        JMenu editMenu = new JMenu("Edit");
        JMenuItem copy = new JMenuItem("Copy");
        editMenu.add(copy);
        JMenuItem paste = new JMenuItem("Paste");
        editMenu.add(paste);
        editMenu.add(new JSeparator());
        JMenuItem histry = new JMenuItem("History");
        editMenu.add(histry);
        menuBar.add(editMenu);

        JMenu help = new JMenu("Help");
        JMenuItem viewHelp = new JMenuItem("View Help");
        help.add(viewHelp);
        help.add(new JSeparator());
        JMenuItem aboutCalculator = new JMenuItem("Aabout Calculator");
        help.add(aboutCalculator);
        menuBar.add(help);

        menuBar.setSize(10, 10);

        panelMain.setLayout(layout);
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.gridheight = 1;
        c.gridwidth = GridBagConstraints.REMAINDER;
        layout.setConstraints(textField, c);
        button.get(5).setText("MC");
        panel1.add(button.get(5));
        button.get(6).setText("MR");
        panel1.add(button.get(6));
        button.get(7).setText("MS");
        panel1.add(button.get(7));
        button.get(8).setText("M+");
        panel1.add(button.get(8));
        button.get(4).setText("M-");
        panel1.add(button.get(4));
        button.get(9).setText("←");
        panel1.add(button.get(9));
        button.get(10).setText("CE");
        panel1.add(button.get(10));
        button.get(11).setText("C");
        panel1.add(button.get(11));
        button.get(12).setText("±");
        panel1.add(button.get(12));
        button.get(3).setText("√");
        panel1.add(button.get(3));
        button.get(25).setText("7");
        panel1.add(button.get(25));
        button.get(26).setText("8");
        panel1.add(button.get(26));
        button.get(27).setText("9");
        panel1.add(button.get(27));
        button.get(13).setText("/");
        panel1.add(button.get(13));
        button.get(0).setText("%");
        panel1.add(button.get(0));
        button.get(22).setText("4");
        panel1.add(button.get(22));
        button.get(23).setText("5");
        panel1.add(button.get(23));
        button.get(24).setText("6");
        panel1.add(button.get(24));
        button.get(14).setText("*");
        panel1.add(button.get(14));
        button.get(1).setText("1/x");
        panel1.add(button.get(1));
        button.get(19).setText("1");
        panel1.add(button.get(19));
        button.get(20).setText("2");
        panel1.add(button.get(20));
        button.get(21).setText("3");
        panel1.add(button.get(21));
        button.get(15).setText("-");
        panel1.add(button.get(15));
        button.get(2).setText("=");
        panel1.add(button.get(2));
        button.get(18).setText("0");
        panel1.add(button.get(18));
        button.get(17).setText(".");
        panel1.add(button.get(17));
        button.get(16).setText("+");
        panel1.add(button.get(16));
        panelButton.add(panel1, BorderLayout.NORTH);
        panelMain.add(textField);
        panelMain.add(panelButton);
        c.gridheight = 4;
        layout.setConstraints(panelButton, c);
        getContentPane().add(panelMain, BorderLayout.SOUTH);
        getContentPane().add(menuBar, BorderLayout.NORTH);
    }
}
