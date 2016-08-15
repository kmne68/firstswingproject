/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swingtutorial;

import java.util.EventObject;

/**
 *
 * @author kemery
 */
public class FormEvent extends EventObject {

    private String name;
    private String occupation;
    private int ageCategory;
    private String employmentStatus;
    private String taxID;
    private boolean usCitizen;
    
    public FormEvent(Object source) {
        super(source);     
        
    }
    
    public FormEvent(Object source, String name, String occupation, int ageCategory, String employmentStatus, String taxID, boolean usCitizen) {
        super(source);
        
        this.name = name;
        this.occupation = occupation;
        this.ageCategory = ageCategory;
        this.employmentStatus = employmentStatus;
        this.taxID = taxID;
        this.usCitizen = usCitizen;
    }
         
    
    public int getAgeCategory() {
        return ageCategory;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the occupation
     */
    public String getOccupation() {
        return occupation;
    }

    /**
     * @param occupation the occupation to set
     */
    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }
    
    public String getEmploymentStatus() {
        
        return employmentStatus;        
    }

    /**
     * @return the taxID
     */
    public String getTaxID() {
        return taxID;
    }


    /**
     * @return the usCitizen
     */
    public boolean isUsCitizen() {
        return usCitizen;
    }

            
    
    
    
}
