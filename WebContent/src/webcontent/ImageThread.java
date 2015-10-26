/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webcontent;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 *
 * @author Alumno
 */
public class ImageThread implements Runnable{
    
    private String url;
    private ArrayList<URL> lista;

    public ImageThread(String url) {
        this.url = url;
        this.lista = new ArrayList<URL>();
    }
    
    
    public void setUrl(String newUrl){
        this.url = newUrl;
    }
    
     public ArrayList<URL> getLista() {
        return lista;
    }
    
    @Override
    public void run() {
        try {
            URL checker = new URL(url);
            //System.out.println(checker.getFile());
            lista.add(checker);
        } catch (MalformedURLException ex) {
            System.out.println("No Descargada");
        }
    }
    
}
