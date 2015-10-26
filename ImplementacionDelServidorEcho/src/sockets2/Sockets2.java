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

/**
 *
 * @author Alumno
 */
public class Sockets2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
       /* ServerSocket serverSocket = null;
        Socket clientSocket = null; 

        try { 
             serverSocket = new ServerSocket(7777); 
             System.out.println("Esperando Conexion");
             clientSocket = serverSocket.accept(); 
            } 
        catch (IOException e) 
            { 
             System.err.println("Error"); 

            } 
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),true); 
        BufferedReader in = new BufferedReader(new InputStreamReader( clientSocket.getInputStream())); 

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
        clientSocket.close(); 
        serverSocket.close(); */
        //System.out.print("GET / HTTP/1.1\nHost: www.unison.edu.mx\n");
        ServerSocket serverSocket = null;
        try { 
            serverSocket = new ServerSocket(7777); 
            for (int i = 0; i < 3; i++) {
                System.out.println("Esperando Conexion...");
                SocketThread t = new SocketThread(serverSocket.accept());
                System.out.println("Conectado.");
            }
            
            }
        catch (IOException e) 
            { 
            e.printStackTrace();
            System.out.println("Error" );

            } 
        
    }
    
}
