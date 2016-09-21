/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Window;
import javax.swing.JDialog;

/**
 *
 * @author kemery
 */
public class ProgressDialog extends JDialog {
        
    public ProgressDialog(Window parent) {
        super(parent, "Message downloading...", ModalityType.APPLICATION_MODAL);
        
        setSize(400, 200);
    }
    
}
