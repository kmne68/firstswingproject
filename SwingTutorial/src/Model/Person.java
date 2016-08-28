/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;

/**
 *
 * @author kemery
 */
public class Person implements Serializable {
    
    private static int count;
    private int id;
    private String name;
    private String occupation;
    private AgeCategory ageCategory;
    private EmploymentCategory employmentCategory;
    private String taxID;
    private boolean usCitizen;
    private Gender gender;
    
    
    public Person(String name, String occupation, AgeCategory ageCategory, EmploymentCategory employmentCategory,
            String taxID, boolean usCitizen, Gender gender) {
        
        this.id = count;
        this.name = name;
        this.occupation = occupation;
        this.ageCategory = ageCategory;
        this.employmentCategory = employmentCategory;
        this.taxID = taxID;
        this.usCitizen = usCitizen;
        this.gender = gender;
        
        count++;
    }
    

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
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

    /**
     * @return the ageCategory
     */
    public AgeCategory getAgeCategory() {
        return ageCategory;
    }

    /**
     * @param ageCategory the ageCategory to set
     */
    public void setAgeCategory(AgeCategory ageCategory) {
        this.ageCategory = ageCategory;
    }

    /**
     * @return the employmentCategory
     */
    public EmploymentCategory getEmploymentCategory() {
        return employmentCategory;
    }

    /**
     * @param employmentCategory the employmentCategory to set
     */
    public void setEmploymentCategory(EmploymentCategory employmentCategory) {
        this.employmentCategory = employmentCategory;
    }

    /**
     * @return the taxID
     */
    public String getTaxID() {
        return taxID;
    }

    /**
     * @param taxID the taxID to set
     */
    public void setTaxID(String taxID) {
        this.taxID = taxID;
    }

    /**
     * @return the usCitizen
     */
    public boolean isUsCitizen() {
        return usCitizen;
    }

    /**
     * @param usCitizen the usCitizen to set
     */
    public void setUsCitizen(boolean usCitizen) {
        this.usCitizen = usCitizen;
    }

    /**
     * @return the gender
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(Gender gender) {
        this.gender = gender;
    }
    
    
}
