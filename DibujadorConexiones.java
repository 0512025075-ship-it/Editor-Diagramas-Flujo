//Clase utilitaria dedicada al renderizado de las líneas y puntas de flechas.
package vista;

import modelo.Bloque;
import modelo.Conexion;
import java.awt.*;

public class DibujadorConexiones {

    public static void dibujar(Graphics2D g2, Conexion con) {
        Bloque o = con.getOrigen();
        Bloque d = con.getDestino();

        int x1 = o.getX() + o.getAncho() / 2;
        int y1 = o.getY() + o.getAlto() / 2;
        int x2 = d.getX() + d.getAncho() / 2;
        int y2 = d.getY() + d.getAlto() / 2;

        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(2));
        g2.drawLine(x1, y1, x2, y2);

        int mx = (x1 + x2) / 2;
        int my = (y1 + y2) / 2;
        dibujarFlecha(g2, x1, y1, x2, y2, mx, my);

        if (con.getCondicion() != null && !con.getCondicion().isEmpty()) {
            g2.setColor(new Color(0, 102, 204));
            g2.drawString(con.getCondicion(), mx + 10, my - 5);
        }
    }

    private static void dibujarFlecha(Graphics2D g2, int x1, int y1, int x2, int y2, int px, int py) {
    double dx = x2 - x1;
    double dy = y2 - y1;
    double largoLinea = Math.hypot(dx, dy); 
    
    if (largoLinea == 0) return; 

    double ux = dx / largoLinea;
    double uy = dy / largoLinea;

    double vx = -uy;
    double vy = ux;

    int largoPunta = 10; 
    int anchoPunta = 5; 
    
    int xAla1 = (int) (px - largoPunta * ux + anchoPunta * vx);
    int yAla1 = (int) (py - largoPunta * uy + anchoPunta * vy);

    int xAla2 = (int) (px - largoPunta * ux - anchoPunta * vx);
    int yAla2 = (int) (py - largoPunta * uy - anchoPunta * vy);

    Polygon punta = new Polygon();
    punta.addPoint(px, py);
    punta.addPoint(xAla1, yAla1);
    punta.addPoint(xAla2, yAla2);

    g2.fillPolygon(punta);
    }
}
