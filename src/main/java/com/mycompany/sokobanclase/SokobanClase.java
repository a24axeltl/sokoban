package com.mycompany.sokobanclase;

import gui.TileMap;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class SokobanClase {
    private JFrame frame;
    private final JLabel instruccionMovimiento = new JLabel("🕹️ Usa las flechas para moverte");
    private final JLabel instruccionResetearNivel = new JLabel("🕹️ Para Resetear el nivel pulsa 'R'");
    private final JLabel instruccionAvanzarYRetroceder = new JLabel("🕹️ Para avanzar y retroceder de nivel pulsa 'S' y 'A'");
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SokobanClase().app());       
    }
    
    private void app(){
        frame = new JFrame("Sokoban");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(540, 665);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setPreferredSize(new Dimension(250, 100));

        
        panel.add(instruccionMovimiento, BorderLayout.NORTH);
        panel.add(instruccionResetearNivel, BorderLayout.WEST);
        panel.add(instruccionAvanzarYRetroceder, BorderLayout.SOUTH);

        frame.add(panel, BorderLayout.SOUTH);
        frame.add(new TileMap(), BorderLayout.CENTER);

        frame.setVisible(true);
    }
}