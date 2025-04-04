package main;


import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class TileMap extends JPanel{
    private int TileTamanho = 45;
    private BufferedImage imagenMuro;
    private BufferedImage imagenPersonaje;
    private BufferedImage imagenCaja;
    private BufferedImage imagenCajaFinal;
    private BufferedImage imagenBoton;
    private Tablero tablero;
    
    public TileMap(){
        tablero = new Tablero();
        leerTeclado();
        //Reaccion de las teclas de forma automatica.
        setFocusable(true);
        requestFocusInWindow();
        
        try {
            imagenMuro = ImageIO.read(new File ("sprites/muro.png"));
            imagenPersonaje = ImageIO.read(new File ("sprites/personaje.png"));
            imagenCaja = ImageIO.read(new File ("sprites/caja.png"));
            imagenCajaFinal = ImageIO.read(new File("sprites/cajaLugar.png"));
            imagenBoton = ImageIO.read(new File("sprites/boton.png"));
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        
        char[][] tableroGrafico = tablero.getTablero();
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 8; j++){
                char parte = tableroGrafico[i][j];
                if(parte == 'M'){
                    paintMuro(g,i,j);
                }
                else if(parte == 'P'){
                    paintPersonaje(g,i,j);
                }
                else if(parte == 'C'){
                    paintCaja(g,i,j);
                }
                else if(parte == 'F'){
                    paintCajaFinal(g,i,j);
                }
                else if(parte == '.'){
                    paintBoton(g,i,j);
                }
            }
        }
    }
    
    private void paintMuro(Graphics g, int fila, int columna){
        g.drawImage(imagenMuro, columna*TileTamanho, fila*TileTamanho, TileTamanho, TileTamanho,this);
    }   
    private void paintPersonaje(Graphics g, int fila, int columna){
        g.drawImage(imagenPersonaje, columna*TileTamanho, fila*TileTamanho, TileTamanho, TileTamanho, this);
    }    
    private void paintCaja(Graphics g, int fila, int columna){
        g.drawImage(imagenCaja, columna*TileTamanho, fila*TileTamanho, TileTamanho, TileTamanho, this);
    }
    private void paintCajaFinal(Graphics g, int fila, int columna){
        g.drawImage(imagenCajaFinal, columna*TileTamanho, fila*TileTamanho, TileTamanho, TileTamanho, this);
    }
    private void paintBoton(Graphics g, int fila, int columna){
        g.drawImage(imagenBoton, columna*TileTamanho, fila*TileTamanho, TileTamanho, TileTamanho, this);
    }
    
    private void leerTeclado(){
        addKeyListener(new KeyListener (){
        @Override
        public void keyTyped(KeyEvent e) {
        
        }

        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
        
            if(keyCode == 38){ //Arriba
                tablero.moverJugador(-1, 0);
            }
            else if(keyCode == 40){ //Abajo
                tablero.moverJugador(1, 0);
            }
            else if(keyCode == 37){ //Izquierda
                tablero.moverJugador(0, -1);
            }
            else if(keyCode == 39){ //Derecha
                tablero.moverJugador(0, 1);
            }
            repaint();
        }
        @Override
        public void keyReleased(KeyEvent e) {
        
        }
        
        });
    }
    /*public void keyPressed(KeyEvent e){
        int keyCode = e.getKeyCode();
        
        if(keyCode == 38){ //Arriba
            tablero.moverJugador(-1, 0);
        }
        else if(keyCode == 40){ //Abajo
            tablero.moverJugador(1, 0);
        }
        else if(keyCode == 37){ //Izquierda
            tablero.moverJugador(0, -1);
        }
        else if(keyCode == 39){ //Derecha
            tablero.moverJugador(0, 1);
        }
    }*/
}
