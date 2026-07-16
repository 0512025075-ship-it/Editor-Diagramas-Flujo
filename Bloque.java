/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.Serializable;

/**
 *
 * @author USUARIO
 */
public class Bloque implements Serializable {
    private static final long serialVersionUID = 1L;
    
    // Enumerador para distinguir el propósito de cada bloque
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

    // Getters y Setters para que tus compañeros puedan interactuar con tus bloques
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

    /**
     * Verifica si el puntero del mouse hizo clic dentro de los límites de este bloque.
     * Esencial para el arrastre (Drag and Drop).
     */
    public boolean contienePunto(int px, int py) {
        return px >= x && px <= x + ancho && py >= y && py <= y + alto;
    }
}
