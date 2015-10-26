/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webcontent;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit;

/**
 *
 * @author rnavarro
 */
public class LinkProcessor extends HTMLEditorKit.ParserCallback {

    private URL root;

    public LinkProcessor() {
        super();
    }

    public LinkProcessor(URL root) {
        super();
        this.root = root;
    }

    @Override
    public void handleText(char[] text, int position) {
        //System.out.println(text);
    }

    @Override
    public void handleSimpleTag(HTML.Tag tag, MutableAttributeSet attributes, int pos) {
        if(tag == HTML.Tag.IMG) {
            Enumeration attribs = attributes.getAttributeNames();
            while (attribs.hasMoreElements()) {
                Object attName = attribs.nextElement();
                String attValue = attributes.getAttribute(attName).toString();
                String url;
                if (attName.toString().equals("src")) {
                    try {
                        url = "http://www.unison.edu.mx/" + attValue;
                        System.out.println(url);
                        URL checker = new URL(url);
                    } catch (MalformedURLException ex) {
                        //System.out.println("\tRelativo: " + attValue);
                        System.out.println("error");
                    }
                }
            }
        }
        
    }
    
    
   /* @Override
    public void handleStartTag(HTML.Tag tag, MutableAttributeSet attributes, 
            int position) {
       
        if(tag == HTML.Tag.IMG) {
            
            //System.out.println(tag);
            Enumeration attribs = attributes.getAttributeNames();
            System.out.println(tag.toString());
            while (attribs.hasMoreElements()) {
                Object attName = attribs.nextElement();
                String attValue = attributes.getAttribute(attName).toString();
                
                if (attName.toString().equals("src")) {
                    try {
                        URL checker = new URL(attValue);
                        //System.out.print(attName + "=");
                        System.out.println(attValue);
                    } catch (MalformedURLException ex) {
                        //System.out.println("\tRelativo: " + attValue);
                        System.out.println("\t"+root.toExternalForm()+"/"+attValue);
                    }
                }
            }
        } 
    }*/
        
        
        
        
}


