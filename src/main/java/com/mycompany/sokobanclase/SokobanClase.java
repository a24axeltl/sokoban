package com.mycompany.sokobanclase;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class SokobanClase {
    private JFrame frame;
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SokobanClase().iniciar());       
    }
    
    private void iniciar(){
        frame = new JFrame ("SokobanTest");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new TileMap());
        frame.setSize(440, 440);
        frame.setVisible(true);
    }
}
