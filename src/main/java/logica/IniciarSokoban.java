package logica;

import gui.TileMap;
import audio.MusicaFondo;
import audio.SonidoFondo;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class IniciarSokoban {
    private static final int TECLA_R = 82;
    private static final int TECLA_S = 83;
    private static final int TECLA_A = 65;
    private static final int FLECHA_DERECHA = 39;
    private static final int FLECHA_IZQUIERDA = 37;
    private static final int FLECHA_ABAJO = 40;
    private static final int FLECHA_ARRIBA = 38;
    
    private LogicaTablero tablero;
    private final GestorNiveles nivel;
    private final TileMap panel;
    private final MusicaFondo musica;
    
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
                case FLECHA_ARRIBA ->
                    direccionFila = -1;
                case FLECHA_ABAJO ->
                    direccionFila = 1;
                case FLECHA_IZQUIERDA ->
                    direccionColumna = -1;
                case FLECHA_DERECHA ->
                    direccionColumna = 1;
                case TECLA_R -> {
                    resetNivel();
                }
                case TECLA_S -> {
                    avanzarNivel();
                }
                case TECLA_A -> {
                    retrocederNivel();
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
                tablero = new LogicaTablero(nivel.getTablero());
                panel.actualizarPantalla(tablero);
                System.out.println("Siguiente Nivel Generado");
            } else {
                System.out.println("Niveles Terminados");
                System.exit(0);
            }
        }
        panel.repaint();
    }
    
    private void resetNivel(){
        tablero = new LogicaTablero(nivel.getTablero());
        panel.actualizarPantalla(tablero);
        SonidoFondo resetSonido = new SonidoFondo("musica/reset.mp3");
        resetSonido.play();
    }
    
    private void avanzarNivel(){
        if(nivel.getNumeroNivel() + 1 < nivel.getNiveles().size()){
            nivel.setNumeroNivel(nivel.getNumeroNivel() + 1);
            tablero = new LogicaTablero(nivel.getTablero());
            panel.actualizarPantalla(tablero);
            System.out.println("Nivel: " + (nivel.getNumeroNivel() + 1));
            resetNivel();
        }
        else{
            System.out.println("Niveles Terminados");
            System.exit(0); 
        } 
    }
    
    private void retrocederNivel(){
        try {
            if (nivel.getNumeroNivel() - 1 < nivel.getNiveles().size()) {
                nivel.setNumeroNivel(nivel.getNumeroNivel() - 1);
                tablero = new LogicaTablero(nivel.getTablero());
                panel.actualizarPantalla(tablero);
                System.out.println("Nivel: " + (nivel.getNumeroNivel() + 1));
                resetNivel();
            }
        } catch (Exception e) {
            System.out.println("No hay niveles anteriores");
            nivel.setNumeroNivel(0);
        }
    }
}
