package com.mycompany.sokobanclase;

import javazoom.jl.player.Player;
import java.io.FileInputStream;
import java.io.BufferedInputStream;

public class MusicaFondo implements Runnable {
    private String filepath;
    private Player player;
    private Thread musicThread;
    private boolean playing = true;
    
    public MusicaFondo(String filepath) {
        this.filepath = filepath;
    }

    public void play() {
        musicThread = new Thread(this); // crea un hilo nuevo
        musicThread.start();            // lo arranca (ejecuta run())
    }

    @Override
    public void run() {
        while(playing){
            try {
                FileInputStream fis = new FileInputStream(filepath);
                BufferedInputStream bis = new BufferedInputStream(fis);
                player = new Player(bis);
                player.play();
            } catch (Exception e) {
                System.out.println("Error al reproducir música: " + e.getMessage());
            }
        }       
    }

    public void stop() {
        playing = false;
    }
}
