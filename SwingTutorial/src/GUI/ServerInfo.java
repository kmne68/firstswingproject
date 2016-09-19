/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

/**
 *
 * @author kemery
 */
public class ServerInfo {
    
    private String name;
    private int id;
    private boolean checked;
    
    public ServerInfo(String name, int id, boolean checked) {
        
        this.name = name;
        this.id = id;
        this.checked = checked;
        
    }
    
    
    public int getId () {
        
        return id;
    }
    
    public String toString() {
        
        return name;
    }
    
    public boolean isChecked() {
        return checked;
    }
    
    
    public void setChecked(boolean checked) {
        
        this.checked = checked;
    }
}