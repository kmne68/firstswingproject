/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import GUI.FormEvent;
import Model.AgeCategory;
import Model.Database;
import Model.EmploymentCategory;
import Model.Gender;
import Model.Person;

/**
 * MainFrame uses the Controller when it wants to ad information to the database
 * @author kemery
 */
public class Controller {
    
    Database db = new Database();
    
    // ideally we would create a method to accept data from the MainFrame and then
    // create a person object with it. For now we are accepting data through a 
    // FormEvent
    public void addPerson(FormEvent e) {
        
        String name = e.getName();
        String occupation = e.getOccupation();
        int ageCategoryID = e.getAgeCategory();
        String employmentStatus = e.getEmploymentStatus();
        boolean usCitizen = e.isUsCitizen();
        String taxID = e.getTaxID();
        String gender = e.getGender(); 
        
        AgeCategory ageCategory = null;
        
        switch(ageCategoryID) {
            case 0:
                ageCategory = AgeCategory.child;
                break;
            case 1:
                ageCategory = AgeCategory.adult;
                break;
            case 2:
                ageCategory = AgeCategory.senior;
                break;                
        }
        
        EmploymentCategory employmentCategory;
        
        if(employmentStatus.equals("employed")) {
            employmentCategory = EmploymentCategory.employed;
        }
        else if (employmentStatus.equals("self-employed")) {
            employmentCategory = EmploymentCategory.selfEmployed;
        }
        else if (employmentStatus.equals("unemployed")) {
            employmentCategory = EmploymentCategory.unemployed;
        }
        else {
            employmentCategory = EmploymentCategory.other;
            System.err.println(employmentStatus);
        }
        
        Gender genderCategory;
        
        if(gender.equals("male")) {
            genderCategory = Gender.male;
        }
        else {
            genderCategory = Gender.female;
        }
        
        Person person = new Person(name, occupation, ageCategory, employmentCategory, taxID, usCitizen, genderCategory);
        
        db.addPerson(person);
    }
}
