/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webcontent;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit;

/**
 *
 * @author Alumno
 */
public class ImageTagHandler extends HTMLEditorKit.ParserCallback {

    private URL root;
    ArrayList<Image> arr;

    public ImageTagHandler() {
        super();
         arr = new ArrayList<Image>();
    }

    public ImageTagHandler(URL root) {
        super();
        this.root = root;
        arr = new ArrayList<Image>();
       
    }

    public ArrayList<Image> getArr() {
        return arr;
    }

    @Override
    public void handleSimpleTag(HTML.Tag tag, MutableAttributeSet attributes, int position) {
        if (tag == HTML.Tag.IMG) {
            //System.out.println(tag);
            Enumeration attribs = attributes.getAttributeNames();
            while (attribs.hasMoreElements()) {
                Object attName = attribs.nextElement();
                final String attValue = attributes.getAttribute(attName).toString();

                if (attName.toString().equals("src")) {
                    //System.out.println("\tRelativo: " + attValue);
                    URL url;
                    Image image;
                    try {
                        url = new URL(root.toString() + "/" + attValue);
                        image = ImageIO.read(url);
                        this.arr.add(image);
                    } catch (MalformedURLException ex) {
                        Logger.getLogger(ImageTagHandler.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(ImageTagHandler.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    System.out.println(root.toString() + "/"+ attValue);
                }
            }
        }

    }
}
