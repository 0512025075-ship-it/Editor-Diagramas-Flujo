package controlador;

import modelo.Bloque;
import modelo.Conexion;
import java.util.List;

public class GeneradorPseudocodigo {

    public static String generar(List<Bloque> bloques, List<Conexion> conexiones) {
        Bloque actual = bloques.stream()
                .filter(b -> b.getTipo() == Bloque.Tipo.INICIO)
                .findFirst()
                .orElse(null);

        if (actual == null) return "Error: No se encontró un bloque de 'Inicio'.";

        StringBuilder sb = new StringBuilder();
        sb.append("ALGORITMO GeneradoAutomaticamente\n");

        while (actual != null) {
            switch (actual.getTipo()) {
                case INICIO -> sb.append("INICIO\n");
                case PROCESO -> sb.append("    ").append(actual.getTexto()).append("\n");
                case SALIDA -> sb.append("    Escribir \"").append(actual.getTexto()).append("\"\n");
                case DECISION -> {
                    sb.append("    SI ").append(actual.getTexto()).append(" ENTONCES\n");
                }
                case FIN -> {
                    sb.append("FIN\n");
                    actual = null; 
                    continue; 
                }
            }
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
