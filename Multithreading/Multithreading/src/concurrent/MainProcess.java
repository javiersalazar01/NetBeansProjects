/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package concurrent;

/**
 *
 * @author rnavarro
 */
public class MainProcess {
    
    public static void main(String args[]) {
        String mainThreadName = Thread.currentThread().getName();
        
        System.out.println("main() is running on: " + mainThreadName);
        doSomething();
        
        mainThreadName = Thread.currentThread().getName();        
        System.out.println("main() is running on: " + mainThreadName);
        
    }
    
    
    public static void doSomething() {
        String threadName = Thread.currentThread().getName();
        System.out.println("doSomething() is running on: " + threadName);   
        doSomethingElse();
    }
    
    public static void doSomethingElse() {
        String threadName = Thread.currentThread().getName();
        System.out.println("doSomethingElse() is running on: " + threadName);       
      
    }     
    
}
