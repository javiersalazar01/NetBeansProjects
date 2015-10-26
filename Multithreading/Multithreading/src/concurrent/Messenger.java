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
public class Messenger implements Runnable {

    private String importantInfo[] = {
        "I will not waste chalk",
        "I will not skateboard in the halls",
        "I will not instigate revolution",
        "I will not draw naked ladies in class",
        "I did not see Elvis",
        "I will not call my teacher \"Hot Cakes\"",
        "Garlic gum is not funny",
        "They are laughing at me, not with me",
        "I will not yell \"Fire!\" in a crowded classroom"
    };
    
    private int i = 0;

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        
        
        while(i < importantInfo.length) {
            System.out.println(i);
            try {
                //Pause for 2 seconds
                Thread.sleep(200);
                //Print a message               
                System.out.println("Message from " + threadName + " ");
                System.out.println(importantInfo[i]);
            } catch (InterruptedException ex) {
                System.out.println(  ex.getMessage() );
                break;
            }
             i++;
        }
    }
}
