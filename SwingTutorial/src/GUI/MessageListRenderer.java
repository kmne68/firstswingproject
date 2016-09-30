/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Model.Message;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

/**
 *
 * @author kemery
 * 
 * Demonstrates use of an arbitrary component as a List Box Renderer).
 * JPanel is used for demonstration purposes, as JLabel would have been sufficient. 
 */
public class MessageListRenderer implements ListCellRenderer {
    
    private JPanel panel;
    private JLabel label;
    
    private Color selectedColor;
    private Color normalColor;
    
    public MessageListRenderer() {
        
        panel = new JPanel();
        label = new JLabel();
        
        selectedColor = new Color(210, 210, 255);
        normalColor = Color.WHITE;
        
        label.setIcon(Utils.createIcon("/images/Information24.gif"));
        
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        panel.add(label);
    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        
        Message message = (Message) value;
        
        label.setText(message.getTitle());
        
        panel.setBackground(cellHasFocus ? selectedColor : normalColor);
        
        return panel;
    }
    
}
