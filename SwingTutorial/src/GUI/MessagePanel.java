/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Model.Message;
import controller.MessageServer;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Window;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
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
public class MessagePanel extends JPanel implements ProgressDialogListener {
    
    private JTree serverTree;
    private ServerTreeCellRenderer treeCellRenderer; 
    private ServerTreeCellEditor treeCellEditor;
    
    private Set<Integer> selectedServers;
    private MessageServer messageServer;
    
    private ProgressDialog progressDialog;
    private SwingWorker<List<Message>, Integer> worker;
    
    private TextPanel textPanel;
    private JList messageList;
    private JSplitPane upperPane;
    private JSplitPane lowerPane;
    
    public MessagePanel(JFrame parent) {
        
        messageServer = new MessageServer();
        selectedServers = new TreeSet<Integer>();
        selectedServers.add(0);
        selectedServers.add(1);
        selectedServers.add(4);
        
        treeCellRenderer = new ServerTreeCellRenderer();
        treeCellEditor = new ServerTreeCellEditor();
       
        serverTree = new JTree(createTree());
        serverTree.setCellRenderer(treeCellRenderer);
        serverTree.setCellEditor(treeCellEditor);
        serverTree.setEditable(true);
        
        progressDialog = new ProgressDialog(parent, "Message downloading...");
        progressDialog.setProgressDialogListener(this);
        
        // prevent selection of more than one leaf
        serverTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        
        treeCellEditor.addCellEditorListener(new CellEditorListener() {

            @Override
            public void editingStopped(ChangeEvent e) {
                
                ServerInfo info = (ServerInfo) treeCellEditor.getCellEditorValue();
                
                System.out.println(info + ": " + info.getId() + "; " + info.isChecked());
                
                int serverId = info.getId();
                
                if(info.isChecked()) {
                    selectedServers.add(serverId);
                }
                else {
                    selectedServers.remove(serverId);
                }
                
                messageServer.setSelectedServers(selectedServers);
                
                retrieveMessages();
                

            }

            @Override
            public void editingCanceled(ChangeEvent e) {
            }
            
        });
        
        setLayout(new BorderLayout());
        
        textPanel = new TextPanel();
        messageList = new JList();
        
        lowerPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, messageList, textPanel);
        upperPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, new JScrollPane(serverTree), lowerPane);
        
        lowerPane.setMinimumSize(new Dimension(10, 100));
        messageList.setMinimumSize(new Dimension(10, 100));
        
        upperPane.setResizeWeight(0.5);
        lowerPane.setResizeWeight(0.5);
        
        add(upperPane, BorderLayout.CENTER);
    }
    
    
    private void retrieveMessages() {
        
        progressDialog.setMaximum(messageServer.getMessageCount());
            
        progressDialog.setVisible(true);
                
        worker = new SwingWorker<List<Message>, Integer>() {

            @Override
            protected void done() {
                
                progressDialog.setVisible(false);
                
                if(isCancelled())
                    return;
                
                try {
                    List<Message> retrievedMessages = get();
                    System.out.println("Retrieved " + retrievedMessages.size() + " messages");
                } catch (InterruptedException ex) {
                    
                    ex.printStackTrace();
                    // Logger.getLogger(MessagePanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ExecutionException ex) {
                    ex.printStackTrace();
                    // Logger.getLogger(MessagePanel.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
            }
            
            
            @Override
            protected void process(List<Integer> counts) {
                
                int retrieved = counts.get(counts.size() - 1);
                
                progressDialog.setValue(retrieved);
            }
            
            @Override
            protected List<Message> doInBackground() throws Exception {
                        
                List<Message> retrievedMessages = new ArrayList<Message>();
                
                int count = 0;
                
                for(Message message: messageServer) {
              
                    if(isCancelled())
                        break;
                    
                    System.out.println(message.getTitle());
                    
                    retrievedMessages.add(message);
                    
                    count++;
                    
                    publish(count);
                }
                
                return retrievedMessages;
                
            }
        };
        
        worker.execute();
    }

    
    private DefaultMutableTreeNode createTree() {

        DefaultMutableTreeNode top = new DefaultMutableTreeNode("Servers");

        DefaultMutableTreeNode branch1 = new DefaultMutableTreeNode("USA");
        DefaultMutableTreeNode server1 = new DefaultMutableTreeNode(new ServerInfo("New York", 0, selectedServers.contains(0)));
        DefaultMutableTreeNode server2 = new DefaultMutableTreeNode(new ServerInfo("Boston", 1, selectedServers.contains(1)));
        DefaultMutableTreeNode server3 = new DefaultMutableTreeNode(new ServerInfo("Los Angeles", 2, selectedServers.contains(2)));  

        branch1.add(server1);
        branch1.add(server2);
        branch1.add(server3);

        DefaultMutableTreeNode branch2 = new DefaultMutableTreeNode("UK");
        DefaultMutableTreeNode server4 = new DefaultMutableTreeNode(new ServerInfo("London", 3, selectedServers.contains(3)));
        DefaultMutableTreeNode server5 = new DefaultMutableTreeNode(new ServerInfo("Oxford", 4, selectedServers.contains(4)));
        DefaultMutableTreeNode server6 = new DefaultMutableTreeNode(new ServerInfo("Edinburgh", 5, selectedServers.contains(5)));

        branch2.add(server4);
        branch2.add(server5);
        branch2.add(server6);

        top.add(branch1);
        top.add(branch2);

        return top;
    }

    @Override
    public void progressDialogCancelled() {
        
        if(worker != null) {
            
            worker.cancel(true);
        }
    }
    
}




