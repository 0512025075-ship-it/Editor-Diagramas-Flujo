/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package vista;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author USUARIO
 */


public class VentanaPrincipal extends JFrame {

    public VentanaPrincipal() {
        setTitle("Editor Visual de Diagramas - Programación II");
        setSize(1180, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 1. Crear el lienzo
        PanelDiagrama panelDiagrama = new PanelDiagrama();
        
        // 2. Crear la barra superior y conectarla al lienzo
        BarraHerramientas barra = new BarraHerramientas(panelDiagrama);

        // 3. Agregar componentes a la ventana
        getContentPane().add(barra, BorderLayout.NORTH);
        getContentPane().add(panelDiagrama, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaPrincipal().setVisible(true));
    }
}