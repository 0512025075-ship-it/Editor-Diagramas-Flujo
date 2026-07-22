package controlador;

import modelo.Bloque;
import modelo.Conexion;
import java.io.*;
import java.util.List;

public class GestorFicheros {

    public static void guardar(File archivo, List<Bloque> bloques, List<Conexion> conexiones) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(bloques);
            oos.writeObject(conexiones);
        }
    }

    @SuppressWarnings("unchecked")
    public static DatosDiagrama cargar(File archivo) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            List<Bloque> bloques = (List<Bloque>) ois.readObject();
            List<Conexion> conexiones = (List<Conexion>) ois.readObject();
            return new DatosDiagrama(bloques, conexiones);
        }
    }

    public static class DatosDiagrama {
        public final List<Bloque> bloques;
        public final List<Conexion> conexiones;
        public DatosDiagrama(List<Bloque> b, List<Conexion> c) { this.bloques = b; this.conexiones = c; }
    }
}
