/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Model.Person;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author kemery
 */
public class PersonTableModel extends AbstractTableModel {
    
    private static final int COLUMNS = 8;
    
    private List<Person> db;
    private String[] colNames = { "ID", "Name", "Occupation", "Age Category", "Employment Category", "US Citizen", "Tax ID", "Gender" }; 
    
    public PersonTableModel() {
        
    }

    @Override
    public String getColumnName(int column) {
        return colNames[column]; //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    public void setData(List<Person> db) {
        
        this.db = db;
    }

    @Override
    public int getRowCount() {
        
        return db.size();
    }

    @Override
    public int getColumnCount() {
        
        return COLUMNS;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        Person person = db.get(rowIndex);
        
        switch(columnIndex) {
            case 0:
                return person.getId();
            case 1:
                return person.getName();
            case 2:
                return person.getOccupation();
            case 3:
                return person.getAgeCategory();
            case 4:
                return person.getEmploymentCategory();
            case 5:
                return person.isUsCitizen();
            case 6:
                return person.getTaxID();
            case 7:
                return person.getGender();
            default:
                return null;
            
        }
    }
    
}
