package co.edu.unbosque.view;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import co.edu.unbosque.controller.Controller;

public class View extends JFrame {

    // Constante para el serialVersionUID
    private static final long serialVersionUID = 1L;

    // Instancia del panel principal
    private PanelPrincipal panelPrincipal;

    // Constructor
    public View(Controller controller) {
        setSize(500, 325);
        setResizable(false);
        setTitle("B.T.M.A CodeCrafters");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Inicialización del panel principal
        panelPrincipal = new PanelPrincipal(controller);
        add(panelPrincipal, BorderLayout.CENTER);
    }

    // Getter para obtener el panel principal
    public PanelPrincipal getPanelPrincipal() {
        return panelPrincipal;
    }
}