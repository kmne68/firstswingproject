/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swingtutorial;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author kemery
 */
public class FormPanel extends JPanel {
    
    private JLabel lbl_name;
    private JLabel lbl_occupation;
    private JTextField txt_name;
    private JTextField txt_occupation;
    private JButton btn_ok;
    
    public FormPanel() {
        
        Dimension dim = getPreferredSize();
        dim.width = 250;
        setPreferredSize(dim);
        
        lbl_name = new JLabel("Name: ");
        lbl_occupation = new JLabel("Occupation: ");
        txt_name = new JTextField(10);
        txt_occupation = new JTextField(10);
        
        btn_ok = new JButton("OK");
        
        Border innerBorder = BorderFactory.createTitledBorder("Add Person");
        Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);
        
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
        
        setLayout(new GridBagLayout());
        
        GridBagConstraints gc = new GridBagConstraints();
        
        // ------------ Row 1
        gc.weightx = 1;
        gc.weighty = 0.1;
        
        gc.gridx = 0;
        gc.gridy = 0;

        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0, 0, 0, 5);
        add(lbl_name, gc);
        
        gc.gridx = 1;
        gc.gridy = 0;
        gc.insets = new Insets(0, 0, 0, 0); 
        gc.anchor = GridBagConstraints.LINE_START;
        
        add(txt_name, gc);
        
        // ------------ Row 2 
        gc.weightx = 1;
        gc.weighty = 0.1;
        
        gc.gridx = 0;
        gc.gridy = 1;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0, 0, 0, 5);        
        add(lbl_occupation, gc);
        
        gc.gridx = 1;
        gc.gridy = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);        
        add(txt_occupation, gc);
        
        // ------------ Row 3  
        gc.weightx = 1;
        gc.weighty = 2.0;        
        
        gc.gridx = 1;
        gc.gridy = 2;
        gc.insets = new Insets(0, 0, 0, 0);        
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(btn_ok, gc);
        
    }
}