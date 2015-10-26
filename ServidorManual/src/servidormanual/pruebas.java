/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidormanual;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Alumno
 */
public class pruebas {
    public static void main(String [] args){
        File f = new File("C:/Users/Alumno/Desktop/pagina.html");
            System.out.println(f.toString());
            try (BufferedReader reader = new BufferedReader(new FileReader(f))) {
                String line = null;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException x) {
                System.err.format("IOException: %s%n", x);
            }
    
    
    }
}
