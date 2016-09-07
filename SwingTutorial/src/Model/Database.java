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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

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
    
    
    public void load() throws SQLException {
        
        people.clear();
        
        Statement selectStatement = con.createStatement();
        
        String sql = "select id, name, age, employment_status, tax_id, us_citizen, gender, occupation from people order by name";
        ResultSet results = selectStatement.executeQuery(sql);
        
        while(results.next()) {
            int id = results.getInt("id");
            String name = results.getString("name");
            String age = results.getString("age");
            String emp = results.getString("employment_status");
            String taxID = results.getString("tax_id");
            Boolean is_us = results.getBoolean("us_citizen");
            String gender = results.getString("gender");
            String occ = results.getString("occupation");                    
            
            // should add exception handling for enum values that can't be converted
            Person person = new Person(id, name, occ, AgeCategory.valueOf(age), EmploymentCategory.valueOf(emp), taxID, is_us, Gender.valueOf(gender));
            
            people.add(person);
            
            System.out.println(person);
            
        }
        
        results.close();
        selectStatement.close();
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
        
        String connectionURL = "jdbc:mysql://localhost:3306/swing";
        
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
    
    
    public void save() throws SQLException {
        
        String checkSQL = "Select count(*) as count from people where id = ?";
        
        PreparedStatement checkStatement = con.prepareStatement(checkSQL);
        
        String insertSQL = "insert into people (id, name, age, employment_status, tax_id, us_citizen, gender, occupation) values (?,?,?,?,?,?,?,?) ";
        PreparedStatement insertStatement = con.prepareStatement(insertSQL);
        
        String updateSQL = "update people set name=?, age=?, employment_status=?, tax_id=?, us_citizen=?, gender=?, occupation=? where id=?";
        PreparedStatement updateStatement = con.prepareStatement(updateSQL);
        
        
        for(Person person: people) {
            int id = person.getId();
            String name = person.getName();
            String occupation = person.getOccupation();
            AgeCategory age = person.getAgeCategory();
            EmploymentCategory emp = person.getEmploymentCategory();
            String taxID = person.getTaxID();
            boolean isCitizen = person.isUsCitizen();
            Gender gender = person.getGender();
                    
            
            checkStatement.setInt(1, id);
            
            ResultSet checkResult = checkStatement.executeQuery();
            
            checkResult.next();
            int count = checkResult.getInt(1);
            
            if(count == 0) {
                System.out.println("Inserting person with ID " + id);
                
                int col = 1;
                insertStatement.setInt(col++, id);
                insertStatement.setString(col++, name);
                insertStatement.setString(col++, age.name());
                insertStatement.setString(col++, emp.name());
                insertStatement.setString(col++, taxID);
                insertStatement.setBoolean(col++, isCitizen);
                insertStatement.setString(col++, gender.name());
                insertStatement.setString(col++, occupation);
                
                insertStatement.executeUpdate();
            }
            else {
                System.out.println("Updating person with ID " + id);
                
                int col = 1;
                updateStatement.setString(col++, name);
                updateStatement.setString(col++, age.name());
                updateStatement.setString(col++, emp.name());
                updateStatement.setString(col++, taxID);
                updateStatement.setBoolean(col++, isCitizen);
                updateStatement.setString(col++, gender.name());
                updateStatement.setString(col++, occupation);
                updateStatement.setInt(col++, id);
                
                updateStatement.executeUpdate();
                
            }
            
        }
        updateStatement.close();
        insertStatement.close();
        checkStatement.close();
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
