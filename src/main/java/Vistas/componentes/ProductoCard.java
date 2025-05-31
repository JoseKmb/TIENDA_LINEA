package Vistas.componentes;

import Modelos.Catalogo.Producto;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * Tarjeta 170×230 que muestra foto, nombre y precio.
 */
public abstract class ProductoCard extends JPanel {

    private static final String RUTA_PLACEHOLDER = "img/placeholder.png";
    protected final Producto prod;

    protected ProductoCard(Producto p) {
        this.prod = p;
        setPreferredSize(new Dimension(170, 230));
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
    }

    /* --- Factory --- */
    public static ProductoCard of(Producto p) {       // solo una variante simple
        return new CardBasica(p);
    }

    /* --- Implementación básica --- */
    private static class CardBasica extends ProductoCard {
        CardBasica(Producto p) {
            super(p);
            add(crearImagen(p.getRutaImagen()), BorderLayout.NORTH);
            add(etiqueta(p.getNombre(), Font.PLAIN, 13), BorderLayout.CENTER);
            add(etiqueta("$" + p.getPrecio(), Font.BOLD, 14), BorderLayout.SOUTH);
        }
    }

    /* --- Helpers visuales --- */
    private static JLabel etiqueta(String t, int style, int size) {
        JLabel l = new JLabel(t, SwingConstants.CENTER);
        l.setFont(new Font("Roboto", style, size));
        return l;
    }

    /**
     * Busca la imagen en dos pasos:
     *   1) En el class-path (`src/main/resources`) con getResource("img/...").
     *   2) Como archivo local (ruta absoluta o relativa a donde se ejecute el jar).
     * Si falla, usa un placeholder.
     */
    private static JLabel crearImagen(String rutaEnBD) {
        final int W = 150, H = 150;
        try {
            ImageIcon icon;

            /* Paso 1: resources (build/classes/img/ ...) */
            URL url = ProductoCard.class.getClassLoader().getResource(rutaEnBD);
            if (url != null) {
                icon = new ImageIcon(url);
            } else {
                /* Paso 2: archivo local */
                java.io.File f = new java.io.File(rutaEnBD);
                if (f.exists()) {
                    icon = new ImageIcon(rutaEnBD);
                } else {
                    // placeholder desde resources
                    URL ph = ProductoCard.class.getClassLoader()
                            .getResource(RUTA_PLACEHOLDER);
                    icon = new ImageIcon(ph);
                }
            }

            Image img = icon.getImage().getScaledInstance(W, H,
                    Image.SCALE_SMOOTH);
            return new JLabel(new ImageIcon(img), SwingConstants.CENTER);

        } catch (Exception e) {
            return new JLabel("Sin imagen", SwingConstants.CENTER);
        }
    }
}
