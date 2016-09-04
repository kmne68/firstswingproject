/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kemery
 */
public class Database {
    
    private List<Person> people;
    private Connection con;
    
    public Database() {
        people = new LinkedList<Person>();
    }
    
    
    public void addPerson(Person person) {
        people.add(person);
    }
    
    
    public void removePerson(int index) {
        
        people.remove(index);
    }
    
    
    public List<Person> getPeople() {
        
        return Collections.unmodifiableList(people);
    }
    
    
    public void connect() throws Exception {
        
        if(con != null) return;
        try {
            Class.forName("com.mysql.jdbc.Driver");            
        } catch (ClassNotFoundException ex) {
            throw new Exception("Driver not found");
        }
        
        String connectionURL = "jdbc:mysql://localhost:3306/dandd";
        con = DriverManager.getConnection(connectionURL, "kmne68", "1sbmLam!0i");
        
        System.out.println("Database connection successful!" + con);
    }
    
    
    public void disconnect() {
        
        if(con != null)
            try {
            con.close();
        } catch (SQLException ex) {
            System.out.println("Connection cannot be closed.");
        }
    }
    
    
    public void saveToFile(File file) throws IOException {
        
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        
        Person[] persons = people.toArray(new Person[people.size()]);
        
        oos.writeObject(persons);
        
        oos.close();
    }
    
    
    public void loadFromFile(File file) throws IOException {
        
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        
        try {
            Person[] persons = (Person[]) ois.readObject();
            
            people.clear();
            
            people.addAll(Arrays.asList(persons));
        } catch (ClassNotFoundException e) {
                e.printStackTrace();
        }
    }
}
