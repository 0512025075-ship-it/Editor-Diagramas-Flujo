package controlador;

import modelo.Bloque;
import modelo.Conexion;
import vista.PanelDiagrama;
import javax.swing.*;
import java.util.List;

public class TareaEjecutarDiagrama implements Runnable {
    private List<Bloque> bloques;
    private List<Conexion> conexiones;
    private PanelDiagrama panel;
    private JButton btnEjecutar;

    public TareaEjecutarDiagrama(List<Bloque> bloques, List<Conexion> conexiones, PanelDiagrama panel, JButton btnEjecutar) {
        this.bloques = bloques;
        this.conexiones = conexiones;
        this.panel = panel;
        this.btnEjecutar = btnEjecutar;
    }

    @Override
    public void run() {
        // Buscar el bloque de inicio
        Bloque actual = bloques.stream()
                .filter(b -> b.getTipo() == Bloque.Tipo.INICIO)
                .findFirst()
                .orElse(null);

        while (actual != null) {
            final Bloque pasoActual = actual;

            // Iluminar bloque actual en la GUI (SwingUtilities.invokeLater)
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    pasoActual.setEjecutando(true);
                    panel.repaint();
                }
            });

            // Simular el tiempo de procesamiento (Thread.sleep())
            try {
                Thread.sleep(1000); // 1 segundo por paso
            } catch (InterruptedException e) {
                System.out.println("Ejecución interrumpida");
            }

            //Apagar iluminación del bloque
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    pasoActual.setEjecutando(false);
                    panel.repaint();
                }
            });

            if (actual.getTipo() == Bloque.Tipo.FIN) {
                actual = null; // Terminar recorrido
            } else {
                // Siguiente bloque
                final Bloque dePaso = actual;
                actual = conexiones.stream()
                        .filter(c -> c.getOrigen() == dePaso)
                        .map(Conexion::getDestino)
                        .findFirst()
                        .orElse(null);
            }
        }

        //Restaurar el botón al finalizar la ejecución
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                btnEjecutar.setEnabled(true);
                JOptionPane.showMessageDialog(panel, "Ejecución del diagrama completada.");
            }
        });
    }
}
