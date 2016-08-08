/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swingtutorial;

import java.awt.Dimension;

import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 *
 * @author kemery
 */
public class FormPanel extends JPanel {
    
    public FormPanel() {
        
        Dimension dim = getPreferredSize();
        dim.width = 250;
        setPreferredSize(dim);
        
        Border innerBorder = BorderFactory.createTitledBorder("Add Person");
        Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);
        
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
    }
}