/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webcontent;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit;

/**
 *
 * @author rnavarro
 */
public class LinkFolower extends HTMLEditorKit.ParserCallback {

    private URL root;
    private Stack<URL> pila;
    private int count;

    public LinkFolower() {
        super();
    }

    public LinkFolower(URL root,Stack<URL> pila, int count) {
        super();
        this.root = root;
        this.pila = pila;
        this.count = count;
    }

    public void handleText(char[] text, int position) {
        //System.out.println(text);
    }

    public void handleStartTag(HTML.Tag tag, MutableAttributeSet attributes, 
            int position) {
       
        if(tag == HTML.Tag.A) {
            //System.out.println(tag);
            Enumeration attribs = attributes.getAttributeNames();
            while (attribs.hasMoreElements()) {
                Object attName = attribs.nextElement();
                String attValue = attributes.getAttribute(attName).toString();
                boolean isRelative = false;
                if (attName.toString().equals("href")) {
                    try {
                        URL checker = new URL(attValue);
                        //System.out.print(attName + "=");
                        System.out.println(attValue);
                    } catch (MalformedURLException ex) {
                        
                        isRelative = true;
                    }
                    if( isRelative ) {
                        try {
                            URL u = new URL(root.toExternalForm()+"/"+attValue);
                            
                            if( attValue.endsWith(".jpg") || attValue.endsWith(".png") ) {
                                if( !pila.contains( u ) )
                                    pila.push(u);
                                for(int i = 0;i<=count;i++)
                                       System.out.print("\t");
                                System.out.println(root.toExternalForm()+"/"+attValue);
                            }
                        } catch (MalformedURLException ex) {
                            Logger.getLogger(LinkFolower.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        }
    }

}
