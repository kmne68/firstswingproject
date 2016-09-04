/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbtest;

import Model.Database;
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
            db.disconnect();
    }

    
}
