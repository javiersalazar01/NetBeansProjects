package pong;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
//import pong.bars;
/**
 *
 * @author Alumno
 */
public class Canvas extends JComponent implements Runnable, KeyListener {

    public static final int MAX_X = 640;
    public static final int MAX_Y = 480;
    public final int MAX_SCORE=15;
    
    public int MAX_SPEED = 10;
     
    //Barra 1
    private int rect1_x = 35;
    private int rect1_y = 180;   
   
    //Barra 2
    private int rect2_x = 585;
    private int rect2_y = 180;
  
    public int xPos = 310;
    public int yPos = 230;

    private boolean gameOver = false;
    private boolean pause = false;
    private boolean comenzar = false;

    public int puntuajeJugador1 = 0;
    public int puntuajeJugador2 = 0;
    
    public String nombreGanador;

    public int sigPartida;

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(MAX_X, MAX_Y);
    }

    public Canvas() {
        this.addKeyListener(this);
        this.setFocusable(true);

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.fillRect(rect1_x, rect1_y, 20, 100);
        g.drawLine(320, 480, 320, 1);
        g.fillOval(xPos, yPos, 20, 20);
        g.fillRect(rect2_x, rect2_y, 20, 100);
        g.drawString("Ping Pong Dani", 280, 20);
        g.drawString("Puntos Jugador 1: " + puntuajeJugador1, 20, 20);
        g.drawString("Puntos Jugador 2: " + puntuajeJugador2, MAX_X - 150, 20);

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void run() {
        int dx = 10;//x
        int dy = 10;//y
        comenzar();
        while (true) {
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

                xPos += dx;
                yPos += dy;

                if (xPos <= rect1_x + 20 && (yPos >= rect1_y) && (yPos <= rect1_y + 100)) { //Para que choque con la barra 1
                    dx = dx * -1;

                }
                if (xPos >= rect2_x - 20 && (yPos >= rect2_y) && (yPos <= rect2_y + 100)) { //Para que choque con la barra 2                  
                    dx = dx * -1;

                }
                if (yPos >= MAX_Y - 20) {
                    dy = dy * -1;
                }
                if (yPos <= 1) {
                    dy = dy * -1;
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

                Thread.sleep(MAX_SPEED);

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
            } catch (InterruptedException e) {
                Logger.getLogger(Canvas.class.getName()).log(Level.SEVERE, null, e);
            }

        }
    }

    @Override
    public void keyPressed(KeyEvent ke) {

        int tecla = ke.getKeyCode();
        if (!gameOver && !pause) {
            if (tecla == KeyEvent.VK_UP && rect2_y > 0) {
                //System.out.println("teclaso");
                rect2_y -= 10;
                this.repaint();
            }
            if (tecla == KeyEvent.VK_DOWN && rect2_y < MAX_Y - 100) {
                //System.out.println("teclaso");
                rect2_y += 10;
                this.repaint();
            }
            if (tecla == KeyEvent.VK_W && rect1_y > 0) {
                //System.out.println("teclaso");
                rect1_y -= 10;
                this.repaint();
            }
            if (tecla == KeyEvent.VK_S && rect1_y < MAX_Y - 100) {
                //System.out.println("teclaso");
                rect1_y += 10;
                this.repaint();
            }
        }

        //para pause
        if (tecla == KeyEvent.VK_P && pause == false) {
            pause();
        } else if (pause == true && tecla == KeyEvent.VK_P) {
            resume();
        }
        //para comenzar
        if (tecla == KeyEvent.VK_SPACE) {
            comenzar = true;
            System.out.println(comenzar);
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {

    }

    public synchronized void resume() {
        pause = false;
        notify();
    }

    public synchronized void pause() {
        pause = true;
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
