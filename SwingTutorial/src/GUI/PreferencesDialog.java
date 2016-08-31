/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author kemery
 */
public class PreferencesDialog extends JDialog {
    
    private JButton btn_ok;
    private JButton btn_cancel;
    private JSpinner portSpinner;
    private SpinnerNumberModel spinnerModel;
    private JTextField txt_username;
    private JPasswordField password;
    
    private PreferencesListener prefsListener;
    
    public PreferencesDialog(JFrame parent) {
        super(parent, "Preferences", false);
        
        btn_ok = new JButton("OK");
        btn_cancel = new JButton("Cancel");
        
        spinnerModel = new SpinnerNumberModel(3306, 0, 9999, 1);
        portSpinner = new JSpinner(spinnerModel);
        
        txt_username = new JTextField(10);
        password = new JPasswordField(10);
        
        password.setEchoChar('*');
        
        setLayout(new GridBagLayout());
        
        GridBagConstraints gc = new GridBagConstraints();
        
        gc.gridy = 0;
        
        // first row
        gc.weightx = 1;
        gc.weighty = 1;
        gc.fill = GridBagConstraints.NONE;
        
        gc.gridx = 0;
        
        add(new JLabel("User: "), gc);
        
        gc.gridx++;
        add(txt_username, gc);  
        
        // next row
        gc.gridy++;
        
        gc.weightx = 1;
        gc.weighty = 1;
        gc.fill = GridBagConstraints.NONE;
        
        gc.gridx = 0;
        
        add(new JLabel("Password: "), gc);
        
        gc.gridx++;
        add(password, gc);         
        
        
        // next row
        gc.gridy++;
        
        gc.weightx = 1;
        gc.weighty = 1;
        gc.fill = GridBagConstraints.NONE;
        
        gc.gridx = 0;
        
        add(new JLabel("Port: "), gc);
        gc.gridx++;
        add(portSpinner, gc);
        
        // next row
        gc.gridy++;
        gc.gridx = 0;
        add(btn_ok, gc);
        
        gc.gridx++;
        add(btn_cancel, gc);
        
        btn_ok.addActionListener(new ActionListener() {
           
            @Override
            public void actionPerformed(ActionEvent e) {
                
                Integer port = (Integer)portSpinner.getValue();
                
                String user = txt_username.getText();
                char[] passwordArray = password.getPassword();
                
        //        System.out.println(user + " " + new String(passwordArray));
                
                if (prefsListener != null) {
                    prefsListener.preferencesSet(user, new String (passwordArray), port);
                    
                }
                setVisible(false);
            }
        });
        
        
        btn_cancel.addActionListener(new ActionListener() {
           
            @Override
            public void actionPerformed(ActionEvent e) {
                
                setVisible(false);
            }
        });
        
        
        setSize(400, 300);
        setLocationRelativeTo(parent);
        
    } 
    
    
    public void setDefaults(String user, String password, int port) {
        
        txt_username.setText(user);
        this.password.setText(password);  // this is confusing and I should probably change one of them
        portSpinner.setValue(port);
        
    }

    public void setPreferencesListener(PreferencesListener preferencesListener) {

        this.prefsListener = preferencesListener;
    }
    
    
}
