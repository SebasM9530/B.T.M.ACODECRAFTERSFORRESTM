package co.edu.unbosque.model;

import java.io.File;

public class CancionDTO {

    // Atributos
    private File archivo;
    private String nombreArchivo;
    private String artista;
    private String genero;

    // Constructor
    public CancionDTO(File archivo, String nombreArchivo, String artista, String genero) {
        this.archivo = archivo;
        this.nombreArchivo = nombreArchivo;
        this.artista = artista;
        this.genero = genero;
    }

    // Getters y Setters
    public File getArchivo() {
        return archivo;
    }

    public void setArchivo(File archivo) {
        this.archivo = archivo;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}