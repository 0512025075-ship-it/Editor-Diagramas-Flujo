package vista;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {

    public VentanaPrincipal() {
        setTitle("Editor Visual de Diagramas - Programación II");
        setSize(1180, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        PanelDiagrama panelDiagrama = new PanelDiagrama();
        
        BarraHerramientas barra = new BarraHerramientas(panelDiagrama);

        getContentPane().add(barra, BorderLayout.NORTH);
        getContentPane().add(panelDiagrama, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaPrincipal().setVisible(true));
    }
}
