/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pong;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author Alumno
 */
public class Pong {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PropertyConfigurator.configure("log4j.properties");
        
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Canvas canvas = new Canvas();
        
        frame.getContentPane().add(canvas,BorderLayout.CENTER);
        frame.pack();
        
        Thread t1 = new Thread(canvas, "T1");
        t1.start();
        
        frame.setVisible(true);
    }
    
}
