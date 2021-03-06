/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Model.EmploymentCategory;
import Model.Person;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author kemery
 */
public class TablePanel extends JPanel {
    
    private JTable table;
    private PersonTableModel tableModel;
    private JPopupMenu popup;
    private PersonTableListener personTableListener;
    
    public TablePanel () {
        
        tableModel = new PersonTableModel();
        table = new JTable(tableModel);
        popup = new JPopupMenu();
        
        table.setDefaultRenderer(EmploymentCategory.class, new EmploymentCategoryRenderer());
        table.setDefaultEditor(EmploymentCategory.class, new EmploymentCategoryEditor());
        table.setRowHeight(25);
        
        JMenuItem removeItem = new JMenuItem("Delete row");
        popup.add(removeItem);
        
        
        
        table.setGridColor(Color.black);           
        table.addMouseListener(new MouseAdapter() {
        
            @Override
            public void mousePressed(MouseEvent me) {
                
                int row = table.rowAtPoint(me.getPoint());
       //         System.out.println(row);
                
                table.getSelectionModel().setSelectionInterval(row, row);
                if(me.getButton() == MouseEvent.BUTTON3) {
                   popup.show(table, me.getX(), me.getY());
               }
              //  super.mousePressed(me); 
            }
    });
        
        removeItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
                int row = table.getSelectedRow();
                
                if(personTableListener != null) {
                    personTableListener.rowDeleted(row);
                    tableModel.fireTableRowsDeleted(row, row);
                }
                System.out.println(row);
            }
        });
        
    
        
        setLayout(new BorderLayout());
                
        add(new JScrollPane(table), BorderLayout.CENTER);
    }
    
    
    public void setData(List<Person> db) {
        
        tableModel.setData(db);
    }
    
    
    public void refresh() {
        
        tableModel.fireTableDataChanged();
    }
    
    
    public void setPersonTableListener(PersonTableListener listener) {
        
        this.personTableListener = listener;
    }
}
