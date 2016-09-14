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
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

/**
 *
 * @author kemery
 */
public class MessagePanel extends JPanel {
    
    
    private JTree serverTree;
    
    public MessagePanel() {
        
        serverTree = new JTree(createTree());
        
        // prevent selection of more than one leaf
        serverTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        
        serverTree.addTreeSelectionListener(new TreeSelectionListener() {

            @Override
            public void valueChanged(TreeSelectionEvent e) {
                
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) serverTree.getLastSelectedPathComponent();
                
                Object userObject = node.getUserObject();
                
                if(userObject instanceof ServerInfo) {
                   
                    int id = ((ServerInfo)userObject).getId();
                    System.out.println("ServerInfo userObject ID = " + id);
                }
                
                System.out.println(userObject);
            }
            
        });
        
        setLayout(new BorderLayout());
        
        add(new JScrollPane(serverTree), BorderLayout.CENTER);
    }

    
    private DefaultMutableTreeNode createTree() {

        DefaultMutableTreeNode top = new DefaultMutableTreeNode("Servers");

        DefaultMutableTreeNode branch1 = new DefaultMutableTreeNode("USA");
        DefaultMutableTreeNode server1 = new DefaultMutableTreeNode(new ServerInfo("New York", 0));
        DefaultMutableTreeNode server2 = new DefaultMutableTreeNode(new ServerInfo("Boston", 1));
        DefaultMutableTreeNode server3 = new DefaultMutableTreeNode(new ServerInfo("Los Angeles", 2));  

        branch1.add(server1);
        branch1.add(server2);
        branch1.add(server3);

        DefaultMutableTreeNode branch2 = new DefaultMutableTreeNode("UK");
        DefaultMutableTreeNode server4 = new DefaultMutableTreeNode(new ServerInfo("London", 3));
        DefaultMutableTreeNode server5 = new DefaultMutableTreeNode(new ServerInfo("Oxford", 4));
        DefaultMutableTreeNode server6 = new DefaultMutableTreeNode(new ServerInfo("Edinburgh", 5));

        branch2.add(server4);
        branch2.add(server5);
        branch2.add(server6);

        top.add(branch1);
        top.add(branch2);

        return top;
    }
    
}



class ServerInfo {
    
    private String name;
    private int id;
    
    public ServerInfo(String name, int id) {
        
        this.name = name;
        this.id = id;
    }
    
    
    public int getId () {
        
        return id;
    }
    
    public String toString() {
        
        return name;
    }
}
