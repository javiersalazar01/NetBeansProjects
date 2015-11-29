/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webcontent;

import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Alumno
 * 
 * clase que obtiene la imagen a mostrar y la muestra en un JinternalFrame.
 * 
 * 
 */
public class ImageThread implements Runnable{

    
    
    
    private ArrayList<Image> arr;
    private JDesktopPane dp;
    private int posicion;

    public ImageThread(ArrayList<Image> arr, JDesktopPane dp, int posicion) {
        this.arr = arr;
        this.dp = dp;
        this.posicion = posicion;
    }
    
    
     
    //Despliega la imagen específica en la posición del arreglo de imágenes específica pasada en los parámetros.
   @Override
    public void run() {
            JInternalFrame internal = new JInternalFrame("Un Internal Frame");
            JPanel p = new JPanel();
            JLabel lb = new JLabel();
            ImageIcon imgn = new ImageIcon(arr.get(posicion));
            internal.setSize(new Dimension(imgn.getIconWidth(),imgn.getIconHeight()));
            lb.setIcon(imgn);
            p.add(lb);
            lb.setVisible(true);
            lb.setBounds(10, 10, 400, 400);
            internal.add(p);
            internal.pack();
            internal.setResizable(true);
            internal.setClosable(true);
            dp.add(internal);
            internal.setVisible(true);
            int randomX = (int)(Math.random() * dp.getSize().width);
            int randomY = (int)(Math.random() * dp.getSize().height);
            internal.setLocation(randomX, randomY);
    }
    
}
