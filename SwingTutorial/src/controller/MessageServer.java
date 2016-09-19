/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Model.Message;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kemery
 * 
 * This is a simulated message server.
 * 
 */
public class MessageServer implements Iterable<Message> {
    
    private Map<Integer, List<Message>> messages;
    private List<Message> selected;
    
    public MessageServer () {
        
        selected = new ArrayList<Message>();
        messages = new TreeMap<Integer, List<Message>>();
        
        List<Message> list = new ArrayList<Message>();         
        list = new ArrayList<Message>();
        list.add(new Message("vini, vidi, vici", "post hoc ad hoc"));
        messages.put(0, list); 

        list.add(new Message("lorem ipsum", "sic semper tyrannis"));
        list.add(new Message("e pluribus unum", "semper ubi sub ubi"));
        messages.put(1, list);
        
        list = new ArrayList<Message>();
        list.add(new Message("cacoethes scribendi", "calix meus inebrians"));
        list.add(new Message("calamus gladio fortior", "Carthago delenda est"));
        messages.put(5, list);
    }
    
    
    public void setSelectedServers(Set<Integer> servers) {
        
        selected.clear();
        
        for(Integer id: servers) {
            if(messages.containsKey(id)) {
                selected.addAll(messages.get(id));
            }
        }
    }
    
    
    public int getMessageCount() {
        
        return selected.size();
    }

    @Override
    public Iterator<Message> iterator() {
        
        return new MessageIterator(selected);
    }
    
}


class MessageIterator implements Iterator {
    
    private Iterator<Message> iterator;
    
    public MessageIterator(List<Message> messages) {
        
        iterator = messages.iterator();
    }

    @Override
    public boolean hasNext() {
        
        return iterator.hasNext();
    }

    @Override
    public Object next() {
        
        try {
            Thread.sleep(1000); // to simulate a slow server
        } catch (InterruptedException ex) {
            Logger.getLogger(MessageIterator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return iterator.next();
    }
    
    
    public void remove() {
        
        iterator.remove();
    }
}