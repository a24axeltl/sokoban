package com.mycompany.sokobanclase;

import java.util.ArrayList;
import java.util.List;

public class GestorNiveles {
    private int numeroNivel = 0;
    private char[][] tableroActual;
    private char[][] tableroNivel;
    private List<char[][]> niveles;
    
    public GestorNiveles(){
        
        niveles = new ArrayList<>();
        
        niveles.add(new char[][] {
            {'M','M','M','M',' ',' '},
            {'M','-','.','M',' ',' '},
            {'M','-','-','M','M','M'},
            {'M','F','P','-','-','M'},
            {'M','-','-','C','-','M'},
            {'M','-','-','-','M','M'},
            {'M','M','M','M',' ',' '}
        });
        niveles.add(new char[][] {
            {'M','M','M','M','M','M'},
            {'M','-','-','-','-','M'},
            {'M','-','M','P','-','M'},
            {'M','-','C','F','-','M'},
            {'M','-','.','F','-','M'},
            {'M','-','-','-','-','M'},
            {'M','M','M','M','M','M'}
        });
        niveles.add(new char[][] {
            {' ',' ','M','M','M','M',' ',' ',' '},
            {'M','M','M','-','-','M','M','M','M'},
            {'M','-','-','-','-','-','C','-','M'},
            {'M','-','M','-','-','M','C','-','M'},
            {'M','-','.','-','.','M','P','-','M'},
            {'M','M','M','M','M','M','M','M','M'}
        });
        niveles.add(new char[][] {
            {'M','M','M','M','M','M','M','M'},
            {'M','-','-','-','-','-','-','M'},
            {'M','-','.','-','-','C','P','M'},
            {'M','-','-','-','-','-','-','M'},
            {'M','M','M','M','M','-','-','M'},
            {' ',' ',' ',' ','M','M','M','M'}
        });
        niveles.add(new char[][] {
            {' ',' ','M','M','M','M','M',' '},
            {'M','M','M','-','-','-','M',' '},
            {'M','.','P','C','-','-','M',' '},
            {'M','M','M','-','C','.','M',' '},
            {'M','.','M','M','C','-','M',' '},
            {'M','-','M','-','.','-','M','M'},
            {'M','C','-','F','C','C','.','M'},
            {'M','-','-','-','.','-','-','M'},
            {'M','M','M','M','M','M','M','M'}
        });
    }
    
    public char[][] getTablero(){
        return obtenerTableroNivel(niveles.get(getNumeroNivel()));
    }
    
    public boolean avanzarNivel(){
        if(getNumeroNivel() + 1 < niveles.size()){
            setNumeroNivel(getNumeroNivel() + 1);
            return true;
        }
        return false;
    }
    
    public boolean nivelCompletado(){
        for(int i = 0; i < tableroActual.length; i++){
            for(int j = 0; j < tableroActual[0].length; j++){
                if(tableroActual[i][j] == 'C'){
                    return false;
                }
            }
        }
        return true;
    }
    
    private char[][] obtenerTableroNivel(char[][] tableroNivel){
        this.tableroNivel = tableroNivel;
        
        tableroActual = new char[tableroNivel.length][tableroNivel[0].length];
        
        for(int i = 0; i < tableroNivel.length; i++){
            for(int j = 0; j < tableroNivel[i].length; j++){
                tableroActual[i][j] = tableroNivel[i][j];
            }
        }
        return tableroActual;
    }

    public int getNumeroNivel() {
        return numeroNivel;
    }

    public void setNumeroNivel(int numeroNivel) {
        this.numeroNivel = numeroNivel;
    }
    
    
}
