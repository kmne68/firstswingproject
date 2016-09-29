/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import controller.Controller;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
//import static java.util.Locale.filter;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author kemery
 */
public class MainFrame extends JFrame {
    
    private Toolbar toolbar;
    private FormPanel formPanel;
    private JFileChooser fileChooser;
    private Controller controller;
    private TablePanel tablePanel;
    private PreferencesDialog prefsDialog;
    private Preferences prefs;
    private JSplitPane splitPane;
    private JTabbedPane tabbedPane;
    private MessagePanel messagePanel;
    
    
    // Constructor
    public MainFrame() {    
        super("Hello World");
        
        setSize(600, 500);
        setMinimumSize(new Dimension(600, 500));
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setVisible(true);  
        setLayout(new BorderLayout());  
        setJMenuBar(createMenuBar());
        
        prefs = Preferences.userRoot().node("db");
                
        toolbar = new Toolbar();
        formPanel = new FormPanel();
        tablePanel = new TablePanel();
        prefsDialog = new PreferencesDialog(this);
        tabbedPane = new JTabbedPane();
        messagePanel = new MessagePanel(this);
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, formPanel, tabbedPane);
        
        splitPane.setOneTouchExpandable(true);
        
        tabbedPane.addTab("Person Database", tablePanel);
        tabbedPane.addTab("Messages", messagePanel);
        
        controller = new Controller();
        
        tablePanel.setData(controller.getPeople());
        
        tablePanel.setPersonTableListener(new PersonTableListener() {
            
            public void rowDeleted(int row) {
                controller.removePerson(row);
             //   System.out.println(row);
            }
        });
        
        
        tabbedPane.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                
                int tabIndex = tabbedPane.getSelectedIndex();
                
