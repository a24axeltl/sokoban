package com.mycompany.sokobanclase;

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
    private BufferedImage imagenPersonajeBoton;
    
    private LogicaTablero tablero;
    private GestorNiveles nivel;
    private IniciarSokoban sokoban;
    
    
    public TileMap() {
        nivel = new GestorNiveles();
        tablero = new LogicaTablero(nivel.getTablero());
        sokoban = new IniciarSokoban(tablero,nivel,this);

        //Activar Sokoban:
        setFocusable(true);
        requestFocusInWindow();
       // sokoban.ejecutar();
       lecturaTeclado();
        
        try {
            imagenMuro = ImageIO.read(new File ("sprites/muro.png"));
            imagenPersonaje = ImageIO.read(new File ("sprites/personajeAbajo.png"));
            imagenCaja = ImageIO.read(new File ("sprites/caja.png"));
            imagenCajaFinal = ImageIO.read(new File("sprites/cajaLugar.png"));
            imagenBoton = ImageIO.read(new File("sprites/boton.png"));
            imagenPersonajeBoton = ImageIO.read(new File ("sprites/personajeBoton.png"));
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        
        char[][] tableroGrafico = tablero.getTablero();
        for(int i = 0; i < tableroGrafico.length; i++){
            for(int j = 0; j < tableroGrafico[i].length; j++){
                char parte = tableroGrafico[i][j];
                paintGame(g,i,j,parte);
            }
        }
    }
    
    private void paintGame(Graphics g, int fila, int columna,char parte){
        int dibujoX = fila*TileTamanho;
        int dibujoY = columna*TileTamanho;
        
        if (parte == 'M') {
            g.drawImage(imagenMuro, dibujoY, dibujoX, TileTamanho, TileTamanho,this);
        } 
        else if (parte == 'P') {
            g.drawImage(imagenPersonaje, dibujoY, dibujoX, TileTamanho, TileTamanho, this);
        }
        else if (parte == 'C') {
            g.drawImage(imagenCaja, dibujoY, dibujoX, TileTamanho, TileTamanho, this);
        }
        else if (parte == 'F') {
            g.drawImage(imagenCajaFinal, dibujoY, dibujoX, TileTamanho, TileTamanho, this);
        }
        else if (parte == '.') {
            g.drawImage(imagenBoton, dibujoY, dibujoX, TileTamanho, TileTamanho, this);
        }
        else if (parte == '+'){
            g.drawImage(imagenPersonajeBoton, dibujoY, dibujoX, TileTamanho, TileTamanho, this);
        }
    }
    
    private void lecturaTeclado(){
        addKeyListener(new KeyListener (){
        @Override
        public void keyTyped(KeyEvent e) {
        
        }

        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            int direccionFila = 0;
            int direccionColumna = 0;
        
            switch (keyCode) {
                case 38 -> //Arriba
                    direccionFila = -1;
                case 40 -> //Abajo
                    direccionFila = 1;
                case 37 -> //Izquierda
                    direccionColumna = -1;
                case 39 -> //Derecha
                    direccionColumna = 1;
                case 82 -> {
                    tablero = new LogicaTablero(nivel.getTablero()); //Letra R(De reset)
                    SonidoFondo resetSonido = new SonidoFondo("musica/reset.mp3");
                    resetSonido.play();
                }
                default -> {
                }
            }
            iniciarSokoban(direccionFila,direccionColumna);
        }
        @Override
        public void keyReleased(KeyEvent e) {
        
        }
        
        });
    }
    
    private void iniciarSokoban(int direccionFila, int direccionColumna){
        tablero.logicaJuego(direccionFila, direccionColumna);    
            if(nivel.nivelCompletado()){
                if(nivel.avanzarNivel()){
                    tablero = new LogicaTablero(nivel.getTablero());
                    SonidoFondo nivelCompletado = new SonidoFondo("musica/nivelCompletado.mp3");
                    nivelCompletado.play();
                    System.out.println("Nuevo Nivel Generado");  
                }
                else{
                    System.out.println("Niveles Terminados");
                    System.exit(0);
                }
            }    
            repaint();
    }
}
