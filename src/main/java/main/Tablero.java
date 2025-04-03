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
    
    public char[][] getTablero() {
        return tablero;
    }
    
    
}
