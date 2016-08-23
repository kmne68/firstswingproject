/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kemery
 */
public class Database {
    
    private ArrayList<Person> people;
    
    public Database() {
        people = new ArrayList<Person>();
    }
    
    
    public void addPerson(Person person) {
        people.add(person);
    }
    
    
    public List<Person> getPeople() {
        
        return people;
    }
}
