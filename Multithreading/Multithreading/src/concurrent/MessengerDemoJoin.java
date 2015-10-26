/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package concurrent;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rnavarro
 */
public class MessengerDemoJoin {
    
    public static void main(String args[]) {
        String mainThreadName = Thread.currentThread().getName();
        System.out.println("main() is running on: " + mainThreadName);
        
        Messenger m1 = new Messenger();
        Messenger m2 = new Messenger();
        Thread t1 = new Thread( m1, "T1");
        t1.start();
        Thread t2 = new Thread( m2, "T2");
        //t2.start(); 
        
        System.out.println("Joining...");
        try {            
            t1.join();
            t2.start(); 
            System.out.println("Keep calm and carry on.\n");
        } catch (InterruptedException ex) {
            Logger.getLogger(MessengerDemoJoin.class.getName()).log(Level.SEVERE, null, ex);
        }
       System.out.println("Leaving main...");       
    }
    
}
