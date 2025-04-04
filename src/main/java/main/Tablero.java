package main;

public class Tablero {
    private char[][] tablero = {
        {' ',' ','M','M','M','M','M',' '},
        {'M','M','M','-','-','-','M',' '},
        {'M','.','P','C','-','-','M',' '},
        {'M','M','M','-','C','.','M',' '},
        {'M','.','M','M','C','-','M',' '},
        {'M','-','M','-','-','-','M','M'},
        {'M','-','-','F','C','C','.','M'},
        {'M','-','-','-','.','-','-','M'},
        {'M','M','M','M','M','M','M','M'}
    };
    private int jugadorFila = 2;
    private int jugadorColumna = 2;
    
    public char[][] getTablero() {
        return tablero;
    }
    
    public void moverJugador(int direccionFila, int direccionColumna){
        int nuevaFila = jugadorFila + direccionFila;
        int nuevaColumna = jugadorColumna + direccionColumna;
        int posicionBotonFila = 2;
        int posicionBotonColumna = 1;
        
        if(tablero[nuevaFila][nuevaColumna] == '-'){
            tablero[jugadorFila][jugadorColumna] = '-';
            tablero[nuevaFila][nuevaColumna] = 'P';
            jugadorFila = nuevaFila;
            jugadorColumna = nuevaColumna;
        }
        
        if(tablero[nuevaFila][nuevaColumna] == '.'){
            posicionBotonFila = nuevaFila;
            posicionBotonColumna = nuevaColumna;
            
            tablero[jugadorFila][jugadorColumna] = '-';
            tablero[nuevaFila][nuevaColumna] = 'P';
            jugadorFila = nuevaFila;
            jugadorColumna = nuevaColumna;
        }
        else if(tablero[nuevaFila][nuevaColumna] == '-' || tablero[posicionBotonFila][posicionBotonColumna] == '-'){
            tablero[posicionBotonFila][posicionBotonColumna] = '.';
            tablero[nuevaFila][nuevaColumna] = 'P';
            jugadorFila = nuevaFila;
            jugadorColumna = nuevaColumna;
        }
        
        if(tablero[nuevaFila][nuevaColumna] == 'C'){
            if(tablero[nuevaFila][nuevaColumna + 1] != 'M'){
                tablero[jugadorFila][jugadorColumna] = '-';
                tablero[nuevaFila][nuevaColumna + 1] = 'C';
                tablero[nuevaFila][nuevaColumna] = 'P';
                jugadorFila = nuevaFila;
                jugadorColumna = nuevaColumna;
            }
            else{
                tablero[nuevaFila][nuevaColumna] = 'C';
                tablero[jugadorFila][jugadorColumna] = 'P';
            }
        }
    }    
}
