/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbtest;

import Model.AgeCategory;
import Model.Database;
import Model.EmploymentCategory;
import Model.Gender;
import Model.Person;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author kemery
 */
public class TestDatabase {
    
    public static void main(String[] args) {
            System.out.println("Running database test: ");
            
            Database db = new Database();
        try {            
            db.connect();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        db.addPerson(new Person("Joe", "builder", AgeCategory.adult, EmploymentCategory.employed, "123-45-67890", true, Gender.female));
        db.addPerson(new Person("Jill", "artist", AgeCategory.adult, EmploymentCategory.selfEmployed, null, true, Gender.female));

        
        try {
            db.save();
        } catch (SQLException ex) {
            Logger.getLogger(TestDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        db.disconnect();
    }

    
}
