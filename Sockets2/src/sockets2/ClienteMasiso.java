/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sockets2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.*;
import java.net.*;
import java.util.Scanner;

/**
 *
 * @author Alumno
 */
public class ClienteMasiso {
    
     public static void main(String[] args) throws IOException {

       /* String serverHostname = "www.unison.edu.mx";

        Socket clienteSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;

        try {
            
            clienteSocket = new Socket(serverHostname, 80);

            out = new PrintWriter(clienteSocket.getOutputStream(), true);

            in = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));
            
        } catch (UnknownHostException e) {
           e.printStackTrace();
        } catch (IOException e) {
           e.printStackTrace();
        }

	Scanner sca = new Scanner(System.in);
	String entrada;
        String llegada = "";
        System.out.print ("Entrada: ");
        String resp = sca.nextLine();
        
         
        out.println("GET / HTTP/1.1");      
        out.println("Host: " + sca.nextLine());
        out.println("");
        
        while((llegada = in.readLine()) != null){
                System.out.println(llegada);
        }
        
	/*while ((entrada = sca.nextLine()) != null) {
            System.out.println(entrada);
	    out.println(entrada);
	}
        
        //out.println("");
        while((llegada = in.readLine()) != null){
            System.out.println(llegada);
        }

	out.close();
	in.close();
	sca.close();
	clienteSocket.close();*/
        
         
         BufferedReader sca = new BufferedReader(new InputStreamReader(System.in));
         
         System.out.print("URL: ");
         String resp = sca.readLine();
         while (!resp.equalsIgnoreCase(".")) {             
             
             ClienteChilo c = new ClienteChilo(resp);
             c.closeAllConections();
             c = null;
             
             System.out.println("URL: ");
             resp = sca.readLine();
         }
         
    }
}
