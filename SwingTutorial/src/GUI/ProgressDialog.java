/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.Cursor;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

/**
 *
 * @author kemery
 */
public class ProgressDialog extends JDialog {
    
    private JButton btn_cancel;
    private JProgressBar progressBar;
    private ProgressDialogListener progressDialogListener;
        
    public ProgressDialog(Window parent, String title) {
        super(parent, title, ModalityType.APPLICATION_MODAL);
        
        btn_cancel = new JButton("Cancel");
        progressBar = new JProgressBar();
        progressBar.setStringPainted(true);
        progressBar.setString("Retrieving messges...");
        progressBar.setMaximum(10);
        
        // progressBar.setIndeterminate(true); // Enable if the maximum is not known
        
        setLayout(new FlowLayout());
        
        Dimension size = btn_cancel.getPreferredSize();
        size.width = 400;
        progressBar.setSize(size);
        
        add(progressBar);
        add(btn_cancel);
        
        btn_cancel.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                
                if(progressDialogListener != null) {
                    progressDialogListener.progressDialogCancelled();
                }
            }
        });
        
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        
        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                
                if(progressDialogListener != null) {
                    progressDialogListener.progressDialogCancelled();
                }
            }
        
        
        });
        
        pack();
        
        setLocationRelativeTo(parent);
        
    }
    
    
    public void setProgressDialogListener(ProgressDialogListener listener) {
        
        this.progressDialogListener = listener;
    }
    
    
    public void setMaximum(int value) {
        
        progressBar.setMaximum(value);
    }
    
    
    public void setValue(int value) {
        
        int progress = 100 * value / progressBar.getMaximum();
        progressBar.setString(String.format("%d%% complete", progress));
        
        progressBar.setValue(value);
    }
    
    
    @Override
    public void setVisible(final boolean visible) {        
        
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                
                if(visible == false) {
                    
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ProgressDialog.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else {
                    progressBar.setValue(0);
                }
                
                if(visible) {                    
                    setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                }
                else {
                    setCursor(Cursor.getDefaultCursor());
                }
                
                ProgressDialog.super.setVisible(visible);
            }
        });
        
    }
           
}
