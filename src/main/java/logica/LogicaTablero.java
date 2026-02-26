package logica;

import audio.SonidoFondo;

public class LogicaTablero {
    private static final char SUELO = ' ';
    private static final char MURO = '#';
    private static final char BOTON = '.';
    private static final char CAJA = '$';
    private static final char CAJA_EN_BOTON = '*';
    private static final char PERSONAJE = '@';
    private static final char PERSONAJE_EN_BOTON = '+';
    
    private final char[][] tablero;
    private int coordenada_PersonajeX;
    private int coordenada_PersonajeY;
    private int botonX;
    private int botonY;
    private final SonidoFondo choqueCaja;
    private final SonidoFondo boton;
    
    public LogicaTablero(char[][] tablero){
        this.tablero = tablero;
        choqueCaja = new SonidoFondo("musica/choqueCaja.mp3");
        boton = new SonidoFondo("musica/boton.mp3");
    }
    
    public void logicaJuego(int posicionX, int posicionY){
        posicionJugador();
        
        int movimientoX = coordenada_PersonajeX + posicionX;
        int movimientoY = coordenada_PersonajeY + posicionY;
        
        //Movimiento Personaje
        switch (tablero[movimientoX][movimientoY]) {

            case SUELO -> movimientoCasillaVacia(movimientoX,movimientoY);
            case BOTON -> movimientoBoton(movimientoX,movimientoY);
            case CAJA -> movimientoCaja(movimientoX,movimientoY,posicionX,posicionY);
            case CAJA_EN_BOTON -> movimientoCajaEnBoton(movimientoX,movimientoY,posicionX,posicionY);
        }
        
        //Comprobar Boton
        if(tablero[movimientoX][movimientoY] == SUELO || tablero[botonX][botonY] == SUELO){
            comprobarPosicionBoton(movimientoX,movimientoY);  
        }
    }
    
    private void movimientoCasillaVacia(int posicionX, int posicionY){
        comprobarSiSeEstaEncimaDeUnBoton();
        tablero[posicionX][posicionY] = PERSONAJE;
        
        coordenada_PersonajeX = posicionX;
        coordenada_PersonajeY = posicionY;
    }
    
    private void movimientoBoton(int posicionX, int posicionY){
        botonX = posicionX;
        botonY = posicionY;
        
        comprobarSiSeEstaEncimaDeUnBoton();
        tablero[posicionX][posicionY] = PERSONAJE_EN_BOTON;
        boton.play();
        
        coordenada_PersonajeX = posicionX;
        coordenada_PersonajeY = posicionY;
    }
    
    private void movimientoCaja(int posicionX, int posicionY, int movimientoX, int movimientoY){
        int movimientoSiguienteX = posicionX + movimientoX;
        int movimientoSiguienteY = posicionY + movimientoY;
                
        switch (tablero[movimientoSiguienteX][movimientoSiguienteY]) {
            case MURO, CAJA, CAJA_EN_BOTON -> {
                comprobarSiSeEstaEncimaDeUnBotonMoviendoCajas();
                tablero[posicionX][posicionY] = CAJA;
                choqueCaja.play();
            }
            case BOTON -> {
                comprobarSiSeEstaEncimaDeUnBoton();
                tablero[movimientoSiguienteX][movimientoSiguienteY] = CAJA_EN_BOTON;
                tablero[posicionX][posicionY] = PERSONAJE;
                boton.play();
                
                coordenada_PersonajeX = posicionX;
                coordenada_PersonajeY = posicionY;
            }
            default -> {
                tablero[coordenada_PersonajeX][coordenada_PersonajeY] = SUELO;
                tablero[movimientoSiguienteX][movimientoSiguienteY] = CAJA;
                tablero[posicionX][posicionY] = PERSONAJE;
                
                coordenada_PersonajeX = posicionX;
                coordenada_PersonajeY = posicionY;
            }
        }
    }
    
    private void movimientoCajaEnBoton(int posicionX, int posicionY, int movimientoX, int movimientoY){
        int movimientoSiguienteX = posicionX + movimientoX;
        int movimientoSiguienteY = posicionY + movimientoY;
            
        botonX = posicionX;
        botonY = posicionY;
                
        switch (tablero[movimientoSiguienteX][movimientoSiguienteY]) {
            case MURO, CAJA, CAJA_EN_BOTON -> {
                comprobarSiSeEstaEncimaDeUnBotonMoviendoCajas();
                tablero[posicionX][posicionY] = CAJA_EN_BOTON;
                choqueCaja.play();
            }
            case BOTON -> {
                comprobarSiSeEstaEncimaDeUnBoton();
                tablero[movimientoSiguienteX][movimientoSiguienteY] = CAJA_EN_BOTON;
                tablero[posicionX][posicionY] = PERSONAJE_EN_BOTON;
                boton.play();
                
                coordenada_PersonajeX = posicionX;
                coordenada_PersonajeY = posicionY;
            }
            default -> {
                comprobarSiSeEstaEncimaDeUnBoton();
                tablero[movimientoSiguienteX][movimientoSiguienteY] = CAJA;
                tablero[posicionX][posicionY] = PERSONAJE_EN_BOTON;
                boton.play();
                
                coordenada_PersonajeX = posicionX;
                coordenada_PersonajeY = posicionY;
            }
        }
    }
    
    private void comprobarSiSeEstaEncimaDeUnBoton(){
        if(tablero[coordenada_PersonajeX][coordenada_PersonajeY] == PERSONAJE_EN_BOTON){
            tablero[coordenada_PersonajeX][coordenada_PersonajeY] = BOTON;
        }
        else{
            tablero[coordenada_PersonajeX][coordenada_PersonajeY] = SUELO;
        }
    }
    
    private void comprobarSiSeEstaEncimaDeUnBotonMoviendoCajas(){
        if (tablero[coordenada_PersonajeX][coordenada_PersonajeY] == PERSONAJE_EN_BOTON) {
            tablero[coordenada_PersonajeX][coordenada_PersonajeY] = PERSONAJE_EN_BOTON;
        }
        else {
            tablero[coordenada_PersonajeX][coordenada_PersonajeY] = PERSONAJE;
        }
    }
    
    private void comprobarPosicionBoton(int posicionX, int posicionY){
        tablero[botonX][botonY] = BOTON;
        tablero[posicionX][posicionY] = PERSONAJE;
        
        coordenada_PersonajeX = posicionX;
        coordenada_PersonajeY = posicionY;
    }
    
    private void posicionJugador(){
        for(int i = 0; i < tablero.length; i++){
            for(int j = 0; j < tablero[0].length; j++){
                if(tablero[i][j] == PERSONAJE){
                    coordenada_PersonajeX = i;
                    coordenada_PersonajeY = j;
                }
            }
        }
    }
    
    public char[][] getTablero() {
        return tablero;
    }
}