/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Model.Person;
import java.awt.BorderLayout;
import java.awt.Color;
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
    
    public TablePanel () {
        
        tableModel = new PersonTableModel();
        table = new JTable(tableModel);
        popup = new JPopupMenu();
        
        JMenuItem removeItem = new JMenuItem("Delete row");
        popup.add(removeItem);
        
        
        
        table.setGridColor(Color.black);           
        table.addMouseListener(new MouseAdapter() {
        
            @Override
            public void mousePressed(MouseEvent me) {
               if(me.getButton() == MouseEvent.BUTTON3) {
                   popup.show(table, me.getX(), me.getY());
               }
              //  super.mousePressed(me); 
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
}
