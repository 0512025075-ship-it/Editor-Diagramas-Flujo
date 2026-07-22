package controlador;

import modelo.Bloque;
import modelo.Conexion;
import java.util.ArrayList;
import java.util.List;

public class ValidadorDiagrama {

    public static List<String> validar(List<Bloque> bloques, List<Conexion> conexiones) {
        List<String> errores = new ArrayList<>();
        if (bloques.isEmpty()) return List.of("El diagrama está vacío.");

        long inicios = bloques.stream().filter(b -> b.getTipo() == Bloque.Tipo.INICIO).count();
        long fines = bloques.stream().filter(b -> b.getTipo() == Bloque.Tipo.FIN).count();

        if (inicios != 1) errores.add("Debe haber exactamente un bloque de 'Inicio'.");
        if (fines < 1) errores.add("Debe haber al menos un bloque de 'Fin'.");

        for (Bloque b : bloques) {
            boolean tieneConexion = conexiones.stream().anyMatch(c -> c.getOrigen() == b || c.getDestino() == b);
            if (!tieneConexion && bloques.size() > 1) {
                errores.add("El bloque '" + b.getTexto() + "' está aislado.");
            }
        }
        return errores;
    }
}
