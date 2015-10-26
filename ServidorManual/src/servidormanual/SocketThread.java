/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidormanual;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author Alumno
 */
public class SocketThread extends Thread {

    private Socket clienteSocket;
    
    private final String error404 = "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n"
            + "<head>\n"
            + "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\"/>\n"
            + "<title>404: archivo o directorio no encontrado.</title>\n"
            + "<style type=\"text/css\">\n"
            + "<!--\n"
            + "body{margin:0;font-size:.7em;font-family:Verdana, Arial, Helvetica, sans-serif;background:#EEEEEE;}\n"
            + "fieldset{padding:0 15px 10px 15px;} \n"
            + "h1{font-size:2.4em;margin:0;color:#FFF;}\n"
            + "h2{font-size:1.7em;margin:0;color:#CC0000;} \n"
            + "h3{font-size:1.2em;margin:10px 0 0 0;color:#000000;} \n"
            + "#header{width:96%;margin:0 0 0 0;padding:6px 2% 6px 2%;font-family:\"trebuchet MS\", Verdana, sans-serif;color:#FFF;\n"
            + "background-color:#555555;}\n"
            + "#content{margin:0 0 0 2%;position:relative;}\n"
            + ".content-container{background:#FFF;width:96%;margin-top:8px;padding:10px;position:relative;}\n"
            + "-->\n"
            + "</style>\n"
            + "</head>\n"
            + "<body>\n"
            + "<div id=\"header\"><h1>Error del servidor</h1></div>\n"
            + "<div id=\"content\">\n"
            + " <div class=\"content-container\"><fieldset>\n"
            + "  <h2>404: archivo o directorio no encontrado.</h2>\n"
            + "  <h3>Puede que se haya quitado el recurso que está buscando, que se le haya cambiado el nombre o que no esté disponible temporalmente.</h3>\n"
            + " </fieldset></div>\n"
            + "</div>\n"
            + "</body>\n"
            + "</html>";
    
    private final String error501 = "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n"
            + "<head>\n"
            + "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\"/>\n"
            + "<title>404: archivo o directorio no encontrado.</title>\n"
            + "<style type=\"text/css\">\n"
            + "<!--\n"
            + "body{margin:0;font-size:.7em;font-family:Verdana, Arial, Helvetica, sans-serif;background:#EEEEEE;}\n"
            + "fieldset{padding:0 15px 10px 15px;} \n"
            + "h1{font-size:2.4em;margin:0;color:#FFF;}\n"
            + "h2{font-size:1.7em;margin:0;color:#CC0000;} \n"
            + "h3{font-size:1.2em;margin:10px 0 0 0;color:#000000;} \n"
            + "#header{width:96%;margin:0 0 0 0;padding:6px 2% 6px 2%;font-family:\"trebuchet MS\", Verdana, sans-serif;color:#FFF;\n"
            + "background-color:#555555;}\n"
            + "#content{margin:0 0 0 2%;position:relative;}\n"
            + ".content-container{background:#FFF;width:96%;margin-top:8px;padding:10px;position:relative;}\n"
            + "-->\n"
            + "</style>\n"
            + "</head>\n"
            + "<body>\n"
            + "<div id=\"header\"><h1>Error del servidor</h1></div>\n"
            + "<div id=\"content\">\n"
            + " <div class=\"content-container\"><fieldset>\n"
            + "  <h2>501: Not Implemented</h2>\n"
            + "  <h3>El servidor no reconoce el metodo de peticion.</h3>\n"
            + " </fieldset></div>\n"
            + "</div>\n"
            + "</body>\n"
            + "</html>";
    
