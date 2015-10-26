/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pong;

import java.awt.BorderLayout;
import javax.swing.JFrame;

/**
 *
 * @author Alumno
 */
public class Pow {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Canvas canvas= new Canvas();
        frame.getContentPane().add(canvas, BorderLayout.CENTER);
        frame.pack(); 
        
       
        frame.setVisible(true);
        frame.setResizable(false);
               
        frame.setTitle("Ping Pong");
        Thread t = new Thread((Runnable) canvas, "Animator");
        t.start();
        
    }
    
}
