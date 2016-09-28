/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import GUI.MainFrame;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * Following tutorial at:
 * https://www.udemy.com/java-swing-complete/?couponCode=Y5Y2PEYD
 * @author kemery
 */
public class App {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {

                new MainFrame();

            }
        });
        

    }
    
}
