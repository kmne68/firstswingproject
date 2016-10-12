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
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * MainFrame uses the Controller when it wants to ad information to the database
 * @author kemery
 */
public class Controller {
    
    Database db = new Database();
    
    public List<Person> getPeople() {
        
        return db.getPeople();
    }
    
    
    public void configure(int port, String user, String password) throws Exception {
        
        db.configure(port, user, password);
    }
    
    
    public void removePerson(int index) {
        
        db.removePerson(index);
    }
    
    
    public void save() throws SQLException {
        
        db.save();
    }
    
    
    public void load() throws SQLException {
        
        db.load();
    }
    
    
    public void connect() throws Exception {
        
        db.connect();
    }
    
    
    public void disconnect() {
        
        db.disconnect();
    }
    
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
    
    
    public void saveToFile(File file) throws IOException {
        
        db.saveToFile(file);
    }
    
    
    public void loadFromFile(File file) throws IOException {
        
        db.loadFromFile(file);
    }
}
