/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidormanual;

import java.io.IOException;
import java.net.ServerSocket;

/**
 *
 * @author Alumno
 */
public class ServidorManual {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try { 
                serverSocket = new ServerSocket(8080); 
                while (true) {
                    System.out.println("Esperando Conexion...");
                    SocketThread t = new SocketThread(serverSocket.accept());
                }
                 
            }
        catch (IOException e) 
            { 
            e.printStackTrace();
            System.out.println("Error" );

            }
    }
    
}
