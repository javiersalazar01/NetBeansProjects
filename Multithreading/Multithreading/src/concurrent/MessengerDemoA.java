/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package concurrent;

/**
 *
 * @author rnavarro
 */
public class MessengerDemoA {
    
    public static void main(String args[]) throws InterruptedException {
        String mainThreadName = Thread.currentThread().getName();
        System.out.println("main() is running on: " + mainThreadName);
        
        Messenger m1 = new Messenger();
        Thread t1 = new Thread( m1, "T1");
        t1.start();
        
        System.out.println("Keep calm and carry on.");
        Thread t2 = new Thread( m1, "T2");
        t2.join();
        t2.start();    
        
              
    }
    
}
