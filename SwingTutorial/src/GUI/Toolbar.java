/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;

/**
 *
 * @author kemery
 */
public class Toolbar extends JToolBar implements ActionListener{
    
    private JButton btn_save;
    private JButton btn_refresh;
    private ToolbarListener textListener;
    
    // Constructor
    public Toolbar() {
        
        // Remove border to make the toolbar dragable
        // setBorder(BorderFactory.createEtchedBorder());
        setFloatable(false);
        
        btn_save = new JButton();
        btn_refresh = new JButton();
        btn_save.setToolTipText("Save");
        
        btn_save.setIcon(createIcon("/images/Save16.gif"));
        btn_refresh.setIcon(createIcon("/images/Refresh16.gif"));
        btn_refresh.setToolTipText("Refresh");
        
        btn_save.addActionListener(this);
        btn_refresh.addActionListener(this);        
        
        // setLayout(new FlowLayout(FlowLayout.LEFT));
        
        add(btn_save);
        // addSeparator();
        add(btn_refresh);
    }
    
    
    private ImageIcon createIcon(String path) {
        
        URL url = getClass().getResource(path);
        
        if(url == null) {
            
            System.err.println("Unable to load image: " + path);
        }
        ImageIcon icon = new ImageIcon(url);
          
        return icon;
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
