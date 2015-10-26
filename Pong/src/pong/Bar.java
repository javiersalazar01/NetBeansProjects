/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pong;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JComponent;

/**
 *
 * @author Alumno
 */
public class Bar extends JComponent implements Runnable{
    
    private int height;
    private int width;
    private int xPos;    
    private int yPos;

    public Bar(int height, int wight, int xPos, int yPos) {
        this.height = height;
        this.width = wight;
        this.xPos = xPos;
        this.yPos = yPos;
    }


   
   
    
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(height, width);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        g.fillRect(xPos, yPos, width, height);
    }
    
    
    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
