//Proporciona un menú de botones contextuales en la parte superior.
package vista;

import modelo.Bloque;
import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Font;

public class BarraHerramientas extends JPanel {
    private int contador = 0;

    public BarraHerramientas(PanelDiagrama panel) {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        
        add(new JLabel("Añadir: "));
        crearBoton("Inicio", Bloque.Tipo.INICIO, panel);
        crearBoton("Proceso", Bloque.Tipo.PROCESO, panel);
        crearBoton("Decisión", Bloque.Tipo.DECISION, panel);
        crearBoton("Salida", Bloque.Tipo.SALIDA, panel);
        crearBoton("Fin", Bloque.Tipo.FIN, panel);
        
        add(new JToolBar.Separator());

        JButton btnEnlazar = new JButton("Enlazar Flujo");
        btnEnlazar.addActionListener(e -> {
            if (panel.getBloques().size() < 2) {
                JOptionPane.showMessageDialog(this, "Necesitas al menos 2 bloques para enlazar.");
                return;
            }
            panel.setModoConectar(true);
            JOptionPane.showMessageDialog(this, "Modo Enlace.\nHaz clic en el bloque de origen.");
        });
        add(btnEnlazar);

        JButton btnValidar = new JButton("Validar");
        btnValidar.addActionListener(e -> {
            
            var errores = controlador.ValidadorDiagrama.validar(panel.getBloques(), panel.getConexiones());
            if (errores.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No hay errores lógicos en tu diagrama.");
            } else {
                JOptionPane.showMessageDialog(this, String.join("\n", errores), "Errores encontrados", JOptionPane.WARNING_MESSAGE);
            }
        });
        add(btnValidar);
        add(new JToolBar.Separator());

        JButton btnGuardar = new JButton("Guardar Proyecto");
        btnGuardar.addActionListener(e -> {
            JFileChooser fc = new JFileChooser();
            if (fc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                try {
                    controlador.GestorFicheros.guardar(fc.getSelectedFile(), panel.getBloques(), panel.getConexiones());
                    JOptionPane.showMessageDialog(this, "¡Proyecto guardado con éxito!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error al guardar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        add(btnGuardar);

        JButton btnCargar = new JButton("Cargar Proyecto");
        btnCargar.addActionListener(e -> {
            JFileChooser fc = new JFileChooser();
            if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                try {
                    var datos = controlador.GestorFicheros.cargar(fc.getSelectedFile());
                    panel.setBloques(datos.bloques);
                    panel.setConexiones(datos.conexiones);
                    JOptionPane.showMessageDialog(this, "¡Proyecto cargado con éxito!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error al cargar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        add(btnCargar);

        JButton btnPseudocodigo = new JButton("Generar Pseudocódigo");
        btnPseudocodigo.addActionListener(e -> {
            var errores = controlador.ValidadorDiagrama.validar(panel.getBloques(), panel.getConexiones());
            if (!errores.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No se puede generar pseudocódigo:\n" + String.join("\n", errores), "Error de Estructura", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String pseudocodigo = controlador.GeneradorPseudocodigo.generar(panel.getBloques(), panel.getConexiones());
            JTextArea areaTexto = new JTextArea(pseudocodigo);
            areaTexto.setEditable(false);
            areaTexto.setFont(new Font("Monospaced", Font.PLAIN, 12));
            JScrollPane scroll = new JScrollPane(areaTexto);
            scroll.setPreferredSize(new Dimension(400, 300));
            
            JOptionPane.showMessageDialog(this, scroll, "Pseudocódigo Generado", JOptionPane.INFORMATION_MESSAGE);
        });
        add(btnPseudocodigo);
       
        JSeparator separador = new JSeparator(JSeparator.VERTICAL);
        add(separador);

        JButton btnLimpiar = new JButton("Limpiar Todo");
        btnLimpiar.addActionListener(e -> {
            int respuesta = JOptionPane.showConfirmDialog(this, 
                "¿Deseas borrar todo el diagrama actual?", 
                "Limpiar Lienzo", JOptionPane.YES_NO_OPTION);
            if (respuesta == JOptionPane.YES_OPTION) {
                panel.limpiarLienzo();
            }
        });
        add(btnLimpiar);
    }

    private void crearBoton(String nombre, Bloque.Tipo tipo, PanelDiagrama panel) {
        JButton boton = new JButton(nombre);
        boton.addActionListener(e -> {
            String texto = JOptionPane.showInputDialog(this, "Texto del bloque:", nombre);
            if (texto != null && !texto.trim().isEmpty()) {
                contador++;
                int offset = (contador % 5) * 15; 
                panel.agregarBloque(new Bloque("B" + contador, tipo, texto, 100 + offset, 100 + offset));
            }
        });
        add(boton);
    }
}
