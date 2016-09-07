/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author kemery
 */
public class Toolbar extends JPanel implements ActionListener{
    
    private JButton btn_save;
    private JButton btn_refresh;
    private ToolbarListener textListener;
    
    // Constructor
    public Toolbar() {
        
        setBorder(BorderFactory.createEtchedBorder());
        
        btn_save = new JButton("Save");
        btn_refresh = new JButton("Refresh");
        
        btn_save.addActionListener(this);
        btn_refresh.addActionListener(this);        
        
        setLayout(new FlowLayout(FlowLayout.LEFT));
        
        add(btn_save);
        add(btn_refresh);
    }
    
    
    public void setToolbarListener(ToolbarListener listener) {

        this.textListener = listener;
    }
   
    
    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        
        JButton clicked = (JButton)e.getSource();
        
        if (clicked == btn_save) {
            if(textListener != null) {
                textListener.saveEventOccurred();
            }
        }
        else if (clicked == btn_refresh) {
            if (textListener != null) {
                textListener.refreshEventOccurred();
            }
        }
    }
                
    //        textPanel.appendText("Goodbye\n");
// throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            
}
