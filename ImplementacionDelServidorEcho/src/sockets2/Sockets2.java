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
     * Clase Socket ejecutese primero para levantar el server, despues ejecute el cliente.
     * 
     */
    public static void main(String[] args) throws IOException {
       
        ServerSocket serverSocket = null;
        try { 
            serverSocket = new ServerSocket(7777); 
            for (int i = 0; i < 3; i++) {
                
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
