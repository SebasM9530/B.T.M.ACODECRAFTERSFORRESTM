package co.edu.unbosque.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.InputStream;
import java.net.URISyntaxException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import co.edu.unbosque.model.CancionDTO;
import co.edu.unbosque.model.ParrillaReproduccion;
import co.edu.unbosque.model.persistence.CancionDAO;
import co.edu.unbosque.view.EmisoraView;
import co.edu.unbosque.view.ParrillaView;
import co.edu.unbosque.view.View;

public class Controller implements ActionListener {

    // Instancias de las vistas y modelos
    private View vista;
    private EmisoraView emisoraView;
    private CancionDAO cancionDAO;
    private ParrillaReproduccion parrillaReproduccion;
    private ParrillaView parrillaView; 

    // Constructor
    public Controller() {
    	vista = new View(this);
        vista.setVisible(true);
        cancionDAO = new CancionDAO();
        parrillaReproduccion = new ParrillaReproduccion(cancionDAO);
        parrillaView = null;
        cargarCanciones();
    }

    // Carga una canción por defecto desde un archivo
    private void cargarCancionPorDefecto(String rutaArchivo) {
        InputStream inputStream = Controller.class.getResourceAsStream(rutaArchivo);
        if (inputStream != null) {
            try {
                File archivo = new File(Controller.class.getResource(rutaArchivo).toURI());
                String nombreArchivo = archivo.getName();
                String artista = "Artista Desconocido";
                String genero = "Género Desconocido";
                CancionDTO cancion = new CancionDTO(archivo, nombreArchivo, artista, genero);
                cancionDAO.agregarCancion(cancion);
            } catch (URISyntaxException e) {
                System.out.println("Error al cargar el archivo: " + e.getMessage());
            }
        } else {
            System.out.println("El archivo no se encontró en la ruta especificada: " + rutaArchivo);
        }
    }
    
    // Carga las canciones por defecto
    private void cargarCanciones() {
        String[] rutasArchivos = {"/canciones/ANUNCIO_1.mp3", "/canciones/ANUNCIO_2.mp3", "/canciones/Janji - Heroes Tonight (feat. Johnning) [NCS Release].mp3", "/canciones/Julius Dreisig & Zeus X Crona - Invisible [NCS Release].mp3"};
        for (String rutaArchivo : rutasArchivos) {
            cargarCancionPorDefecto(rutaArchivo);
        }
    }

    // Getter para obtener instancia de CancionDAO
    public CancionDAO getCancionDAO() {
        return cancionDAO;
    }

    // Getter para obtener instancia de ParrillaReproduccion
    public ParrillaReproduccion getParrillaReproduccion() {
        return parrillaReproduccion;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Si se hace clic en "Iniciar Emisora"
        if (e.getSource() == vista.getPanelPrincipal().getBtnIniciarEmisora()) {
            emisoraView = new EmisoraView(this);
            emisoraView.setVisible(true);
            emisoraView.actualizarListaCanciones();
        } else if (e.getSource() == emisoraView.getBtnAgregarCancion()) {
            // Si se hace clic en "Agregar Canción"
            agregarCancion();
        } else if (e.getSource() == emisoraView.getBtnEliminarCancion()) {
            // Si se hace clic en "Eliminar Canción"
            eliminarCancion();
        } else if (e.getSource() == emisoraView.getBtnMostrarParrilla()) {
            // Si se hace clic en "Mostrar Parrilla"
            parrillaView = new ParrillaView(this, parrillaReproduccion);
            parrillaView.setVisible(true);
        } else if (e.getSource() == parrillaView.btnReproducir) { 
            // Si se hace clic en "Reproducir" en la ParrillaView
            parrillaView.getParrillaReproduccion().reproducirCanciones();
        }
    }

    // Método para agregar una canción
    private void agregarCancion() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(emisoraView);
        if (result == JFileChooser.APPROVE_OPTION) {
            String nombreArchivo = fileChooser.getSelectedFile().getName();
            String artista = JOptionPane.showInputDialog(emisoraView, "Ingrese el nombre del artista:");
            String genero = JOptionPane.showInputDialog(emisoraView, "Ingrese el género musical:");
            CancionDTO cancion = new CancionDTO(fileChooser.getSelectedFile(), nombreArchivo, artista, genero);
            cancionDAO.agregarCancion(cancion);
            emisoraView.actualizarListaCanciones();
        }
    }

    // Método para eliminar una canción
    private void eliminarCancion() {
        int indiceSeleccionado = emisoraView.getListaCanciones().getSelectedIndex();
        if (indiceSeleccionado != -1) {
            cancionDAO.eliminarCancion(indiceSeleccionado);
            emisoraView.actualizarListaCanciones();
        } else {
            JOptionPane.showMessageDialog(emisoraView, "Seleccione una canción para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}