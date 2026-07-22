package vista;

import modelo.Bloque;
import javax.swing.JPanel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PanelDiagrama extends JPanel {
    private List<Bloque> bloques = new ArrayList<>();
    private java.util.List<modelo.Conexion> conexiones = new java.util.ArrayList<>();
    private boolean modoConectar = false;
    private Bloque primerBloqueConexion = null;
    private Bloque bloqueSeleccionado = null; 

    public PanelDiagrama() {
        setBackground(Color.WHITE);
        GestorRaton gestor = new GestorRaton(this);
        addMouseListener(gestor);
        addMouseMotionListener(gestor);
        
        
    }

    public void agregarBloque(Bloque b) {
        bloques.add(b);
        bloqueSeleccionado = b; // Lo seleccionamos automáticamente al crearlo
        repaint();
    }

    public void eliminarBloque(Bloque b) {
        bloques.remove(b);
        if (bloqueSeleccionado == b) {
            bloqueSeleccionado = null;
        }
        repaint();
    }

    public void limpiarLienzo() {
        bloques.clear();
        conexiones.clear();//integrante 2
        bloqueSeleccionado = null;
        repaint();
    }

    public List<Bloque> getBloques() { return bloques; }
    public void setBloques(List<Bloque> nuevos) { this.bloques = nuevos; repaint(); }

    public Bloque getBloqueSeleccionado() { return bloqueSeleccionado; }
    public void setBloqueSeleccionado(Bloque b) { this.bloqueSeleccionado = b; repaint(); }
    
    

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        
        for (modelo.Conexion con : conexiones) {
            DibujadorConexiones.dibujar(g2, con);
        }
        
        for (Bloque b : bloques) {
            DibujadorBloques.dibujar(g2, b);
            if (b == getBloqueSeleccionado()) {
                g2.setColor(new Color(0, 120, 215));
                g2.setStroke(new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0));
                g2.drawRoundRect(b.getX() - 4, b.getY() - 4, b.getAncho() + 8, b.getAlto() + 8, 10, 10);
            }
        }
    }
    
    public void agregarConexion(Bloque origen, Bloque destino, String condicion) {
        conexiones.add(new modelo.Conexion(origen, destino, condicion));
        repaint();
    }

    public java.util.List<modelo.Conexion> getConexiones() { return conexiones; }
    public void setConexiones(java.util.List<modelo.Conexion> nuevas) { this.conexiones = nuevas; repaint(); }

    public boolean isModoConectar() { return modoConectar; }
    public void setModoConectar(boolean modoConectar) { 
        this.modoConectar = modoConectar; 
        if(!modoConectar) primerBloqueConexion = null;
    }

    public Bloque getPrimerBloqueConexion() { return primerBloqueConexion; }
    public void setPrimerBloqueConexion(Bloque b) { this.primerBloqueConexion = b; }
    
    
}
