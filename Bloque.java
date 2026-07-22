package modelo;

import java.io.Serializable;

public class Bloque implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public enum Tipo { 
        INICIO, PROCESO, DECISION, SALIDA, FIN 
    }

    private String id;
    private Tipo tipo;
    private String texto;
    private int x, y;
    private int ancho = 120;
    private int alto = 50;

    public Bloque(String id, Tipo tipo, String texto, int x, int y) {
        this.id = id;
        this.tipo = tipo;
        this.texto = texto;
        this.x = x;
        this.y = y;
    }
    
    public String getId() { return id; }
    public Tipo getTipo() { return tipo; }
    public String getTexto() { return texto; }
    public void setTexto(String texto) { this.texto = texto; }
    public int getX() { return x; }
    public void setX(int x) { this.x = x; }
    public int getY() { return y; }
    public void setY(int y) { this.y = y; }
    public int getAncho() { return ancho; }
    public int getAlto() { return alto; }

    public boolean contienePunto(int px, int py) {
        return px >= x && px <= x + ancho && py >= y && py <= y + alto;
    }
}
