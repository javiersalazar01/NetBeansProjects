/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pong;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import org.apache.log4j.*;

/**
 *
 * @author Francisco Javier Salazar Espinoza
 */
public class Canvas extends JComponent implements Runnable{
        
    private static final int MAX_X = 640;    
    private static final int MAX_Y = 480;
    public final int MAX_SCORE=3;
    private final org.apache.log4j.Logger log;
    
    
    private int xPos = 500;
    private int yPos = 200;

    private int rectWidth = 20;
    private int rectLength = 100;
    private int xPosRect = 50;
    private int yPosRect = 20;
    
    private boolean gameOver = false;
    private boolean pause = false;
    private boolean comenzar = false;
    
    public int puntuajeJugador1 = 0;
    public int puntuajeJugador2 = 0;
    
    public String nombreGanador;

    public int sigPartida;
    
    private int rectWidth2 = 20;
    private int rectLength2 = 100;
    private int xPosRect2 = 550;
    private int yPosRect2 = 20;

   
    
    
    public Canvas() {
        log = org.apache.log4j.Logger.getLogger(Canvas.class);
       
        this.setFocusable(true);

        this.addKeyListener(new KeyListener() {
            
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                
                int code = e.getKeyCode();
                
                if (code == KeyEvent.VK_W) {
                    yPosRect += -10;
                }


                if (code == KeyEvent.VK_S) {
                    yPosRect += 10;
                }
                
                
                if (code == KeyEvent.VK_UP) {
                    yPosRect2 += -10;
                }


                if (code == KeyEvent.VK_DOWN) {
                    yPosRect2 += 10;
                }
                //para pause
                if (code == KeyEvent.VK_P && pause == false) {
                    pause();
                } else if (pause == true && code == KeyEvent.VK_P) {
                    resume();
                }
                //para comenzar
                if (code == KeyEvent.VK_SPACE) {
                    comenzar = true;
                    System.out.println(comenzar);
                }

            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

    }


    @Override
    public Dimension getPreferredSize() {
        return new Dimension(MAX_X, MAX_Y);
    }



    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        g.fillOval(xPos, yPos, 20, 20);
        g.drawLine(320, 480, 320, 1);
        g.fillRect(xPosRect, yPosRect, rectWidth, rectLength);        
        g.fillRect(xPosRect2, yPosRect2, rectWidth2, rectLength2);
        
        g.drawLine(320, 480, 320, 1);
        g.drawString("Pong", 280, 20);
        g.drawString("Puntos Jugador 1: " + puntuajeJugador1, 20, 20);
        g.drawString("Puntos Jugador 2: " + puntuajeJugador2, MAX_X - 150, 20);

    }



    @Override
    public void run() {
        int dX = 2;       
        int dY = 2;

        while(true){
            if (gameOver) {
                continue;
            }
            try {
                
                synchronized (this) {
                    if (pause) {
                        System.out.println("pause");
                        wait();
                        System.out.println("Despausado");
                    }
                }
                
                xPos += dX;   
                yPos += dY;

                
                if (xPos - 15 <= (xPosRect) && (yPos >= yPosRect) && (yPos <= yPosRect + rectLength) && (xPos >= xPosRect - rectWidth) ) {
                    dX = dX * -1; 
                }
                
                if (xPos + 15 >= (xPosRect2) && (yPos >= yPosRect2) && (yPos <= yPosRect2 + rectLength2) && (xPos <= xPosRect2 + rectWidth2 - 19)) {
                    dX = dX * -1; 
                }
                
                if (yPos >= MAX_Y - 20) {
                    dY = dY * -1;
                }
                if (yPos <= 1) {
                    dY = dY * -1;
                }
                
                if (xPos >= MAX_X - 20) { // Sumar puntos para jugador 1
                    puntuajeJugador1 += 1;
                    xPos = 310;
                    yPos = 230;

                }
                
                if (xPos <= 1) {   // Sumar puntos para jugador 2
                    puntuajeJugador2 += 1;
                    xPos = 310;
                    yPos = 230;
                }
                this.repaint();
                Thread.sleep(10);
                
                if (puntuajeJugador1 == MAX_SCORE || puntuajeJugador2 == MAX_SCORE) {
                   // gameOver = true;
                    if (puntuajeJugador1 == MAX_SCORE) {
                        nombreGanador = "Jugador 1";

                    } else {
                        nombreGanador = "Jugador 2";
                    }
                    sigPartida = JOptionPane.showConfirmDialog(this, "El ganador es: " + nombreGanador 
                            + "\n ¿Desea seguir jugando?");
                     if (sigPartida==0) {
                        restart();
                    }else{
                         gameOver=true;
                     }
                    System.out.println("Ganador " + nombreGanador);
                }
            } catch (Exception e) {
            }
        }
    }

    
     public synchronized void resume() {
        pause = false;
        notify();
    }
 
    public synchronized void pause() {
        pause = true;
        log.info("pausa");
    }

    public void restart() {
        xPos = 310;
        yPos = 230;
        
        puntuajeJugador1=0;
        puntuajeJugador2=0;
    }

    public void comenzar(){
        JOptionPane.showMessageDialog(this,"En cuanto esten listos den aceptar" );
    }
    
    
    
}
