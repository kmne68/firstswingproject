/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeSelectionModel;

/**
 *
 * @author kemery
 */
public class MessagePanel extends JPanel {
    
    private JTree serverTree;
    private ServerTreeCellRenderer treeCellRenderer; 
    private ServerTreeCellEditor treeCellEditor;
    
    public MessagePanel() {
        
        treeCellRenderer = new ServerTreeCellRenderer();
        treeCellEditor = new ServerTreeCellEditor();
       
        serverTree = new JTree(createTree());
        serverTree.setCellRenderer(treeCellRenderer);
        serverTree.setCellEditor(treeCellEditor);
        serverTree.setEditable(true);
        
        // prevent selection of more than one leaf
        serverTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        
        treeCellEditor.addCellEditorListener(new CellEditorListener() {

            @Override
            public void editingStopped(ChangeEvent e) {
                
                ServerInfo info = (ServerInfo) treeCellEditor.getCellEditorValue();
                
                System.out.println(info + ": " + info.getId() + "; " + info.isChecked());
            }

            @Override
            public void editingCanceled(ChangeEvent e) {
            }
            
            
        });
        
        setLayout(new BorderLayout());
        
        add(new JScrollPane(serverTree), BorderLayout.CENTER);
    }

    
    private DefaultMutableTreeNode createTree() {

        DefaultMutableTreeNode top = new DefaultMutableTreeNode("Servers");

        DefaultMutableTreeNode branch1 = new DefaultMutableTreeNode("USA");
        DefaultMutableTreeNode server1 = new DefaultMutableTreeNode(new ServerInfo("New York", 0, true));
        DefaultMutableTreeNode server2 = new DefaultMutableTreeNode(new ServerInfo("Boston", 1, false));
        DefaultMutableTreeNode server3 = new DefaultMutableTreeNode(new ServerInfo("Los Angeles", 2, true));  

        branch1.add(server1);
        branch1.add(server2);
        branch1.add(server3);

        DefaultMutableTreeNode branch2 = new DefaultMutableTreeNode("UK");
        DefaultMutableTreeNode server4 = new DefaultMutableTreeNode(new ServerInfo("London", 3, false));
        DefaultMutableTreeNode server5 = new DefaultMutableTreeNode(new ServerInfo("Oxford", 4, true));
        DefaultMutableTreeNode server6 = new DefaultMutableTreeNode(new ServerInfo("Edinburgh", 5, true));

        branch2.add(server4);
        branch2.add(server5);
        branch2.add(server6);

        top.add(branch1);
        top.add(branch2);

        return top;
    }
    
}




