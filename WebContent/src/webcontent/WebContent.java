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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.html.HTMLEditorKit;

/**
 *
 * @author rnavarro
 */
public class WebContent {

    public static void main(String[] args) {

        HTMLParser par = new HTMLParser();

        HTMLEditorKit.Parser parser = par.getParser();
        URL url = null;
        String encoding = "iso-8859-1";
        try {
            // Crear URL y obtener conexion
            url = new URL(args[0]);
            URLConnection uc = url.openConnection();
            //encoding = uc.getContentType();
            //System.out.println(uc);
        } catch (MalformedURLException ex) {
            System.out.println("Error en el URL. " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Error de E/S. " + ex.getMessage());
        }
        InputStream in;
        InputStreamReader reader = null;

        // Obtener flujo de entrada
        try {
            if (url != null) {
                in = url.openStream();
                reader = new InputStreamReader(in, encoding);
            }
        } catch (IOException ex) {
            Logger.getLogger(WebContent.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Obtener manejador de etiquetas
        //HTMLEditorKit.ParserCallback callback = new TextOnlyProcessor();
       // HTMLEditorKit.ParserCallback callback = new LinkProcessor(url);
         HTMLEditorKit.ParserCallback callback = new ImageTagHandler(url);
        try {
            //procesar el documento
            parser.parse(reader, callback, true);
        } catch (IOException ex) {
            Logger.getLogger(WebContent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
