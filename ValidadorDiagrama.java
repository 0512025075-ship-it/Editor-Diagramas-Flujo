/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import modelo.Bloque;
import modelo.Conexion;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USUARIO
 */

public class ValidadorDiagrama {

    public static List<String> validar(List<Bloque> bloques, List<Conexion> conexiones) {
        List<String> errores = new ArrayList<>();
        if (bloques.isEmpty()) return List.of("El diagrama está vacío.");

        long inicios = bloques.stream().filter(b -> b.getTipo() == Bloque.Tipo.INICIO).count();
        long fines = bloques.stream().filter(b -> b.getTipo() == Bloque.Tipo.FIN).count();

        if (inicios != 1) errores.add("Debe haber exactamente un bloque de 'Inicio'.");
        if (fines < 1) errores.add("Debe haber al menos un bloque de 'Fin'.");

        // Validar bloques desconectados (flotantes)
        for (Bloque b : bloques) {
            boolean tieneConexion = conexiones.stream().anyMatch(c -> c.getOrigen() == b || c.getDestino() == b);
            if (!tieneConexion && bloques.size() > 1) {
                errores.add("El bloque '" + b.getTexto() + "' está aislado.");
            }
        }
        return errores;
    }
}