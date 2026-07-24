//Es el contenedor raíz (JFrame) que inicia y ensambla la aplicación.
package vista;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {

    private JTextArea areaAvisos;
    private JTextArea areaPseudocodigo;
    private JTabbedPane panelInferior;

    public VentanaPrincipal() {
        setTitle("Editor Visual de Diagramas de Flujo");
        setSize(1200, 780);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 1. Crear el lienzo principal
        PanelDiagrama panelDiagrama = new PanelDiagrama();

        // 2. Crear las áreas de texto del panel inferior
        areaAvisos = new JTextArea();
        areaAvisos.setEditable(false);
        areaAvisos.setFont(new Font("Consolas", Font.PLAIN, 13));
        areaAvisos.setBackground(new Color(15, 23, 42)); // Azul oscuro nocturno
        areaAvisos.setForeground(new Color(56, 189, 248)); // Texto cian suave

        areaPseudocodigo = new JTextArea();
        areaPseudocodigo.setEditable(false);
        areaPseudocodigo.setFont(new Font("Consolas", Font.BOLD, 13));
        areaPseudocodigo.setBackground(new Color(15, 23, 42));
        areaPseudocodigo.setForeground(new Color(74, 222, 128)); // Texto verde código

        // 3. Crear el panel tabulado inferior
        panelInferior = new JTabbedPane();
        panelInferior.setPreferredSize(new Dimension(1200, 180));
        panelInferior.addTab("Avisos y Consola", new JScrollPane(areaAvisos));
        panelInferior.addTab("Pseudocódigo Generado", new JScrollPane(areaPseudocodigo));

        // 4. Crear la barra superior estilizada
        BarraHerramientas barra = new BarraHerramientas(panelDiagrama, this);

        // 5. Ensamblar la ventana
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(barra, BorderLayout.NORTH);
        getContentPane().add(panelDiagrama, BorderLayout.CENTER);
        getContentPane().add(panelInferior, BorderLayout.SOUTH);

        logAviso("Sistema iniciado correctamente. ¡Bienvenido!");
    }

    public void logAviso(String mensaje) {
        String hora = java.time.LocalTime.now().format(java.time.format.DateTimeFormatter.ofPattern("HH:mm:ss"));
        areaAvisos.append("[" + hora + "] " + mensaje + "\n");
        areaAvisos.setCaretPosition(areaAvisos.getDocument().getLength());
        panelInferior.setSelectedIndex(0); // Cambia a la pestaña de Avisos
    }

    public void mostrarPseudocodigo(String codigo) {
        areaPseudocodigo.setText(codigo);
        panelInferior.setSelectedIndex(1); // Cambia a la pestaña de Pseudocódigo
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaPrincipal().setVisible(true));
    }
}
