/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author kemery
 */
public class FormPanel extends JPanel {
    
    private JLabel lbl_age;
    private JLabel lbl_employment;
    private JLabel lbl_name;
    private JLabel lbl_occupation;
    private JTextField txt_name;
    private JTextField txt_occupation;
    private JButton btn_ok;
    private FormListener formListener;
    private JList lst_ages;
    private JComboBox cbx_employment;
    private JCheckBox chk_citizen;
    private JTextField txt_taxField;
    private JLabel lbl_taxField;
    private JRadioButton rdo_male;
    private JRadioButton rdo_female;
    private ButtonGroup grp_gender;
    
    
    public FormPanel() {
        
        Dimension dim = getPreferredSize();
        dim.width = 250;
        setPreferredSize(dim);
        
        lbl_age = new JLabel("Age: ");
        lbl_name = new JLabel("Name: ");
        lbl_occupation = new JLabel("Occupation: ");
        lbl_employment = new JLabel("Employment: ");
        txt_name = new JTextField(10);
        txt_occupation = new JTextField(10);
        lst_ages = new JList();
        cbx_employment = new JComboBox();
        chk_citizen = new JCheckBox();
        txt_taxField = new JTextField(10);
        lbl_taxField = new JLabel("Tax ID: ");
        btn_ok = new JButton("OK");        
        
        // Set up tax ID
        lbl_taxField.setEnabled(false);
        txt_taxField.setEnabled(false);
        
        // Set up mnemonics
        btn_ok.setMnemonic(KeyEvent.VK_O);
        lbl_name.setDisplayedMnemonic(KeyEvent.VK_N);
        lbl_name.setLabelFor(txt_name);
        chk_citizen.setMnemonic(KeyEvent.VK_Z);
        
        chk_citizen.addActionListener(new ActionListener() {
        
        public void actionPerformed(ActionEvent arg0) {
            boolean isTicked = chk_citizen.isSelected();
            lbl_taxField.setEnabled(isTicked);
            txt_taxField.setEnabled(isTicked);
        }
    });
        
        // ListBox creation
        DefaultListModel ageModel = new DefaultListModel();
        ageModel.addElement(new AgeCategory(0, "Under 18"));
        ageModel.addElement(new AgeCategory(1,"18 to 65"));
        ageModel.addElement(new AgeCategory(2,"65 or older"));
        lst_ages.setModel(ageModel);
        lst_ages.setSelectedIndex(0);
        
        // ComboBox creation
        DefaultComboBoxModel employmentModel = new DefaultComboBoxModel();
        employmentModel.addElement("Employed");
        employmentModel.addElement("Self");
        employmentModel.addElement("Unemployed");
        cbx_employment.setModel(employmentModel);
        cbx_employment.setSelectedItem(0);
        cbx_employment.setEditable(true);
        
        
        lst_ages.setPreferredSize(new Dimension(110, 70));
        lst_ages.setBorder(BorderFactory.createBevelBorder(5));
        
        btn_ok.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String name = txt_name.getText();
                String occupation = txt_occupation.getText();
                AgeCategory ageCategory = (AgeCategory) lst_ages.getSelectedValue();
                String empStatus = (String) cbx_employment.getSelectedItem();
                String taxID = txt_taxField.getText();
                boolean usCitizen = chk_citizen.isSelected();
                String gender = grp_gender.getSelection().getActionCommand();
                                
                FormEvent ev = new FormEvent(this, name, occupation, ageCategory.getID(), empStatus, taxID, usCitizen, gender);
                
                if(formListener != null)
                    
                    formListener.formEventOccurred(ev);
            }
        });
        
        
        rdo_female = new JRadioButton("femal");
        rdo_male = new JRadioButton("male");
        grp_gender = new ButtonGroup();
        rdo_female.setActionCommand("female");
        rdo_male.setActionCommand("male");
        rdo_female.setSelected(true);
        
        // Set up gender buttons
        grp_gender.add(rdo_female);
        grp_gender.add(rdo_male);
                
        Border innerBorder = BorderFactory.createTitledBorder("Add Person");
        Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);
        
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
        
        layoutComponents();
        

        } // end FormPanel
    
    public void layoutComponents() {
        
        setLayout(new GridBagLayout());       
        
        GridBagConstraints gc = new GridBagConstraints();
        
        // ------------ Row 1
        gc.gridy = 0;       
        
        gc.weightx = 1;
        gc.weighty = 0.1;
        
        gc.gridx = 0;

        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0, 0, 0, 5);
        add(lbl_name, gc);
        
        gc.gridx = 1;
        gc.insets = new Insets(0, 0, 0, 0); 
        gc.anchor = GridBagConstraints.LINE_START;
        
        add(txt_name, gc);
        
        // ------------ Row 2
        gc.gridy++;        
        
        gc.weightx = 1;
        gc.weighty = 0.1;
        
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0, 0, 0, 5);        
        add(lbl_occupation, gc);
        
        gc.gridx = 1;

        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);        
        add(txt_occupation, gc);
        
        // ------------ Row 3  
        gc.gridy++; 
        
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        gc.insets = new Insets(0, 0, 0, 5);        
        add(lbl_age, gc);
        
        gc.weightx = 1;
        gc.weighty = .2;        
        
        gc.gridx = 1;

        gc.insets = new Insets(0, 0, 0, 0);        
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(lst_ages, gc);
        
        
        // ------------ Row 4  
       
        gc.gridy++;   
        
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0, 0, 20, 0);        
        add(lbl_employment, gc);        
        
         gc.weightx = 1;
        gc.weighty = .2;        
        
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(5, 0, 0, 0);        

        add(cbx_employment, gc); 
                
        
                // ------------ Row 5  
       
        gc.gridy++;   
        
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0, 0, 20, 0);        
        add(new JLabel("U.S. Citizen: "), gc);        
        
        gc.weightx = 1;
        gc.weighty = 0.2;        
        
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(5, 0, 0, 0);        

        add(chk_citizen, gc); 
        
        
        // ------------ Row 6  
       
        gc.gridy++;  
        
        gc.weightx = 1;
        gc.weighty = 0.1;        
        
        gc.gridx = 0;
        gc.insets = new Insets(0, 0, 0, 5); 
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_END;
       
        add(lbl_taxField, gc);        
        
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);        

        add(txt_taxField, gc);         
                
        // ------------ Row 7  
       
        gc.gridy++;  
        
        gc.weightx = 1;
        gc.weighty = 0.05;        
        
        gc.gridx = 0;
        gc.insets = new Insets(0, 0, 0, 0); 
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_END;
       
        add(new JLabel("Gender: "), gc);        
        
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);        

        add(rdo_male, gc);  
        
        // ------------ Row 8
        
                gc.gridy++;  
        
        gc.weightx = 1;
        gc.weighty = 0.2;        
        
        gc.gridx = 0;
        gc.insets = new Insets(0, 0, 0, 0); 
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_END;
       
     //   add(new JLabel("Gender: "), gc);        
        
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);        

        add(rdo_female, gc);  
        
        // ------------ Row 9
        
        gc.gridy++;           
        
        gc.weightx = 1;
        gc.weighty = 1.0;        
        
        gc.gridx = 1;

        gc.insets = new Insets(0, 0, 0, 0);        
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(btn_ok, gc);

    }
    
    public void setFormListener(FormListener listener) {
        
        this.formListener = listener;
    }
    
}

class AgeCategory {
        
    private int id;
    private String value;
        
    public AgeCategory(int id, String value) {
        this.id = id;
        this.value = value;

    }
    
    public String toString() {
        return value;
    }
    
    public int getID() {
        return id;
    }
            
}