                if (tabIndex == 1) {
                    
                    messagePanel.refresh();
                }
            }
           
            
        });
        
        prefsDialog.setPreferencesListener(new PreferencesListener() {
            
            public void preferencesSet(String user, String password, int port) {
         //       System.out.println(user + ", " + password);
                prefs.put("user", user);
                prefs.put("password", password);
                prefs.putInt("port", port);
            }
            
        });
        
        String user = prefs.get("user", "");
        String password = prefs.get("password", "");
        Integer port = prefs.getInt("port", 3306);
        prefsDialog.setDefaults(user, password, port);
        
        fileChooser = new JFileChooser();
        fileChooser.addChoosableFileFilter(new PersonFileFilter());
        
        
        toolbar.setToolbarListener(new ToolbarListener()
        {
        
            public void saveEventOccurred() {
                
                connect();
                try {
                    controller.save();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(MainFrame.this, "Cannot save to database", "Database save error.", JOptionPane.ERROR_MESSAGE);
                }
            }        

            @Override
            public void refreshEventOccurred() {
                
                connect();
                try {
                    controller.load();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(MainFrame.this, "Cannot load from database", "Database read error.", JOptionPane.ERROR_MESSAGE);
                }
                
                tablePanel.refresh();
            }
            
        }); 
        
        // listens for form clicks
        formPanel.setFormListener(new FormListener() {
            
            public void formEventOccurred(FormEvent e) {
            
        /*  This information is all passed to the controller in the call below
                String name = e.getName();
                String occupation = e.getOccupation();
                int ageCategory = e.getAgeCategory();
                String employmentStatus = e.getEmploymentStatus();
                boolean usCitizen = e.isUsCitizen();
                String taxID = e.getTaxID();
                String gender = e.getGender(); */
                
                controller.addPerson(e);
                tablePanel.refresh();

            //    textPanel.appendText(name + ": " + occupation + ": " + ageCategory + " " + ": " + employmentStatus+ ": " +  usCitizen + ": " +  taxID + ": " + gender +"\n" );
            }
        });
        
        
        addWindowListener(new WindowAdapter() {
            
            public void windowClosing(WindowEvent e) {
                
                System.out.println("Window closing");
                controller.disconnect();
                dispose();
                System.gc();
            }
           
            
        });
        
        
        
        
        add(toolbar, BorderLayout.PAGE_START);  
      //  add(formPanel, BorderLayout.WEST);
      //  add(textPanel, BorderLayout.CENTER);
     //   add(tablePanel, BorderLayout.CENTER);
        add(splitPane, BorderLayout.CENTER);

    }
    
    
    private void connect() {
        
                try {
                    controller.connect();
                }
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(MainFrame.this, "Cannot connect to database", "Database connection problem.", JOptionPane.ERROR_MESSAGE);
                }
        
    }
    
            
        private JMenuBar createMenuBar() {
            
            JMenuBar menuBar = new JMenuBar();
            
            JMenu fileMenu = new JMenu("File");            
            JMenuItem exportDataItem = new JMenuItem("Export Data...");
            JMenuItem importDataItem = new JMenuItem("Import Data...");
            JMenuItem exitItem = new JMenuItem("Exit");
            
            fileMenu.add(exportDataItem);
            fileMenu.add(importDataItem);
            fileMenu.addSeparator();
            fileMenu.add(exitItem);
            
            JMenuItem prefsItem = new JMenuItem("Preferences...");
            
            JMenu showMenu = new JMenu("Show");            
            JMenu windowMenu = new JMenu("Window");            
            windowMenu.add(prefsItem);   
            
            prefsItem.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    prefsDialog.setVisible(true);
                }
            } );
            
            
            JCheckBoxMenuItem showFormItem = new JCheckBoxMenuItem("Person Form");
            showFormItem.setSelected(true);
            
            
            showFormItem.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    JCheckBoxMenuItem menuItem = (JCheckBoxMenuItem) e.getSource();
                    
                    if(menuItem.isSelected()) {
                        splitPane.setDividerLocation((int) formPanel.getMinimumSize().getWidth());
                    }
                    formPanel.setVisible(menuItem.isSelected());
                    
                }
            });
            
            
            fileMenu.setMnemonic(KeyEvent.VK_F);
            exitItem.setMnemonic(KeyEvent.VK_X);
            
            prefsItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
            
            exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
            
            importDataItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK));

            importDataItem.addActionListener(new ActionListener() {
                

                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        if (fileChooser.showOpenDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION)
                            controller.loadFromFile(fileChooser.getSelectedFile());
                        tablePanel.refresh();
                     //       System.out.println(fileChooser.getSelectedFile());
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(MainFrame.this, "Could not load data from file.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    
                }
                
            });
            
            
            
            exportDataItem.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    if (fileChooser.showOpenDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION)
                    try {
                        controller.saveToFile(fileChooser.getSelectedFile());
                            System.out.println(fileChooser.getSelectedFile());
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(MainFrame.this, "Could not save data to file.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                
            });
            
            
            exitItem.addActionListener(new ActionListener() {
                
                @Override
                public void actionPerformed(ActionEvent e) {
                    
                    // Demo changing an icon (JOptionPane.QUESTION_MESSAGE)
                    JOptionPane.showInputDialog(MainFrame.this, "Enter your user name.", "Enter User Name", JOptionPane.OK_OPTION | JOptionPane.QUESTION_MESSAGE);
                    
                    int action = JOptionPane.showConfirmDialog (MainFrame.this, "Do you really want to exit?", "Confirm Exit", JOptionPane.OK_CANCEL_OPTION);
                    
                    if(action == JOptionPane.OK_OPTION) {
                        WindowListener[] listeners = getWindowListeners();
                        
                        for(WindowListener listener: listeners)
                            listener.windowClosing(new WindowEvent(MainFrame.this, 0));
                    }
                }
            });
            
            showMenu.add(showFormItem);
            windowMenu.add(showMenu);
            
            menuBar.add(fileMenu);
            menuBar.add(windowMenu);

            
            
            return menuBar;
        }
    
}
