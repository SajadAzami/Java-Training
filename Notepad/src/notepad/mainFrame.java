/*
 * The main frame of the notepad application
 */
package notepad;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.Thread.MIN_PRIORITY;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Sajad Azami
 */
public class mainFrame {

    JFrame mainframe;
    TextArea textArea;
    String selectedFont;
    int selectedStyle = 0, selectedSize = 12;

    public mainFrame() {
        //Change the look and feel of the program
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(mainFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(mainFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(mainFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(mainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.mainframe = new JFrame("Notepad");
        mainframe.setLayout(new BorderLayout());
        mainframe.setExtendedState(JFrame.MAXIMIZED_BOTH);
        textArea = new TextArea("", 0, 0, TextArea.SCROLLBARS_VERTICAL_ONLY);
        mainframe.add(textArea);
        setMenuBar(mainframe);
        mainframe.setResizable(true);
        mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainframe.setVisible(true);
        textArea.setFont(new Font("Arial Rounded MT", Font.BOLD, 20));
        URL iconURL = getClass().getResource("/images/titleicon.png");
        ImageIcon icon = new ImageIcon(iconURL);
        mainframe.setIconImage(icon.getImage());
    }

    /**
     * Handle the actions to be implemented
     */
    private void onAction(String command) {
        switch (command) {
            case "new":
                textArea.setText(" ");
                break;
            case "save":
                JFileChooser save = new JFileChooser();
                int saveOption = save.showSaveDialog(mainframe);
                if (saveOption == JFileChooser.APPROVE_OPTION) {
                    try {
                        BufferedWriter out = new BufferedWriter(new FileWriter(save.getSelectedFile().getPath()));
                        out.write(this.textArea.getText());
                        out.close();
                    } catch (IOException ex) {
                        Logger.getLogger(mainFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
            case "open":
                JFileChooser open = new JFileChooser();
                int openOption = open.showOpenDialog(mainframe);
                if (openOption == JFileChooser.APPROVE_OPTION) {
                    try {
                        this.textArea.setText(" ");
                        Scanner scan = new Scanner(new FileReader(open.getSelectedFile().getPath()));
                        while (scan.hasNext()) {
                            this.textArea.append(scan.nextLine() + "\n");
                        }
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(mainFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
            case "font":
                showFont();
                break;
        }
    }

    /**
     * Show the font menu
     */
    private void showFont() {

        final JFrame formatFrame = new JFrame("Font");
        formatFrame.setLayout(new BorderLayout());
        formatFrame.setSize(600, 400);
        JPanel panel1 = new JPanel(new GridLayout(1, 3, 30, 30));
        JPanel panel2 = new JPanel(new GridLayout(2, 1));
        GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        final JList<String> fonts = new JList<String>(graphicsEnvironment.getAvailableFontFamilyNames());
        fonts.setSelectedValue(selectedFont, true);
        fonts.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(fonts);

        String[] styleNames = {"Bold", "Plain", "Italic", "Bold Italic"};
        final JList<String> styles = new JList<String>(styleNames);

        final JSpinner sizes = new JSpinner(new SpinnerNumberModel(12, 6, 24, 1));

        JButton colorChooser = new JButton("Choose a Color");

        final JLabel preview = new JLabel("Test");

        //Actions to be done
        fonts.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                selectedFont = fonts.getSelectedValue();
                textArea.setFont(new Font(selectedFont, selectedStyle, selectedSize));
                preview.setFont(new Font(selectedFont, selectedStyle, selectedSize));
            }
        });
        styles.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                selectedStyle = styles.getSelectedIndex();
                textArea.setFont(new Font(selectedFont, selectedStyle, selectedSize));
                preview.setFont(new Font(selectedFont, selectedStyle, selectedSize));
            }
        });
        sizes.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                String size = sizes.getModel().getValue().toString();
                selectedSize = Integer.parseInt(size);
                textArea.setFont(new Font(selectedFont, selectedStyle, selectedSize));
                preview.setFont(new Font(selectedFont, selectedStyle, selectedSize));
            }
        });
        colorChooser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color color = JColorChooser.showDialog(formatFrame, "Choose a Color", Color.BLACK);

                if (color == null) {
                    color = Color.BLACK;
                }

                textArea.setForeground(color);
                preview.setForeground(color);
            }
        });

        //Adding items into frame
        panel2.add(styles);
        panel2.add(preview);
        panel1.add(scrollPane);
        panel1.add(panel2);
        panel1.add(sizes);
        formatFrame.add(panel1, BorderLayout.CENTER);
        formatFrame.add(colorChooser, BorderLayout.SOUTH);
        formatFrame.setVisible(true);
    }

    /**
     * Design of the menu bar
     *
     * @param mainframe the JFrame that menu will be added to
     */
    private void setMenuBar(JFrame mainframe) {
        AbstractAction onClick = new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                onAction(e.getActionCommand());

            }
        };

        JMenuBar menubar = new JMenuBar();

        //File menu
        JMenu file = new JMenu("File");
        file.setMnemonic(KeyEvent.VK_F);
        menubar.setBackground(Color.WHITE);

        JMenuItem FileMenuItem1 = new JMenuItem("New");
        FileMenuItem1.setMnemonic(KeyEvent.VK_N);
        FileMenuItem1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
        FileMenuItem1.setActionCommand("new");
        FileMenuItem1.addActionListener(onClick);

        JMenuItem FileMenuItem2 = new JMenuItem("Open");
        FileMenuItem2.setMnemonic(KeyEvent.VK_O);
        FileMenuItem2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
        FileMenuItem2.setActionCommand("open");
        FileMenuItem2.addActionListener(onClick);

        JMenuItem FileMenuItem3 = new JMenuItem("Save");
        FileMenuItem3.setMnemonic(KeyEvent.VK_S);
        FileMenuItem3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
        FileMenuItem3.setActionCommand("save");
        FileMenuItem3.addActionListener(onClick);

        file.add(FileMenuItem1);
        file.add(FileMenuItem2);
        file.add(FileMenuItem3);

        //Format menu
        JMenu format = new JMenu("Format");
        format.setMnemonic(KeyEvent.VK_R);
        format.setBackground(Color.WHITE);

        JMenuItem FileMenuItem4 = new JMenuItem("Font");
        FileMenuItem4.setMnemonic(KeyEvent.VK_T);
        FileMenuItem4.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, KeyEvent.CTRL_DOWN_MASK));
        FileMenuItem4.setActionCommand("font");
        FileMenuItem4.addActionListener(onClick);

        format.add(FileMenuItem4);

        menubar.add(file);
        menubar.add(format);

        mainframe.setJMenuBar(menubar);
        mainframe.setLocationRelativeTo(null);

    }
}
