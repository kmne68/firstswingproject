/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author kemery
 */
public class Utils {
    
    public static String getFileExtension(String name) {
        
        int pointIndex = name.lastIndexOf(".");
        
        if(pointIndex == -1) {
            return null;
        }
        
        if(pointIndex == name.length() - 1) {
            return null;
        }
        
        return name.substring(pointIndex+1, name.length());
        
    }
    
    
    public static ImageIcon createIcon(String path) {
        
        URL url = System.class.getResource(path);
        
        if(url == null) {
            
            System.err.println("Unable to load image: " + path);
        }
        ImageIcon icon = new ImageIcon(url);
          
        return icon;
    }
    
    
    public static Font createFont(String path) {
        
        URL url = System.class.getResource(path);
        
        if(url == null) {
            
            System.err.println("Unable to load font: " + path);
        }
        Font font = null;        
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, url.openStream());
        } catch (FontFormatException ex) {
            System.err.println("Bad format in font file: " + path);
        } catch (IOException ex) {
            System.err.println("Unable to read from file: " + path);
        }
          
        return font;
    }
    
    
}
