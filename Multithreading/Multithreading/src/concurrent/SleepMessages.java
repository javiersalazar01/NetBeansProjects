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
public class SleepMessages {

    public static void main(String args[]) {
        String mainThreadName = Thread.currentThread().getName();
        System.out.println("main() is running on: " + mainThreadName);
        
        try {
            messenger();
        } catch (InterruptedException ex) {
            Logger.getLogger(SleepMessages.class.getName()).log(Level.SEVERE, null, ex);
        }
        mainThreadName = Thread.currentThread().getName();
        System.out.println("main() is running on: " + mainThreadName);
    }

    public static void messenger() throws InterruptedException {
        
        String threadName = Thread.currentThread().getName();
        System.out.println("messenger() is running on: " + threadName);
        
        String importantInfo[] = {
            "Mares eat oats",
            "Does eat oats",
            "Little lambs eat ivy",
            "A kid will eat ivy too"
        };

        for (int i = 0; i < importantInfo.length; i++) {
            //Pause for 1 second
            Thread.sleep(1000);
            //Print a message
            System.out.println(importantInfo[i]);
        }

    }
}
