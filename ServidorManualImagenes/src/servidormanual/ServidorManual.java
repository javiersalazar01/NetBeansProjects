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
     * 
     * Metodo main para ejecucion de la aplicacion.
     * 
     * para mandar a llamar el error404, introdusca una pagina que no existe ejemplo. http://localhost:8080/contacto/noexisto.html
     * para mandar a llamar el error 501 utilize algun programa para realizar peticiones post a cualquier direccion de la pagina ejemplo. post - http://localhost:8080/contacto/index.html.
     * para mandar a llamar el mensaje de Fordbidden escriba una direccion que no termine en / y sin formato. ejemplo. http://localhost:8080/contacto.
     * para mostrar imagenes simplemente coloque la img en el url. ejemplo. http://localhost:8080/imagen.jpg.
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
