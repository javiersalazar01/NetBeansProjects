/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscaimagenes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Maestros
 */
public class HttpSimpleClient {

    public static final int HTTP_PORT = 80;
    public static final String GET = "GET ";
    public static final String PROTOCOL = " HTTP/1.1";

    public static void main(String[] args) {
        URL url = null;
        
        try {
            url = new URL("http://dii.isi.uson.mx/");
        } catch (MalformedURLException ex) {
            Logger.getLogger(HttpSimpleClient.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(0);
        }

        String host = url.getHost();
        String recurso = url.getPath();
        
        System.out.println( getFileName(url));
        
        InetAddress server = null;
        try {
            server = InetAddress.getByName(host);
        } catch (UnknownHostException ex) {
            Logger.getLogger(HttpSimpleClient.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(0);
        }

        Socket conexion = null;

        try {
            conexion = new Socket(server, HTTP_PORT);
        } catch (IOException ex) {
            Logger.getLogger(HttpSimpleClient.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(0);
        }

        InputStream i = null;
        OutputStream o = null;

        try {
            i = conexion.getInputStream();
            o = conexion.getOutputStream();
        } catch (IOException ex) {
            Logger.getLogger(HttpSimpleClient.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(0);          
        }
        
        BufferedReader in = new BufferedReader(new InputStreamReader(i));
        PrintWriter outGoing = new PrintWriter( o );
             
        // enviar solicitud al servidor web
        outGoing.println(GET + recurso + PROTOCOL);
        outGoing.println("Host: " + host);
        outGoing.println("User-Agent: Mozilla/5.0");
        outGoing.println();
        outGoing.flush();
        //procesar respuesta del servidor
        String response = null;
        String content = null;
        
        try {
            while( (response=in.readLine()) != null ) {               
                if( response.startsWith("Content-Type")  ) {                    
                    content = response.substring(response.lastIndexOf(" ")+1);
                   // break;
                }
                System.out.println(response);
            }
            System.out.println(content);
            if( content.startsWith("image") ) {
                processBinary(i);
            } else {
                processText(i);
            }
            
        } catch (IOException ex) {
            Logger.getLogger(HttpSimpleClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            conexion.close();
        } catch (IOException ex) {
            Logger.getLogger(HttpSimpleClient.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private static void processBinary(InputStream i) {
       
    }
    
    private static String getFileName(URL u) {
        String s = u.getPath();
        String f = s.substring(s.lastIndexOf('/' ) + 1);
        return f;
    }

    private static void processText(InputStream i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}