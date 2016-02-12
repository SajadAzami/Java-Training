/*
 * JDM's graphical user interface's and functionality implementation
 */
package jdm;

import jdlib.Download;
import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import static java.lang.Thread.MIN_PRIORITY;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Sajad Azami
 */
public class mainFrame implements Observer {

    Dimension mainFrameDimansion = new Dimension(560, 400);
    JFrame mainframe;
    JPanel menuPanel;
    JPanel toolPanel;
    JPanel listPanel;
    DefaultTableModel tableModel = new DefaultTableModel();
    String directory = "C:\\Users\\Espinas\\Downloads\\JDM";
    ArrayList<Download> downloads = new ArrayList<>();
    JTable DownloadsTable = new JTable(100, 5);
    JProgressBar progressBar = new JProgressBar(0, 100);
    int downloadsSameTime = 10;

    /**
     * Constructor
     *
     * @throws IOException
     * @throws AWTException
     */
    public mainFrame() throws IOException, AWTException {
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

        progressBar.setVisible(true);
        mainframe = new JFrame("JDM");
        mainframe.setSize(mainFrameDimansion);
        mainframe.setResizable(false);
        FlowLayout myLayout = new FlowLayout();
        myLayout.setHgap(0);
        myLayout.setVgap(0);
        mainframe.setLayout(myLayout);
        mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        URL iconURL = getClass().getResource("/images/favicon.png");
        ImageIcon icon = new ImageIcon(iconURL);
        mainframe.setIconImage(icon.getImage());

        SystemTray tray = SystemTray.getSystemTray();
        Image image = Toolkit.getDefaultToolkit().createImage(getClass().getResource("/images/favicons.png"));
        final TrayIcon trayIcon = new TrayIcon(image);
        final PopupMenu popup = new PopupMenu();

        AbstractAction onClick = new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                onAction(e.getActionCommand());

            }
        };
        // Create a pop-up menu components
        MenuItem showItem = new MenuItem("Open JDM");
        showItem.setActionCommand("show");
        showItem.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        mainframe.show();
                    }

                });
        MenuItem aboutItem = new MenuItem("About");
        aboutItem.setActionCommand("about us");
        aboutItem.addActionListener(onClick);
        Menu downloadMenu = new Menu("Download");
        MenuItem newItem = new MenuItem("New");
        newItem.setActionCommand("new download");
        newItem.addActionListener(onClick);
        MenuItem exitItem = new MenuItem("Exit");
        exitItem.setActionCommand("EXIT");
        exitItem.addActionListener(onClick);

        popup.add(showItem);
        popup.add(aboutItem);
        popup.addSeparator();
        popup.add(downloadMenu);
        downloadMenu.add(newItem);
        popup.add(exitItem);

        trayIcon.setPopupMenu(popup);

        try {
            tray.add(trayIcon);
        } catch (AWTException e2) {
            e2.printStackTrace();
        }

        setMenuBar(mainframe);
        Border outline = BorderFactory.createLineBorder(Color.WHITE);

        toolPanel = new JPanel();
        toolPanel.setLayout(new FlowLayout());
        toolPanel.setBackground(Color.WHITE);
        toolPanel.setBorder(outline);
        toolPanel.setPreferredSize(new Dimension(700, 75));
        setButtons(toolPanel);
        mainframe.add(toolPanel);

        listPanel = new JPanel();
        listPanel.setBackground(Color.WHITE);
        listPanel.setPreferredSize(new Dimension(560, 500));
        FlowLayout myListPanelLayout = new FlowLayout();
        myListPanelLayout.setHgap(0);
        myListPanelLayout.setVgap(0);
        listPanel.setLayout(myListPanelLayout);

        JTabbedPane tabbedPane = new JTabbedPane();
        JPanel queuePanel = new JPanel();
        queuePanel.setBackground(Color.WHITE);

        tabbedPane.addTab("Queue", queuePanel);
        JPanel schedulePanel = new JPanel();
        schedulePanel.setBackground(Color.WHITE);
        tabbedPane.addTab("Schedule", schedulePanel);
        listPanel.add(tabbedPane, BorderLayout.CENTER);

        addTable(queuePanel);
        mainframe.add(listPanel);

        mainframe.show();
    }

    /**
     * Create setting dialog
     */
    private void settingDialogMaker() {

        final JDialog settingDialog = new JDialog();
        settingDialog.setLayout(new GridLayout(3, 0));
        settingDialog.setResizable(false);

        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.add(new JLabel("Save Directory :"));
        final JTextField textField1 = new JTextField(directory);
        textField1.setPreferredSize(new Dimension(290, 20));
        topPanel.add(textField1);

        settingDialog.add(topPanel);

        JPanel middlePanel = new JPanel(new FlowLayout());
        middlePanel.add(new JLabel("Downloads at the Same time :"));
        final JTextField textField2 = new JTextField("" + downloadsSameTime);
        textField2.setPreferredSize(new Dimension(195, 20));

        middlePanel.add(textField2);

        settingDialog.add(middlePanel);

        JPanel bottomPanel = new JPanel(new FlowLayout());
        JButton butt1 = new JButton("Save");

        butt1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                directory = textField1.getText();
                File file = new File(directory);

                try {
                    if (Integer.parseInt(textField2.getText()) > 0) {
                        downloadsSameTime = Integer.parseInt(textField2.getText());
                        settingDialog.dispose();
                    } else {
                        JOptionPane.showMessageDialog(settingDialog, "Number isn not valid!");
                    }

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(settingDialog, "Number isn not valid!");

                }
            }
        });

        JButton butt2 = new JButton("Cancel");
        butt2.setActionCommand("exit");
        butt2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                settingDialog.dispose();
            }
        });

        bottomPanel.add(butt1);
        bottomPanel.add(butt2);

        settingDialog.add(bottomPanel);

        settingDialog.setModal(true);
        settingDialog.setTitle("Setting");
        settingDialog.setSize(410, 170);
        settingDialog.setLocationRelativeTo(settingDialog);
        settingDialog.show();
    }

    /**
     * Actions to be implemented by pressing the buttons
     */
    private void onAction(String command) {
        URL downloadUrl = null;
        switch (command) {
            case "new download":
                try {
                    downloadUrl = new URL(JOptionPane.showInputDialog("Enter the URL: "));
                } catch (MalformedURLException e) {
                    System.out.println(e);
                }
                Download download = new Download(downloadUrl, directory);
                downloads.add(download);
                download.start();
                addRowToTable(tableModel, download);
                download.addObserver(mainFrame.this);
                tableModel.fireTableRowsInserted(tableModel.getRowCount(), tableModel.getColumnCount());
                break;
            case "pause":
                downloads.get(DownloadsTable.getSelectedRow()).pause();
                break;
            case "resume":
                downloads.get(DownloadsTable.getSelectedRow()).resume();
                break;
            case "cancel":
                downloads.get(DownloadsTable.getSelectedRow()).cancel();
                break;
            case "delete":
                tableModel.removeRow(DownloadsTable.getSelectedRow());
                break;
            case "setting":
                settingDialogMaker();
                break;
            case "about us":
                JOptionPane.showMessageDialog(mainframe, "Java Download Mangare\n"
                        + "All rights reserved by : Sajad Azami", "About Us", MIN_PRIORITY,
                        new ImageIcon(getClass().getResource("/images/icon.png")));
                break;
            case "Registration":
                System.out.println("Registration");
                if ("no".equals(jdm.JDM.registered)) {
                    JOptionPane.showMessageDialog(mainframe, "NOT Registered !");
                } else if ("wrong".equals(jdm.JDM.registered)) {
                    JOptionPane.showMessageDialog(mainframe, "Registered with wrong key !");
                } else {
                    JOptionPane.showMessageDialog(mainframe, "Registered");
                }
                break;
            case "EXIT":
                System.exit(0);
                break;
        }
    }

    /**
     * @return the number of downloads and also table rows
     */
    public int getRowCount() {
        return downloads.size();
    }

    /**
     * Set buttons
     *
     * @param toolPanel the panel that buttons will be added
     * @throws IOException
     */
    private void setButtons(JPanel toolPanel) throws IOException {
        AbstractAction onClick = new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                onAction(e.getActionCommand());

            }
        };

        ImageIcon buttNDIcon = createImageIcon("/images/newdownload.png", "Java");
        MyButton buttND = new MyButton(buttNDIcon, "New Downlaod");
        buttND.setActionCommand("new download");
        buttND.addActionListener(onClick);

        toolPanel.add(buttND);

        // pause
        ImageIcon ButtPIcon = createImageIcon("/images/pause.png", "Java");
        MyButton buttP = new MyButton(ButtPIcon, "Pause");
        buttP.setActionCommand("pause");
        buttP.addActionListener(onClick);

        toolPanel.add(buttP);
        // resume
        ImageIcon ButtRIcon = createImageIcon("/images/resume.png", "Java");
        MyButton buttR = new MyButton(ButtRIcon, "Resume");
        buttR.setActionCommand("resume");
        buttR.addActionListener(onClick);

        toolPanel.add(buttR);
        // cancel
        ImageIcon ButtCIcon = createImageIcon("/images/cancel.png", "Java");
        MyButton buttC = new MyButton(ButtCIcon, "Cancel");
        buttC.setActionCommand("cancel");
        buttC.addActionListener(onClick);

        toolPanel.add(buttC);
        // delete
        ImageIcon ButtDellIcon = createImageIcon("/images/delete.png", "Java");
        MyButton buttDELL = new MyButton(ButtDellIcon, "Delete");
        buttDELL.setActionCommand("delete");
        buttDELL.addActionListener(onClick);

        toolPanel.add(buttDELL);
        // setting
        ImageIcon ButtSIcon = createImageIcon("/images/setting.png", "Java");
        MyButton buttS = new MyButton(ButtSIcon, "Setting");
        buttS.setActionCommand("setting");
        buttS.addActionListener(onClick);

        toolPanel.add(buttS);

    }

    /**
     * Create an ImageIcon from a path
     *
     * @param path the path of image file
     * @param description String of description
     * @return the ImageIcon
     */
    private ImageIcon createImageIcon(String path, String description) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }

    }

    /**
     * Add a row to a table
     *
     * @param model the DefaultTableModel to be added
     * @param download the download to be added to the row
     */
    private void addRowToTable(DefaultTableModel model, Download download) {
        Object[] DownloadInfo = new Object[4];
        DownloadInfo[0] = download.getFileName();
        DownloadInfo[1] = "" + (download.getSize() / 1000) + "KB";
        DownloadInfo[2] = download.getSpeed();
//        DownloadInfo[3] = progressBar;
        DownloadInfo[3] = download.getProgress();
        model.addRow(DownloadInfo);
    }

    /**
     * Add a column too the table
     *
     * @param listPanel
     */
    private void addTable(JPanel listPanel) {

        String[] columnNamesExampe = {"File Name",
            "Size",
            "Speed",
            "Progress",
            "Status"};

        tableModel.addColumn("File Name");
        tableModel.addColumn("Size");
        tableModel.addColumn("Speed");
        tableModel.addColumn("Progress");
        tableModel.addColumn("Status");
        DownloadsTable.setRowHeight(20);
        DownloadsTable.setAutoResizeMode(3);
        DownloadsTable.setModel(tableModel);
        DownloadsTable.setDefaultRenderer(JProgressBar.class, new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                progressBar.setValue(10);
                return progressBar;
            }
        });

        DownloadsTable.getTableHeader().setReorderingAllowed(false);
        //DownloadsTable.setModel(new AbsTableModel() {});
        for (int c = 0; c < DownloadsTable.getColumnCount(); c++) {
            Class<?> col_class = DownloadsTable.getColumnClass(c);
            DownloadsTable.setDefaultEditor(col_class, null);        // remove editor
        }
        JScrollPane scrollpane = new JScrollPane(DownloadsTable);
        scrollpane.setPreferredSize(new Dimension(554, 260));

        listPanel.add(scrollpane);

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
        JMenu file = new JMenu("File");
        file.setMnemonic(KeyEvent.VK_F);
        menubar.setBackground(Color.WHITE);

        JMenu FileMenuItem1 = new JMenu("Download");
        FileMenuItem1.setMnemonic(KeyEvent.VK_D);
        JMenuItem newDownload = new JMenuItem("New Download");
        newDownload.setMnemonic(KeyEvent.VK_N);
        newDownload.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, Event.CTRL_MASK));
        newDownload.setActionCommand("new download");
        newDownload.addActionListener(onClick);
        FileMenuItem1.add(newDownload);

        JMenuItem pause = new JMenuItem("Pause");
        pause.setMnemonic(KeyEvent.VK_P);
        pause.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.CTRL_DOWN_MASK));
        pause.setActionCommand("pause");
        pause.addActionListener(onClick);
        FileMenuItem1.add(pause);

        JMenuItem resume = new JMenuItem("Resume");
        resume.setMnemonic(KeyEvent.VK_R);
        resume.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, Event.CTRL_MASK));
        resume.setActionCommand("resume");
        resume.addActionListener(onClick);
        FileMenuItem1.add(resume);

        JMenuItem cancel = new JMenuItem("Cancel");
        cancel.setMnemonic(KeyEvent.VK_C);
        cancel.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, Event.CTRL_MASK));
        cancel.setActionCommand("cancel");
        cancel.addActionListener(onClick);
        FileMenuItem1.add(cancel);

        JMenuItem delete = new JMenuItem("Delete");
        delete.setMnemonic(KeyEvent.VK_D);
        delete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, Event.CTRL_MASK));
        delete.setActionCommand("delete");
        delete.addActionListener(onClick);
        FileMenuItem1.add(delete);

        JMenuItem setting = new JMenuItem("Setting");
        setting.setMnemonic(KeyEvent.VK_S);
        setting.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0));
        setting.setActionCommand("setting");
        setting.addActionListener(onClick);
        FileMenuItem1.add(setting);

        JMenuItem queue = new JMenuItem("Queue");
        queue.setMnemonic(KeyEvent.VK_Q);
        queue.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, Event.CTRL_MASK));
        queue.setActionCommand("queue");
        queue.addActionListener(onClick);
        FileMenuItem1.add(queue);

        JMenuItem schedule = new JMenuItem("Schedule");
        schedule.setMnemonic(KeyEvent.VK_H);
        schedule.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, Event.ALT_MASK));
        schedule.setActionCommand("schedule");
        schedule.addActionListener(onClick);
        FileMenuItem1.add(schedule);

        file.add(FileMenuItem1);
        file.addSeparator();

        JMenuItem FileMenuItem2 = new JMenuItem("Exit");
        FileMenuItem2.setMnemonic(KeyEvent.VK_E);
        FileMenuItem2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, Event.CTRL_MASK));
        FileMenuItem2.setToolTipText("Exit application");
        FileMenuItem2.setActionCommand("EXIT");
        FileMenuItem2.addActionListener(onClick);
        file.add(FileMenuItem2);

        JMenu help = new JMenu("Help");
        help.setMnemonic(KeyEvent.VK_H);
        JMenuItem HelpMenuItem1 = new JMenuItem("About us");
        HelpMenuItem1.setActionCommand("about us");
        HelpMenuItem1.addActionListener(onClick);
        HelpMenuItem1.setMnemonic(KeyEvent.VK_A);
        HelpMenuItem1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, Event.CTRL_MASK));
        help.add(HelpMenuItem1);

        help.addSeparator();
        JMenuItem HelpMenuItem2 = new JMenuItem("Registration");
        HelpMenuItem2.setActionCommand("Registration");
        HelpMenuItem2.addActionListener(onClick);
        HelpMenuItem2.setMnemonic(KeyEvent.VK_R);
        help.add(HelpMenuItem2);

        menubar.add(file);
        menubar.add(help);

        mainframe.setJMenuBar(menubar);
        mainframe.setLocationRelativeTo(null);

    }

    /**
     * Update the table
     *
     * @param o the observable object
     * @param arg the cell that information will be added
     */
    @Override
    public void update(Observable o, Object arg) {
        tableModel.fireTableRowsUpdated(downloads.indexOf(o), downloads.indexOf(o));
        System.out.println(Download.class.cast(o).getProgress());
        progressBar.setValue((int) Download.class.cast(o).getProgress());
        tableModel.setValueAt(Download.class.cast(o).getStatus(), downloads.indexOf(o), 4);
        tableModel.setValueAt(Download.class.cast(o).getProgress(), downloads.indexOf(o), 3);
        tableModel.setValueAt(Download.class.cast(o).getSpeed(), downloads.indexOf(o), 2);
        tableModel.setValueAt(Download.class.cast(o).getSize() / 1000 + " KB", downloads.indexOf(o), 1);
    }

}
