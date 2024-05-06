package co.edu.unbosque.model.persistence;

import co.edu.unbosque.model.CancionDTO;
import java.util.ArrayList;

public class CancionDAO {

    // Lista de canciones
    private ArrayList<CancionDTO> canciones;

    // Constructor
    public CancionDAO() {
        canciones = new ArrayList<>();
    }

    // Método para agregar una canción
    public void agregarCancion(CancionDTO cancion) {
        canciones.add(cancion);
    }

    // Método para eliminar una canción
    public void eliminarCancion(int index) {
        if (!canciones.isEmpty()) { // Verifica si la lista de canciones no está vacía
            canciones.remove(index);
        }
    }

    // Getter para obtener la lista de canciones
    public ArrayList<CancionDTO> getCanciones() {
        return canciones;
    }

    // Setter para establecer la lista de canciones
    public void setCanciones(ArrayList<CancionDTO> canciones) {
        this.canciones = canciones;
    }
}