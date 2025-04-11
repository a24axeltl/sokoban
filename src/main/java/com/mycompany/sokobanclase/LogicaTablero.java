package com.mycompany.sokobanclase;

public class LogicaTablero {
    private char[][] tableroActual;
    private int jugadorX;
    private int jugadorY;
    private int botonX;
    private int botonY;
    private SonidoFondo sonido;
    
    public LogicaTablero(char[][] tablero){
        this.tableroActual = tablero;
        sonido = new SonidoFondo("musica/choqueCaja.mp3");
    }
    
    public void logicaJuego(int movimientoX, int movimientoY){
        posicionJugador();
        
        int posicionX = jugadorX + movimientoX;
        int posicionY = jugadorY + movimientoY;
        
        //Movimiento Personaje
        switch (tableroActual[posicionX][posicionY]) {
            case '-' -> movimientoCasillaVacia(posicionX,posicionY);
            case '.' -> movimientoBoton(posicionX,posicionY);
            case 'C' -> movimientoCajas(posicionX,posicionY,movimientoX,movimientoY);
            case 'F' -> movimientoCajasPosicion(posicionX,posicionY,movimientoX,movimientoY);
            default -> {
            }
        }
        
        //Comprobar Boton
        if(tableroActual[posicionX][posicionY] == '-' || tableroActual[botonX][botonY] == '-'){
            comprobarBoton(posicionX,posicionY);  
        }
    }
    
    
    private void movimientoCasillaVacia(int posicionX, int posicionY){
        tableroActual[jugadorX][jugadorY] = '-';
        tableroActual[posicionX][posicionY] = 'P';
        
        jugadorX = posicionX;
        jugadorY = posicionY;
    }
    
    private void movimientoBoton(int posicionX, int posicionY){
        botonX = posicionX;
        botonY = posicionY;
            
        tableroActual[jugadorX][jugadorY] = '-';
        tableroActual[posicionX][posicionY] = '+';
        
        jugadorX = posicionX;
        jugadorY = posicionY;
    }
    
    private void movimientoCajas(int posicionX, int posicionY, int movimientoX, int movimientoY){
        int posicionSiguienteX = posicionX + movimientoX;
        int posicionSiguienteY = posicionY + movimientoY;
                
        switch (tableroActual[posicionSiguienteX][posicionSiguienteY]) {
            case 'M', 'C', 'F' -> {
                tableroActual[posicionX][posicionY] = 'C';
                tableroActual[jugadorX][jugadorY] = 'P';
                sonido.play();
            }
            case '.' -> {
                tableroActual[jugadorX][jugadorY] = '-';
                tableroActual[posicionSiguienteX][posicionSiguienteY] = 'F';
                tableroActual[posicionX][posicionY] = 'P';
                
                jugadorX = posicionX;
                jugadorY = posicionY;
            }
            default -> {
                tableroActual[jugadorX][jugadorY] = '-';
                tableroActual[posicionSiguienteX][posicionSiguienteY] = 'C';
                tableroActual[posicionX][posicionY] = 'P';
                
                jugadorX = posicionX;
                jugadorY = posicionY;
            }
        }
    }
    
    private void movimientoCajasPosicion(int posicionX, int posicionY, int movimientoX, int movimientoY){
        int posicionSiguienteX = posicionX + movimientoX;
        int posicionSiguienteY = posicionY + movimientoY;
            
        botonX = posicionX;
        botonY = posicionY;
                
        switch (tableroActual[posicionSiguienteX][posicionSiguienteY]) {
            case 'M', 'C','F' -> {
                tableroActual[posicionX][posicionY] = 'F';
                tableroActual[jugadorX][jugadorY] = 'P';
                sonido.play();
            }
            case '.' -> {
                tableroActual[jugadorX][jugadorY] = '-';
                tableroActual[posicionSiguienteX][posicionSiguienteY] = 'F';
                tableroActual[posicionX][posicionY] = '+';
                
                jugadorX = posicionX;
                jugadorY = posicionY;
            }
            default -> {
                if(tableroActual[jugadorX][jugadorY] == '+'){
                    tableroActual[jugadorX][jugadorY] = '.';
                    tableroActual[posicionSiguienteX][posicionSiguienteY] = 'C';
                    tableroActual[posicionX][posicionY] = '+';
                
                    jugadorX = posicionX;
                    jugadorY = posicionY;
                }
                tableroActual[jugadorX][jugadorY] = '-';
                tableroActual[posicionSiguienteX][posicionSiguienteY] = 'C';
                tableroActual[posicionX][posicionY] = 'P';

                jugadorX = posicionX;
                jugadorY = posicionY;              
            }
        }
    }
    
    private void comprobarBoton(int posicionX, int posicionY){
        tableroActual[botonX][botonY] = '.';
        tableroActual[posicionX][posicionY] = 'P';
        jugadorX = posicionX;
        jugadorY = posicionY;
    }
    
    private void posicionJugador(){
        for(int i = 0; i < tableroActual.length; i++){
            for(int j = 0; j < tableroActual[0].length; j++){
                if(tableroActual[i][j] == 'P'){
                    jugadorX = i;
                    jugadorY = j;
                }
            }
        }
    }
    
    public char[][] getTablero() {
        return tableroActual;
    }
    public void setTableroActual(char[][] tableroActual) {
        this.tableroActual = tableroActual;
    } 
}
