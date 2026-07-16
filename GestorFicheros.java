/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import modelo.Bloque;
import modelo.Conexion;
import java.io.*;
import java.util.List;

/**
 *
 * @author USUARIO
 */

public class GestorFicheros {

    // Guarda el estado actual del diagrama (bloques y conexiones) en un archivo binario
    public static void guardar(File archivo, List<Bloque> bloques, List<Conexion> conexiones) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(bloques);
            oos.writeObject(conexiones);
        }
    }

    // Lee el archivo y devuelve un objeto que contiene ambas listas
    @SuppressWarnings("unchecked")
    public static DatosDiagrama cargar(File archivo) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            List<Bloque> bloques = (List<Bloque>) ois.readObject();
            List<Conexion> conexiones = (List<Conexion>) ois.readObject();
            return new DatosDiagrama(bloques, conexiones);
        }
    }

    // Clase contenedora auxiliar para transportar ambos datos juntos
    public static class DatosDiagrama {
        public final List<Bloque> bloques;
        public final List<Conexion> conexiones;
        public DatosDiagrama(List<Bloque> b, List<Conexion> c) { this.bloques = b; this.conexiones = c; }
    }
}