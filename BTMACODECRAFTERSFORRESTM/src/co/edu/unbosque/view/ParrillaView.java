package co.edu.unbosque.view;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import co.edu.unbosque.controller.Controller;
import co.edu.unbosque.model.CancionDTO;
import co.edu.unbosque.model.ParrillaReproduccion;

public class ParrillaView extends JFrame {

    // Constante para el serialVersionUID
    private static final long serialVersionUID = 1L;

    // Componentes de la interfaz gráfica
    private JPanel contentPane;
    public JButton btnReproducir;
    private JButton btnPausar;
    private JButton btnReanudar;
    private JButton btnRegresar;
    private JList<String> listaParrilla;
    private DefaultListModel<String> modeloListaParrilla;
    private JLabel lblInformacionCancion;
    private JLabel lblNombreArtista;
    private JLabel lblGenero;

    // Instancia de ParrillaReproduccion
    private ParrillaReproduccion parrillaReproduccion;

    // Constructor
    public ParrillaView(Controller controller, ParrillaReproduccion parrillaReproduccion) {
        this.parrillaReproduccion = parrillaReproduccion;
        parrillaReproduccion.setParrillaView(this);

        setTitle("Parrilla de Programación");
        setSize(649, 412);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setBackground(UIManager.getColor("controlShadow"));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Inicialización del panel de botones
        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(UIManager.getColor("controlShadow"));
        panelBotones.setBounds(401, 77, 231, 290);
        contentPane.add(panelBotones);

        // Inicialización del botón "Reproducir"
        btnReproducir = new JButton("REPRODUCIR");
        btnReproducir.setBackground(new Color(70, 130, 180));
        btnReproducir.setFont(new Font("Arial", Font.BOLD, 12));
        btnReproducir.setBounds(40, 11, 157, 56);
        btnReproducir.addActionListener(e -> reproducirCanciones());
        panelBotones.setLayout(null);
        panelBotones.add(btnReproducir);

        // Inicialización del botón "Pausar"
        btnPausar = new JButton("PAUSAR");
        btnPausar.setBackground(new Color(70, 130, 180));
        btnPausar.setFont(new Font("Arial", Font.BOLD, 12));
        btnPausar.setBounds(40, 145, 157, 56);
        btnPausar.addActionListener(e -> {
            parrillaReproduccion.getReproductor().pausarReproduccion();
        });
        panelBotones.add(btnPausar);

        // Inicialización del botón "Reanudar"
        btnReanudar = new JButton("REANUDAR");
        btnReanudar.setBackground(new Color(70, 130, 180));
        btnReanudar.setFont(new Font("Arial", Font.BOLD, 12));
        btnReanudar.setBounds(40, 78, 157, 56);
        btnReanudar.addActionListener(e -> {
            parrillaReproduccion.getReproductor().reanudarReproduccion();
        });
        panelBotones.add(btnReanudar);

        // Inicialización del botón "Regresar"
        btnRegresar = new JButton("REGRESAR");
        btnRegresar.setBackground(new Color(70, 130, 180));
        btnRegresar.setFont(new Font("Arial", Font.BOLD, 12));
        btnRegresar.setBounds(40, 212, 157, 56);
        btnRegresar.addActionListener(e -> this.dispose());
        panelBotones.add(btnRegresar);

        // Inicialización del panel de lista
        JPanel panelLista = new JPanel();
        panelLista.setBounds(10, 77, 381, 290);
        contentPane.add(panelLista);

        // Inicialización del modelo y lista de canciones de la parrilla
        modeloListaParrilla = new DefaultListModel<>();
        panelLista.setLayout(null);
        listaParrilla = new JList<>(modeloListaParrilla);
        listaParrilla.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
        JScrollPane scrollPane = new JScrollPane(listaParrilla);
        scrollPane.setBounds(0, 0, 381, 289);
        panelLista.add(scrollPane);

        // Inicialización del panel de información
        JPanel panelInformacion = new JPanel();
        panelInformacion.setBackground(UIManager.getColor("controlShadow"));
        panelInformacion.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
        panelInformacion.setBounds(0, 0, 632, 72);
        contentPane.add(panelInformacion);
        panelInformacion.setLayout(null);

        // Inicialización de las etiquetas de información
        lblInformacionCancion = new JLabel("REPRODUCIENDO:");
        lblInformacionCancion.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblInformacionCancion.setBounds(10, 0, 622, 19);
        panelInformacion.add(lblInformacionCancion);

        lblNombreArtista = new JLabel("ARTISTA:");
        lblNombreArtista.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblNombreArtista.setBounds(10, 24, 622, 19);
        panelInformacion.add(lblNombreArtista);

        lblGenero = new JLabel("GÉNERO:");
        lblGenero.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblGenero.setBounds(10, 48, 622, 19);
        panelInformacion.add(lblGenero);

        new ArrayList<>();
    }

    // Método para agregar una canción a la parrilla de reproducción
    public void agregarCancionAParrilla(CancionDTO cancion) {
        parrillaReproduccion.agregarCancionAParrilla(cancion);
        actualizarListaParrilla();
        JOptionPane.showMessageDialog(this, "Canción agregada a la Parrilla de Programación exitosamente.",
                "Confirmación", JOptionPane.INFORMATION_MESSAGE);
    }

    // Método para reproducir canciones
    private void reproducirCanciones() {
        parrillaReproduccion.reproducirCanciones();
        CancionDTO cancionActual = parrillaReproduccion.getCancionActual();
        if (cancionActual != null) {
            mostrarInformacionCancion(cancionActual);
        }
    }

    // Método para actualizar la lista de canciones en la parrilla
    private void actualizarListaParrilla() {
        modeloListaParrilla.clear();
        List<CancionDTO> canciones = parrillaReproduccion.getCanciones();
        for (CancionDTO cancion : canciones) {
            modeloListaParrilla.addElement(cancion.getNombreArchivo());
        }
    }

    // Método para mostrar la información de una canción
    private void mostrarInformacionCancion(CancionDTO cancion) {
        lblInformacionCancion.setText("Reproduciendo: " + cancion.getNombreArchivo());
        lblNombreArtista.setText("Nombre del Artista: " + cancion.getArtista());
        lblGenero.setText("Género: " + cancion.getGenero());
    }

    // Método para actualizar la información de la canción actual
    public void actualizarInformacionCancion(CancionDTO cancion) {
        lblInformacionCancion.setText("Reproduciendo: " + cancion.getNombreArchivo());
        lblNombreArtista.setText("Nombre del Artista: " + cancion.getArtista());
        lblGenero.setText("Género: " + cancion.getGenero());
    }

    // Getter para obtener la instancia de ParrillaReproduccion
    public ParrillaReproduccion getParrillaReproduccion() {
        return parrillaReproduccion;
    }
}