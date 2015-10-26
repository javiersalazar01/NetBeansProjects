/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package webcontent;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.html.HTMLEditorKit;

/**
 *
 * @author rnavarro
 */
public class WebCrawler {

    public static void main(String[] args) {
        
        HTMLParser par = new HTMLParser();

        HTMLEditorKit.Parser parser = par.getParser();
        URL url = null;
        String encoding = "iso-8859-1";
        try {
            // Crear URL y obtener conexion
            url = new URL("https://www.youtube.com/");
            
        } catch (MalformedURLException ex) {
            Logger.getLogger(WebCrawler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(WebCrawler.class.getName()).log(Level.SEVERE, null, ex);
        }
        InputStream in;
        InputStreamReader reader = null;       
              
        Stack <URL> pila = new Stack<URL>();
        pila.push(url);
        // Obtener manejador de etiquetas
        //HTMLEditorKit.ParserCallback callback = new TextOnlyProcessor();
        //HTMLEditorKit.ParserCallback callback = new LinkFolower(url,);
        
        URL currentURL = null;
        int count = 0;
        try {
            while( !pila.empty() )  {
                currentURL = pila.pop();
                in = currentURL.openStream();
                reader = new InputStreamReader(in, encoding);
                HTMLEditorKit.ParserCallback callback = new LinkFolower(url,pila, count);
                parser.parse(reader, callback, true);
            }
           
        } catch (IOException ex) {
            Logger.getLogger(WebCrawler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