    private final String errorForbiden = "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n"
            + "<head>\n"
            + "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\"/>\n"
            + "<title>404: archivo o directorio no encontrado.</title>\n"
            + "<style type=\"text/css\">\n"
            + "<!--\n"
            + "body{margin:0;font-size:.7em;font-family:Verdana, Arial, Helvetica, sans-serif;background:#EEEEEE;}\n"
            + "fieldset{padding:0 15px 10px 15px;} \n"
            + "h1{font-size:2.4em;margin:0;color:#FFF;}\n"
            + "h2{font-size:1.7em;margin:0;color:#CC0000;} \n"
            + "h3{font-size:1.2em;margin:10px 0 0 0;color:#000000;} \n"
            + "#header{width:96%;margin:0 0 0 0;padding:6px 2% 6px 2%;font-family:\"trebuchet MS\", Verdana, sans-serif;color:#FFF;\n"
            + "background-color:#555555;}\n"
            + "#content{margin:0 0 0 2%;position:relative;}\n"
            + ".content-container{background:#FFF;width:96%;margin-top:8px;padding:10px;position:relative;}\n"
            + "-->\n"
            + "</style>\n"
            + "</head>\n"
            + "<body>\n"
            + "<div id=\"header\"><h1>Error Fordbidden</h1></div>\n"
            + "<div id=\"content\">\n"
            + " <div class=\"content-container\"><fieldset>\n"
            + "  <h2>Error Fordbidden</h2>\n"
            + "  <h3>No es posible accesar a este directorio.</h3>\n"
            + " </fieldset></div>\n"
            + "</div>\n"
            + "</body>\n"
            + "</html>";

    public SocketThread(Socket clienteSocket) {
        this.clienteSocket = clienteSocket;
        System.out.println("Conectado");
        start();
    }

    @Override
    public void run() {

        PrintWriter out = null;
        BufferedReader in = null;
        try {

            out = new PrintWriter(clienteSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));

            String primera = in.readLine();
            String arr[] = primera.split(" ");
            String method = arr[0];
            String urlRelativo = arr[1];

            if (!urlRelativo.equalsIgnoreCase("/favicon.ico")) {

                System.out.println("------------------ Informacion Del Explorador -------------------");
                String inputLine;

                System.out.println(primera);
                while ((inputLine = in.readLine()) != null) {
                    if (inputLine.length() == 0) {
                        break;
                    }
                    System.out.println(inputLine);
                }

                if (urlRelativo.equalsIgnoreCase("/")) {
                    urlRelativo = "/index.html";
                }

                File f = new File("C:/Users/CSI-PRO-PC/Desktop/cositas jijiji/raiz" + urlRelativo);
                //File f = new File("C:/JAVIER/cositasjijiij/raiz/" + urlRelativo);

                
                
                
                if (method.equalsIgnoreCase("post")) {
                    out.print(error501);
                } else if (f.isDirectory() && !urlRelativo.endsWith("/")){
                    out.print(errorForbiden);
                } else if (urlRelativo.endsWith("/")){
                    
                    f = new File("C:/Users/CSI-PRO-PC/Desktop/cositas jijiji/raiz" + urlRelativo + "index.html");
                    if (f.exists()) {
                        System.out.println("Direccion de archivo :" + f.toString());
                        try (BufferedReader reader = new BufferedReader(new FileReader(f))) {
                            String line = null;
                            System.out.println("------------------ Pagina Enviada --------------------");
                            while ((line = reader.readLine()) != null) {
                                out.println(line);
                                System.out.println(line);
                            }
                        } catch (IOException x) {
                            System.err.format("IOException: %s%n", x);
                        }
                    } else {
                        out.print(error404);
                    }
                    
                } else if (f.exists()) {
                    System.out.println("Direccion de archivo :" + f.toString());
                    try (BufferedReader reader = new BufferedReader(new FileReader(f))) {
                        String line = null;
                        System.out.println("------------------ Pagina Enviada --------------------");
                        while ((line = reader.readLine()) != null) {
                            out.println(line);
                            System.out.println(line);
                        }
                    } catch (IOException x) {
                        System.err.format("IOException: %s%n", x);
                    }
                   
                } else {
                     out.print(error404);
                }

            }

            out.close();
            in.close();
            clienteSocket.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
