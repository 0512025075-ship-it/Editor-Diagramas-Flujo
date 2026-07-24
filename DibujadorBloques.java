//Clase utilitaria dedicada al renderizado de las figuras geométricas de cada bloque.
package vista;

import modelo.Bloque;
import java.awt.*;

public class DibujadorBloques {

    public static void dibujar(Graphics2D g2, Bloque b) {
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // 1. Pintar el fondo (Si está en ejecución por el hilo, se pinta de amarillo)
        if (b.isEjecutando()) {
            g2.setColor(Color.YELLOW); 
        } else {
            g2.setColor(obtenerColor(b.getTipo()));
        }

        if (b.getTipo() == Bloque.Tipo.DECISION) {
            g2.fillPolygon(crearRombo(b));
        } else if (b.getTipo() == Bloque.Tipo.INICIO || b.getTipo() == Bloque.Tipo.FIN) {
            g2.fillRoundRect(b.getX(), b.getY(), b.getAncho(), b.getAlto(), 25, 25);
        } else {
            g2.fillRect(b.getX(), b.getY(), b.getAncho(), b.getAlto());
        }

        // 2. Dibujar el borde (Rojo si está ejecutando)
        if (b.isEjecutando()) {
            g2.setColor(Color.RED);
            g2.setStroke(new BasicStroke(4));
        } else {
            g2.setColor(Color.BLACK);
            g2.setStroke(new BasicStroke(2));
        }

        if (b.getTipo() == Bloque.Tipo.DECISION) {
            g2.drawPolygon(crearRombo(b));
        } else if (b.getTipo() == Bloque.Tipo.INICIO || b.getTipo() == Bloque.Tipo.FIN) {
            g2.drawRoundRect(b.getX(), b.getY(), b.getAncho(), b.getAlto(), 25, 25);
        } else {
            g2.drawRect(b.getX(), b.getY(), b.getAncho(), b.getAlto());
        }

        //Dibujar el texto
        g2.setColor(Color.BLACK);
        FontMetrics fm = g2.getFontMetrics();
        int textoX = b.getX() + (b.getAncho() - fm.stringWidth(b.getTexto())) / 2;
        int textoY = b.getY() + ((b.getAlto() - fm.getHeight()) / 2) + fm.getAscent();
        g2.drawString(b.getTexto(), textoX, textoY);
    }

    private static Color obtenerColor(Bloque.Tipo tipo) {
        return switch (tipo) {
            case INICIO, FIN -> new Color(144, 238, 144); 
            case PROCESO -> new Color(173, 216, 230);    
            case DECISION -> new Color(255, 204, 153);
            case SALIDA -> new Color(255, 255, 153);   
        };
    }

    private static Polygon crearRombo(Bloque b) {
        Polygon rombo = new Polygon();
        rombo.addPoint(b.getX() + b.getAncho() / 2, b.getY());
        rombo.addPoint(b.getX() + b.getAncho(), b.getY() + b.getAlto() / 2);
        rombo.addPoint(b.getX() + b.getAncho() / 2, b.getY() + b.getAlto());
        rombo.addPoint(b.getX(), b.getY() + b.getAlto() / 2);
        return rombo;
    }
}
