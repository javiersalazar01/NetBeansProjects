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
    
    /*
       Clase cliente, ejecútese para crear un cliente que se conecta al servidor por medio de localhost.
       Es necesario primero ejecutar el servidor forzosamente.
    */
    
    
     public static void main(String[] args) throws IOException {

        String serverHostname = "127.0.0.1";

        Socket clienteSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;

        try {
            
            clienteSocket = new Socket(serverHostname, 7777);

            out = new PrintWriter(clienteSocket.getOutputStream(), true);

            in = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));
            
        } catch (UnknownHostException e) {
           e.printStackTrace();
        } catch (IOException e) {
           e.printStackTrace();
        }

	Scanner sca = new Scanner(System.in);
	String entrada = null;
        
         System.out.print("Entrada: ");
	while ((entrada = sca.nextLine()) != null) {
            if (entrada.equals(".")) {
                break;
            }
	    out.println(entrada);
            System.out.println("Echo: " + in.readLine());
            System.out.print("Entrada: ");
	}
        
	out.close();
	in.close();
	sca.close();
	clienteSocket.close();
    }
}
