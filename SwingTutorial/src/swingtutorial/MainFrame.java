/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swingtutorial;

import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

/**
 *
 * @author kemery
 */
public class MainFrame extends JFrame {
    
    private TextPanel textPanel;
    private Toolbar toolbar;
    private FormPanel formPanel;
    
    // Constructor
    public MainFrame() {    
        super("Helo World");
        
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);  
        setLayout(new BorderLayout());   
        
        textPanel = new TextPanel();
        toolbar = new Toolbar();
        formPanel = new FormPanel();
        
        toolbar.setStringListener(new StringListener()
        {
        
            public void textEmitted(String text) {
                textPanel.appendText(text);
            }        
            
        }); 
        
        formPanel.setFormListener(new FormListener() {
            
            public void formEventOccurred(FormEvent e) {
            
                String name = e.getName();
                String occupation = e.getOccupation();
                int ageCategory = e.getAgeCategory();

                textPanel.appendText(name + ": " + occupation + ": " + ageCategory + " " + "\n" );
            }
        });
        
        
        add(toolbar, BorderLayout.NORTH);  
        add(formPanel, BorderLayout.WEST);
        add(textPanel, BorderLayout.CENTER);

    }
}
