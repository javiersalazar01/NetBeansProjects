/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sockets2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alumno
 */
public class SocketThread extends Thread {

    
    private Socket clienteSocket;

    public SocketThread(Socket clienteSocket) {
        this.clienteSocket = clienteSocket;
        System.out.println("Iniciado conexion.");
        start();
    }
    
    
    @Override
    public void run() {
       

       
       
        PrintWriter out = null;
        BufferedReader in = null;
        try {
            
           
            
            out = new PrintWriter(clienteSocket.getOutputStream(),true); 
            in = new BufferedReader(new InputStreamReader( clienteSocket.getInputStream())); 

            String inputLine; 

            while ((inputLine = in.readLine()) != null){ 
                if (inputLine.equals(".")){
                    break;
                }
                System.out.println ("Entrada: " + inputLine); 
                out.println(inputLine);
                System.out.println("Enviado: " + inputLine);

                } 

            out.close(); 
            in.close(); 
            clienteSocket.close();
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        

        
    }
    
}
