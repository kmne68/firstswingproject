/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Model.EmploymentCategory;
import java.awt.Component;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

    
/**
 *
 * @author Keith
 */
public class EmploymentCategoryRenderer implements TableCellRenderer {

    private JComboBox combo;
    
    public EmploymentCategoryRenderer() {
        
        combo = new JComboBox(EmploymentCategory.values());
        
        }

    @Override
    public Component getTableCellRendererComponent(JTable jtable, Object value, boolean isSelected , boolean hasFocus, int row, int column) {
       
        combo.setSelectedItem(value);
        return combo;
    }
    
}
