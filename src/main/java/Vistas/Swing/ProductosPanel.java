package Vistas.Swing;

import Controladores.Catalogo.CatalogoController;
import Modelos.Catalogo.Producto;
import Vistas.componentes.ProductoCard;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Panel reutilizable que pinta los productos activos del catálogo.
 *
 *  • No requiere modificar el resto de la IU: simplemente haz
 *      add(new ProductosPanel())  donde quieras que aparezca el grid.
 *
 *  • Usa el patrón callback que ya implementa tu CatalogoController:
 *      new CatalogoController(Consumer<List<Producto>>)
 *    con lo cual se mantiene el flujo MVC que ya tenías.
 */
public class ProductosPanel extends JPanel {

    private final CatalogoController ctrl;

    public ProductosPanel() {
        /* ---- apariencia general del grid ---- */
        super(new FlowLayout(FlowLayout.LEFT, 20, 20));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        /* ---- controlador: se le pasa el callback 'this::pintarProductos' ---- */
        ctrl = new CatalogoController(this::pintarProductos);

        /* ---- pedimos al controlador «todos los productos» (cat = 0, texto vacío) ---- */
        ctrl.filtrar(0, "");
    }

    /* ------------------------------------------------------------------
     *  CALLBACK desde CatalogoController
     * ------------------------------------------------------------------ */
    private void pintarProductos(List<Producto> lista) {
        removeAll();

        if (lista.isEmpty()) {
            JLabel sin = new JLabel("No hay productos disponibles");
            sin.setFont(new Font("Roboto", Font.ITALIC, 16));
            sin.setForeground(Color.DARK_GRAY);
            add(sin);
        } else {
            lista.stream()                 // cada Producto →
                    .filter(p -> p.getStock() > 0)          // opcional: sólo con stock
                    .forEach(p -> add(ProductoCard.of(p))); // reutiliza tu componente Card
        }

        revalidate();
        repaint();
    }
}
