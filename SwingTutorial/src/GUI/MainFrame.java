/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.sun.glass.events.KeyEvent;
import controller.Controller;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import static java.util.Locale.filter;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

/**
 *
 * @author kemery
 */
public class MainFrame extends JFrame {
    
    private TextPanel textPanel;
    private Toolbar toolbar;
    private FormPanel formPanel;
    private JFileChooser fileChooser;
    private Controller controller;
    
    
    // Constructor
    public MainFrame() {    
        super("Helo World");
        
        setSize(600, 500);
        setMinimumSize(new Dimension(600, 500));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);  
        setLayout(new BorderLayout());  
        setJMenuBar(createMenuBar());
        
        textPanel = new TextPanel();
        toolbar = new Toolbar();
        formPanel = new FormPanel();
        
        controller = new Controller();
        
        fileChooser = new JFileChooser();
        fileChooser.addChoosableFileFilter(new PersonFileFilter());
        
        
        toolbar.setStringListener(new StringListener()
        {
        
            public void textEmitted(String text) {
                textPanel.appendText(text);
            }        
            
        }); 
        
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

            //    textPanel.appendText(name + ": " + occupation + ": " + ageCategory + " " + ": " + employmentStatus+ ": " +  usCitizen + ": " +  taxID + ": " + gender +"\n" );
            }
        });
        

        
        
        add(toolbar, BorderLayout.NORTH);  
        add(formPanel, BorderLayout.WEST);
        add(textPanel, BorderLayout.CENTER);

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
            
            JMenu windowMenu = new JMenu("Window");            
            JMenu showMenu = new JMenu("Show");
            JCheckBoxMenuItem showFormItem = new JCheckBoxMenuItem("Person Form");
            showFormItem.setSelected(true);
            
            showFormItem.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    
                    JCheckBoxMenuItem menuItem = (JCheckBoxMenuItem) e.getSource();
                    formPanel.setVisible(menuItem.isSelected());
                }
            });
            
            fileMenu.setMnemonic(KeyEvent.VK_F);
            exitItem.setMnemonic(KeyEvent.VK_X);
            
            exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
            
            importDataItem.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    if (fileChooser.showOpenDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION)
                        System.out.println(fileChooser.getSelectedFile());
                    
                }
                
            });
            
            
            
            exportDataItem.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    if (fileChooser.showSaveDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION)
                        System.out.println(fileChooser.getSelectedFile());
                    
                }
                
            });
            
            exitItem.addActionListener(new ActionListener() {
                
                @Override
                public void actionPerformed(ActionEvent e) {
                    
                    // Demo changing an icon (JOptionPane.QUESTION_MESSAGE)
                    JOptionPane.showInputDialog(MainFrame.this, "Enter your user name.", "Enter User Name", JOptionPane.OK_OPTION | JOptionPane.QUESTION_MESSAGE);
                    
                    int action = JOptionPane.showConfirmDialog (MainFrame.this, "Do you really want to exit?", "Confrim Exit", JOptionPane.OK_CANCEL_OPTION);
                    
                    if(action == JOptionPane.OK_OPTION) {
                        System.exit(0);
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
