package audio;

import javazoom.jl.player.Player;
import java.io.FileInputStream;
import java.io.BufferedInputStream;

public class SonidoFondo implements Runnable {
    private String filepath;
    private Player player;
    private Thread sonidoThread;

    public SonidoFondo(String filepath) {
        this.filepath = filepath;
    }

    public void play() {
        sonidoThread = new Thread(this); // crea un hilo nuevo
        sonidoThread.start();            // lo arranca (ejecuta run())
    }

    @Override
    public void run() {
        try {
            FileInputStream fis = new FileInputStream(filepath);
            BufferedInputStream bis = new BufferedInputStream(fis);
            player = new Player(bis);
            player.play(); // reproduce y se bloquea SOLO este hilo
        } catch (Exception e) {
            System.out.println("Error al reproducir música: " + e.getMessage());
        }
    }

    public void stop() {
        if (player != null) {
            player.close(); // detiene la música
        }
    }
}
