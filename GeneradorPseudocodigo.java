/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import modelo.Bloque;
import modelo.Conexion;
import java.util.List;
/**
 *
 * @author USUARIO
 */

public class GeneradorPseudocodigo {

    public static String generar(List<Bloque> bloques, List<Conexion> conexiones) {
        // Buscamos el bloque de inicio para arrancar el recorrido
        Bloque actual = bloques.stream()
                .filter(b -> b.getTipo() == Bloque.Tipo.INICIO)
                .findFirst()
                .orElse(null);

        if (actual == null) return "Error: No se encontró un bloque de 'Inicio'.";

        StringBuilder sb = new StringBuilder();
        sb.append("ALGORITMO GeneradoAutomaticamente\n");

        // Recorremos el diagrama de flujo de bloque en bloque siguiendo las conexiones
        while (actual != null) {
            switch (actual.getTipo()) {
                case INICIO -> sb.append("INICIO\n");
                case PROCESO -> sb.append("    ").append(actual.getTexto()).append("\n");
                case SALIDA -> sb.append("    Escribir \"").append(actual.getTexto()).append("\"\n");
                case DECISION -> {
                    sb.append("    SI ").append(actual.getTexto()).append(" ENTONCES\n");
                    // Opcionalmente se pueden programar las bifurcaciones Sí/No aquí en futuras versiones
                }
                case FIN -> {
                    sb.append("FIN\n");
                    actual = null; // Detiene el bucle al llegar al Fin
                    continue; 
                }
            }

            // Buscamos el siguiente bloque conectado a la salida del bloque actual
            final Bloque dePaso = actual;
            actual = conexiones.stream()
                    .filter(c -> c.getOrigen() == dePaso)
                    .map(Conexion::getDestino)
                    .findFirst()
                    .orElse(null);
        }

        return sb.toString();
    }
}