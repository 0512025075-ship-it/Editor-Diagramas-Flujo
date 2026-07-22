package vista;

import modelo.Bloque;
import javax.swing.*;
import java.awt.Point;
import java.awt.event.*;

public class GestorRaton extends MouseAdapter {
    private final PanelDiagrama panel;
    private Bloque bloqueArrastrado = null;
    private final Point desfase = new Point();

    public GestorRaton(PanelDiagrama panel) {
        this.panel = panel;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        var bloques = panel.getBloques();
        Bloque bloqueClickeado = null;

        for (int i = bloques.size() - 1; i >= 0; i--) {
            Bloque b = bloques.get(i);
            if (b.contienePunto(e.getX(), e.getY())) {
                bloqueClickeado = b;
                break;
            }
        }
        
        if (panel.isModoConectar() && bloqueClickeado != null) {
            if (panel.getPrimerBloqueConexion() == null) {
                panel.setPrimerBloqueConexion(bloqueClickeado);
                JOptionPane.showMessageDialog(panel, "Bloque origen seleccionado ('" + bloqueClickeado.getTexto() + "').\nAhora haz clic en el bloque de destino.");
            } else {
                Bloque origen = panel.getPrimerBloqueConexion();
                Bloque destino = bloqueClickeado;

                if (origen == destino) {
                    JOptionPane.showMessageDialog(panel, "No puedes conectar un bloque consigo mismo.");
                } else {
                    String condicion = "";
                    if (origen.getTipo() == Bloque.Tipo.DECISION) {
                        condicion = JOptionPane.showInputDialog(panel, "Condición de la ramificación (ej: Sí / No):", "Sí");
                    }
                    panel.agregarConexion(origen, destino, condicion);
                    JOptionPane.showMessageDialog(panel, "Enlace creado");
                }
                panel.setModoConectar(false); // Apaga el modo conectar tras enlazar
            }
            return;
        }

        panel.setBloqueSeleccionado(bloqueClickeado);

        if (bloqueClickeado != null) {
            if (e.getClickCount() == 2 && SwingUtilities.isLeftMouseButton(e)) {
                String nuevoTexto = JOptionPane.showInputDialog(panel, "Modificar texto del bloque:", bloqueClickeado.getTexto());
                if (nuevoTexto != null && !nuevoTexto.trim().isEmpty()) {
                    bloqueClickeado.setTexto(nuevoTexto);
                    panel.repaint();
                }
                return;
            }

            if (SwingUtilities.isRightMouseButton(e)) {
                mostrarMenuEmergente(e, bloqueClickeado);
                return;
            }

            if (SwingUtilities.isLeftMouseButton(e)) {
                bloqueArrastrado = bloqueClickeado;
                desfase.x = e.getX() - bloqueClickeado.getX();
                desfase.y = e.getY() - bloqueClickeado.getY();
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        bloqueArrastrado = null;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (bloqueArrastrado != null && SwingUtilities.isLeftMouseButton(e)) {
            bloqueArrastrado.setX(e.getX() - desfase.x);
            bloqueArrastrado.setY(e.getY() - desfase.y);
            panel.repaint();
        }
    }

    private void mostrarMenuEmergente(MouseEvent e, Bloque bloque) {
        JPopupMenu menu = new JPopupMenu();
        JMenuItem itemEliminar = new JMenuItem("Eliminar bloque");
        
        itemEliminar.addActionListener(ae -> {
            int respuesta = JOptionPane.showConfirmDialog(panel, 
                "¿Estás seguro de que deseas eliminar este bloque?", 
                "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
            if (respuesta == JOptionPane.YES_OPTION) {
                panel.eliminarBloque(bloque);
            }
        });

        menu.add(itemEliminar);
        menu.show(panel, e.getX(), e.getY());
    }
}
