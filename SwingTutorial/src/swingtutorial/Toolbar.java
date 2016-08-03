/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swingtutorial;

import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import javafx.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author kemery
 */
public class Toolbar extends JPanel implements ActionListener{
    
    private JButton btnHello;
    private JButton btnGoodbye;
    
    private TextPanel textPanel;
    
    public Toolbar() {
        btnHello = new JButton("Hello");
        btnGoodbye = new JButton("Goodbye");
        
        btnHello.addActionListener(this);
        btnGoodbye.addActionListener(this);        
        
        setLayout(new FlowLayout(FlowLayout.LEFT));
        
        add(btnHello);
        add(btnGoodbye);
    }
    
    
    public void setTextPanel(TextPanel panel) {

        this.textPanel = panel;
    }
   
    
    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        
        JButton clicked = (JButton)e.getSource();
        
        if (clicked == btnHello)
            textPanel.appendText("Hello\n");
        else
            textPanel.appendText("Goodbye\n");
// throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
            
}
