package co.edu.unbosque.model;

import java.io.FileInputStream;
import java.io.InputStream;
import javazoom.jl.player.Player;

public class ReproductorMusical {

    // Instancia de Player para reproducir la música
    private Player player;

    // InputStream para leer el archivo de música
    private InputStream inputStream;

    // Bandera para indicar si la reproducción está pausada
    private boolean isPaused = false;

    // Hilo para la reproducción de música
    private Thread playbackThread;

    // Método para reproducir una canción
    public void reproducirCancion(CancionDTO cancion, ParrillaReproduccion parrilla) {
        playbackThread = new Thread(new Runnable() {
            public void run() {
                try {
                    inputStream = new FileInputStream(cancion.getArchivo());
                    player = new Player(inputStream);
                    isPaused = false; // Reiniciar el estado de pausa
                    player.play();
                } catch (Exception e) {
                    System.out.println("Error al reproducir el archivo MP3: " + e);
                } finally {
                    parrilla.avanzarCancionActual();
                    parrilla.reproducirCanciones();
                }
            }
        });
        playbackThread.start();
    }

    // Método para pausar la reproducción
    @SuppressWarnings("removal")
    public void pausarReproduccion() {
        if (playbackThread != null && playbackThread.isAlive() && !isPaused) {
            isPaused = true;
            playbackThread.suspend();
        }
    }

    // Método para reanudar la reproducción
    @SuppressWarnings("removal")
    public void reanudarReproduccion() {
        if (playbackThread != null && playbackThread.isAlive() && isPaused) {
            isPaused = false;
            playbackThread.resume();
        }
    }

    // Método para detener la reproducción
    public void detenerReproduccion() {
        if (playbackThread != null && playbackThread.isAlive()) {
            playbackThread.interrupt();
        }
        if (player != null) {
            player.close();
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e) {
                    System.out.println("Error al cerrar el InputStream: " + e);
                }
            }
        }
    }
}