package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class SokobanTest {
    private JFrame frame;
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SokobanTest().iniciar());
    }
    
    private void iniciar(){
        frame = new JFrame ("SokobanTest");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new TileMap());
        frame.setSize(375, 440);
        frame.setVisible(true);
        leerTeclado();
    }
    
    private void leerTeclado(){
        frame.addKeyListener(new KeyListener (){
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                switch(e.getKeyCode()){
                    case 38:
                        System.out.println("Arriba");
                    case 40:
                        System.out.println("Abajo");
                    case 37:
                        System.out.println("Izquierda");
                    case 39:
                        System.out.println("Derecha");
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }  
        });
    }
}
