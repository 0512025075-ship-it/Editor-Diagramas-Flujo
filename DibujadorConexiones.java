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
        double angulo = Math.atan2(y2 - y1, x2 - x1);
        int tamaño = 10;
        Polygon punta = new Polygon();
        punta.addPoint(px, py);}
        punta.addPoint((int) (px - tamaño * Math.cos(angulo - Math.PI/6)), (int) (py - tamaño * Math.sin(angulo - Math.PI/6)));
        punta.addPoint((int) (px - tamaño * Math.cos(angulo + Math.PI/6)), (int) (py - tamaño * Math.sin(angulo + Math.PI/6)));
        g2.fillPolygon(punta);
    }
}
