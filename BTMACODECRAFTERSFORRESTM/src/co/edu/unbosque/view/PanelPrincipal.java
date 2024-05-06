package co.edu.unbosque.view;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import java.awt.*;
import javax.swing.border.*;
import co.edu.unbosque.controller.Controller;

public class PanelPrincipal extends JPanel {

    // Constante para el serialVersionUID
    private static final long serialVersionUID = 1L;

    // Componentes de la interfaz gráfica
    private JLabel lblBienvenida;
    private JButton btnIniciarEmisora;

    // Constructor
    public PanelPrincipal(Controller controller) {
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(20, 20, 20, 20));
        setBackground(UIManager.getColor("controlShadow"));

        // Inicialización del panel de bienvenida
        JPanel panelBienvenida = new JPanel();
        panelBienvenida.setBackground(UIManager.getColor("controlShadow"));
        panelBienvenida.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelBienvenida.setLayout(new BorderLayout());

        // Inicialización de la etiqueta de bienvenida
        lblBienvenida = new JLabel("<html><center>Bienvenido a la radio emisora B.T.M.A CodeCrafters<br>Emisora Streaming - Tops de la electrónica</center></html>");
        lblBienvenida.setFont(new Font("SansSerif", Font.BOLD, 18));
        lblBienvenida.setForeground(Color.BLACK);
        lblBienvenida.setHorizontalAlignment(JLabel.CENTER);
        panelBienvenida.add(lblBienvenida, BorderLayout.CENTER);

        add(panelBienvenida, BorderLayout.CENTER);

        // Inicialización del panel de botón
        JPanel panelBoton = new JPanel();
        panelBoton.setBackground(UIManager.getColor("controlShadow"));
        panelBoton.setLayout(new FlowLayout(FlowLayout.CENTER));

        // Inicialización del botón "Iniciar Emisora"
        btnIniciarEmisora = new JButton("Iniciar Emisora");
        btnIniciarEmisora.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnIniciarEmisora.setBackground(new Color(70, 130, 180));
        btnIniciarEmisora.setForeground(Color.WHITE);
        btnIniciarEmisora.addActionListener(controller);
        btnIniciarEmisora.setFocusPainted(false);
        btnIniciarEmisora.setPreferredSize(new Dimension(200, 50));
        btnIniciarEmisora.setBorder(new LineBorder(Color.GRAY, 1));
        panelBoton.add(btnIniciarEmisora);

        add(panelBoton, BorderLayout.SOUTH);
    }

    // Getter para obtener el botón "Iniciar Emisora"
    public JButton getBtnIniciarEmisora() {
        return btnIniciarEmisora;
    }
}