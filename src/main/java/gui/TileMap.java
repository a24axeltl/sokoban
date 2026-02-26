package gui;

import javax.swing.ImageIcon;

import logica.GestorNiveles;
import logica.IniciarSokoban;
import logica.LogicaTablero;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class TileMap extends JPanel{
    private static final int TILE_TAMANHO = 45;
    private static final char BORDE = '-';
    private static final char MURO = '#';
    private static final char BOTON = '.';
    private static final char CAJA = '$';
    private static final char CAJA_EN_BOTON = '*';
    private static final char PERSONAJE = '@';
    private static final char PERSONAJE_EN_BOTON = '+';
    
    private BufferedImage imagenSuelo;
    private BufferedImage imagenMuro;
    private BufferedImage imagenCaja;
    private BufferedImage imagenCajaFinal;
    private BufferedImage imagenBoton;
    private BufferedImage imagenPersonajeBoton;
    private ImageIcon imagenPersonajeGif;
    
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
        sokoban.ejecutar();
        
        try {
            ImageIcon iconOriginal = new ImageIcon("sprites/personaje.gif");
            Image imagenEscalada = iconOriginal.getImage().getScaledInstance(TILE_TAMANHO, TILE_TAMANHO, Image.SCALE_DEFAULT);
            imagenPersonajeGif = new ImageIcon(imagenEscalada);

            imagenSuelo = ImageIO.read(new File ("sprites/suelo.png"));
            imagenMuro = ImageIO.read(new File ("sprites/muro.png"));
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
        int dibujoX = fila*TILE_TAMANHO;
        int dibujoY = columna*TILE_TAMANHO;
        
        if (parte != BORDE) {
            g.drawImage(imagenSuelo, dibujoY, dibujoX, TILE_TAMANHO, TILE_TAMANHO, this);
        }
        
        if (parte == MURO) {
            g.drawImage(imagenMuro, dibujoY, dibujoX, TILE_TAMANHO, TILE_TAMANHO,this);
        }
        else if (parte == PERSONAJE) {
            imagenPersonajeGif.paintIcon(this, g, dibujoY, dibujoX);
        }
        else if (parte == CAJA) {
            g.drawImage(imagenCaja, dibujoY, dibujoX, TILE_TAMANHO, TILE_TAMANHO, this);
        }
        else if (parte == CAJA_EN_BOTON) {
            g.drawImage(imagenCajaFinal, dibujoY, dibujoX, TILE_TAMANHO, TILE_TAMANHO, this);
        }
        else if (parte == BOTON) {
            g.drawImage(imagenBoton, dibujoY, dibujoX, TILE_TAMANHO + 10, TILE_TAMANHO + 5, this);
        }
        else if (parte == PERSONAJE_EN_BOTON){
            g.drawImage(imagenPersonajeBoton, dibujoY, dibujoX, TILE_TAMANHO + 10, TILE_TAMANHO + 5, this);
        }
    }
    
    public void actualizarPantalla(LogicaTablero nuevoTablero) {
        this.tablero = nuevoTablero;
    }
}