package co.edu.unbosque.view;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import co.edu.unbosque.controller.Controller;
import co.edu.unbosque.model.CancionDTO;
import co.edu.unbosque.model.persistence.CancionDAO;
import java.awt.*;

public class EmisoraView extends JFrame {

	// Constante para el serialVersionUID
	private static final long serialVersionUID = 1L;
	
	// Componentes de la interfaz gráfica
	private JPanel contentPane;
	private JButton btnAgregarCancion;
	private JButton btnEliminarCancion;
	private JButton btnAgregarCancionesParrilla;
	private JButton btnMostrarParrilla;
	private JList<String> listaCanciones;
	private DefaultListModel<String> modeloListaCanciones;
	
	// Instancias de otras clases
	private ParrillaView parrillaView;
	private EmisoraView emisoraView = this;
	private CancionDAO cancionDAO;

	// Constructor
	public EmisoraView(Controller controller) {
		cancionDAO = controller.getCancionDAO();
		setTitle("B.T.M.A CodeCrafters Emisora");
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		contentPane.setBackground(UIManager.getColor("InternalFrame.borderShadow"));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Inicialización del panel de botones
		JPanel panelBotones = new JPanel();
		panelBotones.setBounds(10, 10, 764, 161);
		panelBotones.setLayout(new GridLayout(2, 2, 20, 20));
		panelBotones.setBackground(UIManager.getColor("InternalFrame.borderShadow"));
		contentPane.add(panelBotones);

		// Inicialización del botón "Agregar Canción"
		btnAgregarCancion = new JButton("AGREGAR CANCIÓN");
		btnAgregarCancion.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnAgregarCancion.addActionListener(controller);
		btnAgregarCancion.setPreferredSize(new Dimension(150, 40));
		btnAgregarCancion.setBackground(new Color(70, 130, 180));
		btnAgregarCancion.setForeground(Color.BLACK);
		panelBotones.add(btnAgregarCancion);

		// Inicialización del botón "Agregar a la Parrilla"
		btnAgregarCancionesParrilla = new JButton("AGREGAR A LA PARRILLA");
		btnAgregarCancionesParrilla.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnAgregarCancionesParrilla.addActionListener(e -> agregarCancionAParrilla());
		btnAgregarCancionesParrilla.setPreferredSize(new Dimension(150, 40));
		btnAgregarCancionesParrilla.setBackground(new Color(70, 130, 180));
		btnAgregarCancionesParrilla.setForeground(Color.BLACK);
		panelBotones.add(btnAgregarCancionesParrilla);

		// Inicialización del botón "Mostrar Parrilla"
		btnMostrarParrilla = new JButton("MOSTRAR PARRILLA");
		btnMostrarParrilla.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnMostrarParrilla.addActionListener(e -> parrillaView.setVisible(true));
		btnMostrarParrilla.setPreferredSize(new Dimension(150, 40));
		btnMostrarParrilla.setBackground(new Color(70, 130, 180));
		btnMostrarParrilla.setForeground(Color.BLACK);
		panelBotones.add(btnMostrarParrilla);

		// Inicialización del botón "Eliminar Canción"
		btnEliminarCancion = new JButton("ELIMINAR CANCIÓN");
		btnEliminarCancion.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnEliminarCancion.addActionListener(controller);
		btnEliminarCancion.setPreferredSize(new Dimension(150, 40));
		btnEliminarCancion.setBackground(new Color(70, 130, 180));
		btnEliminarCancion.setForeground(Color.BLACK);
		panelBotones.add(btnEliminarCancion);

		// Inicialización del panel de lista
		JPanel panelLista = new JPanel();
		panelLista.setBorder(new MatteBorder(2, 0, 0, 0, (Color) new Color(0, 0, 0)));
		panelLista.setBounds(10, 197, 764, 353);
		panelLista.setBackground(UIManager.getColor("InternalFrame.borderShadow"));
		contentPane.add(panelLista);

		// Inicialización del modelo y lista de canciones
		modeloListaCanciones = new DefaultListModel<>();
		panelLista.setLayout(null);
		listaCanciones = new JList<>(modeloListaCanciones);
		listaCanciones.setBorder(new MatteBorder(2, 2, 2, 2, Color.BLACK));
		listaCanciones.setBackground(Color.WHITE);
		listaCanciones.setForeground(Color.BLACK);
		listaCanciones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPane = new JScrollPane(listaCanciones);
		scrollPane.setBounds(0, 31, 764, 322);
		panelLista.add(scrollPane);

		// Inicialización de la etiqueta de la lista
		JLabel lblTituloLista = new JLabel("LISTA DE CANCIONES");
		lblTituloLista.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTituloLista.setBackground(Color.GRAY);
		lblTituloLista.setForeground(Color.BLACK);
		lblTituloLista.setHorizontalAlignment(SwingConstants.CENTER);
		lblTituloLista.setBounds(0, 0, 764, 31);
		panelLista.add(lblTituloLista);

		// Inicialización de la instancia de ParrillaView
		parrillaView = new ParrillaView(controller, controller.getParrillaReproduccion());
	}

	// Método para agregar una canción a la parrilla de reproducción
	private void agregarCancionAParrilla() {
		int indiceSeleccionado = listaCanciones.getSelectedIndex();
		if (indiceSeleccionado != -1) {
			CancionDTO cancion = cancionDAO.getCanciones().get(indiceSeleccionado);
			parrillaView.agregarCancionAParrilla(cancion);
		} else {
			JOptionPane.showMessageDialog(emisoraView, "Seleccione una canción para agregar a la parrilla.");
		}
	}

	// Método para actualizar la lista de canciones
	public void actualizarListaCanciones() {
	    SwingUtilities.invokeLater(() -> {
	        modeloListaCanciones.clear();
	        ArrayList<CancionDTO> canciones = cancionDAO.getCanciones();
	        if (!canciones.isEmpty()) {
	            for (CancionDTO cancion : canciones) {
	                modeloListaCanciones.addElement(cancion.getNombreArchivo());
	            }
	        } else {
	            // Si la lista está vacía, agregar un elemento vacío
	            //modeloListaCanciones.addElement("");
	        }
	        listaCanciones.setModel(modeloListaCanciones);
	    });
	}
	
	// Getters para obtener referencias a los componentes
	public JButton getBtnAgregarCancion() {
		return btnAgregarCancion;
	}

	public JButton getBtnEliminarCancion() {
		return btnEliminarCancion;
	}

	public JList<String> getListaCanciones() {
		return listaCanciones;
	}

	public JButton getBtnMostrarParrilla() {
		return btnMostrarParrilla;
	}
}