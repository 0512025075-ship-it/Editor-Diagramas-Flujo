package modelo;

import java.io.Serializable;

public class Conexion implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private final Bloque origen;
    private final Bloque destino;
    private String condicion; 

    public Conexion(Bloque origen, Bloque destino, String condicion) {
        this.origen = origen;
        this.destino = destino;
        this.condicion = condicion;
    }

    public Bloque getOrigen() { return origen; }
    public Bloque getDestino() { return destino; }
    public String getCondicion() { return condicion; }
    public void setCondicion(String condicion) { this.condicion = condicion; }
}
