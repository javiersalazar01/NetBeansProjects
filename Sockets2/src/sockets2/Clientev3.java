/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sockets2;

import java.io.BufferedReader;
import java.io.*;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 *
 * @author Alumno
 */
public class Clientev3 {
    

     public static void main(String[] args) throws IOException {
        
        Socket clienteSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;
         
         
        BufferedReader sca = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("URL: ");
        String resp = sca.readLine();
        String llegada = "";
        
        while (!resp.equalsIgnoreCase(".")) {             
            
            String serverHostname = resp;

            try {

                clienteSocket = new Socket(serverHostname, 80);

                out = new PrintWriter(clienteSocket.getOutputStream(), true);

                in = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));

            } catch (UnknownHostException e) {
               e.printStackTrace();
            } catch (IOException e) {
               e.printStackTrace();
            }

            out.println("GET / HTTP/1.1");      
            out.println("Host: " + serverHostname);
            out.println("");
            
            while((llegada = in.readLine()) != null){
                    System.out.println(llegada);
            }
            
            out.close();
            in.close();
            sca.close();
            clienteSocket.close();
            
            System.out.print("URL: ");
            resp = sca.readLine();
        }
            
	//www.unison.edu.mx
        
       
    
    
    }
     
}
