package co.edu.unbosque.model;

import java.util.ArrayList;
import java.util.List;

import co.edu.unbosque.model.persistence.CancionDAO;
import co.edu.unbosque.view.ParrillaView;

public class ParrillaReproduccion {

    // Instancia de ParrillaView
    private ParrillaView parrillaView;

    // Lista de canciones en la parrilla
    private List<CancionDTO> canciones;

    // Índice de la canción actual
    private int indiceCancionActual;

    // Instancia de ReproductorMusical
    private ReproductorMusical reproductor;

    // Instancia de CancionDAO
    private CancionDAO cancionDAO;

    // Constructor
    public ParrillaReproduccion(CancionDAO cancionDAO) {
        this.cancionDAO = cancionDAO;
        canciones = new ArrayList<>(cancionDAO.getCanciones());
        indiceCancionActual = 0;
        reproductor = new ReproductorMusical();
    }

    // Método para agregar una canción a la parrilla
    public void agregarCancionAParrilla(CancionDTO cancion) {
        canciones.add(cancion);
    }

    // Método para reproducir canciones
    public void reproducirCanciones() {
        if (!canciones.isEmpty()) {
            if (indiceCancionActual >= canciones.size()) {
                indiceCancionActual = 0;
            }
            CancionDTO cancion = canciones.get(indiceCancionActual);
            reproductor.reproducirCancion(cancion, this);
            parrillaView.actualizarInformacionCancion(cancion);
        }
    }

    // Método para avanzar a la siguiente canción
    public void avanzarCancionActual() {
        indiceCancionActual++;
        if (indiceCancionActual >= canciones.size()) {
            indiceCancionActual = 0;
        }
    }

    // Método para detener la reproducción
    public void detenerReproduccion() {
        reproductor.pausarReproduccion();
    }

    // Getters y setters
    public List<CancionDTO> getCanciones() {
        return canciones;
    }

    public void setCanciones(List<CancionDTO> canciones) {
        this.canciones = canciones;
    }

    public int getIndiceCancionActual() {
        return indiceCancionActual;
    }

    public void setIndiceCancionActual(int indiceCancionActual) {
        this.indiceCancionActual = indiceCancionActual;
    }

    public CancionDTO getCancionActual() {
        if (!canciones.isEmpty() && indiceCancionActual < canciones.size()) {
            return canciones.get(indiceCancionActual);
        }
        return null;
    }

    public ReproductorMusical getReproductor() {
        return reproductor;
    }

    public CancionDAO getCancionDAO() {
        return cancionDAO;
    }

    public void setParrillaView(ParrillaView parrillaView) {
        this.parrillaView = parrillaView;
    }
}