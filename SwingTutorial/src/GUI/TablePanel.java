/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Model.Person;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author kemery
 */
public class TablePanel extends JPanel {
    
    private JTable table;
    private PersonTableModel tableModel;
    
    
    public TablePanel () {
        
        tableModel = new PersonTableModel();
        table = new JTable(tableModel);
        
        table.setGridColor(Color.black);
        
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
