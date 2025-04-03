package main;


import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class TileMap extends JPanel{
    private int TileTamanho = 42;
    private BufferedImage imagenMuro;
    private BufferedImage imagenPersonaje;
    
    public TileMap(){
        try {
            imagenMuro = ImageIO.read(new File ("sprites/muro.png"));
            imagenPersonaje = ImageIO.read(new File ("sprites/personaje.png"));
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        
        if (imagenMuro == null){
            return;
        }
        
        for(int i = 0; i < 10; ++i){
            for(int j = 0; j < 10; ++j){
                paintWall(g,i,j);
                paintCharacter(g,i,j);
            }
        }
    }
    
    private void paintWall(Graphics g, int fila, int columna){
        if(fila == 0 || fila == 10 -1 || columna == 0 || columna == 10 - 1){
            g.drawImage(imagenMuro, columna*TileTamanho,fila*TileTamanho,TileTamanho,TileTamanho,this);
        }
    }
    
    private void paintCharacter(Graphics g, int fila, int columna){
        g.drawImage(imagenPersonaje, columna=250, fila=250, TileTamanho, TileTamanho, this);
    }
}
