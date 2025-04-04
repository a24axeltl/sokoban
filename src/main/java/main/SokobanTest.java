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
    }
}
