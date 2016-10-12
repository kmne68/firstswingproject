/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author kemery
 */
public enum EmploymentCategory {
    
    employed ("employed"),
    selfEmployed ("self-employed"),
    unemployed ("unemployed"),
    other ("other");
    
    private String text;
    
    private EmploymentCategory(String text) {
        
        this.text = text;
    }
    
    @Override
    public String toString() {
        
        return text;
    }
    
}
