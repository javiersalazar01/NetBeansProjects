/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sockets2;

import static com.sun.corba.se.impl.util.Utility.printStackTrace;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author Alumno
 */

public class ClienteChilo {
    
    private String serverHostname;
    private Socket clienteSocket = null;
    private PrintWriter out = null;
    private BufferedReader in = null;
    private String answer = "";

    public ClienteChilo(String url) {
        serverHostname = url;
        try {
            
            clienteSocket = new Socket(serverHostname, 80);
            
            out = new PrintWriter(clienteSocket.getOutputStream(), true);
            
            in = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));
            
            String line = "";
            out.println("GET / HTTP/1.1");      
            out.println("Host: " + serverHostname);
            out.println("");
            
            while((line = in.readLine()) != null){
                System.out.println(line);
            }
            
        } catch (UnknownHostException e) {
           e.printStackTrace();
        } catch (IOException e) {
           e.printStackTrace();
        }
    }

    
    public String getServerHostname() {
        return serverHostname;
    }

   
    public void closeAllConections(){
        try {
            out.close();
            in.close();
            clienteSocket.close();
        } catch (Exception e) {
            printStackTrace();
        }
       
    }
        
        
        
        

        
}
