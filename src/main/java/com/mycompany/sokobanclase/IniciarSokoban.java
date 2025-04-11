package com.mycompany.sokobanclase;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class IniciarSokoban {
    private LogicaTablero tablero;
    private GestorNiveles nivel;
    private TileMap panel;
    private MusicaFondo musica;
    
    public IniciarSokoban(LogicaTablero tablero, GestorNiveles nivel, TileMap panel){
        this.tablero = tablero;
        this.nivel = nivel;
        this.panel = panel;
        
        musica = new MusicaFondo("musica/musica.mp3");
        musica.play(); // Arranca la música en segundo plano
    }
    
    public void ejecutar(){
        lecturaTeclado();
    }
    
    private void lecturaTeclado(){
        panel.addKeyListener(new KeyListener (){
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
                    resetNivel();
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
        if (nivel.nivelCompletado()) {
            if (nivel.avanzarNivel()) {
                tablero.setTableroActual(nivel.getTablero());
                SonidoFondo nivelCompletado = new SonidoFondo("musica/nivelCompletado.mp3");
                nivelCompletado.play();
                System.out.println("Nuevo Nivel Generado");
            } else {
                System.out.println("Niveles Terminados");
                System.exit(0);
            }
        }
        panel.repaint();
    }
    
    private void resetNivel(){
        tablero.setTableroActual(nivel.getTablero()); //Letra R(De reset)
        SonidoFondo resetSonido = new SonidoFondo("musica/reset.mp3");
        resetSonido.play();
    }
}
