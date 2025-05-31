package Vistas.Swing;

import Controladores.Catalogo.CatalogoController;
import Modelos.Catalogo.Categoria;
import Modelos.Catalogo.Producto;
import Vistas.componentes.ProductoCard;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CatalogoView extends JFrame {

    private final CatalogoController ctrl;
    private final JPanel grid = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));
    private final JComboBox<Categoria> cboCat;
    private final JTextField txtBuscar = new JTextField(20);

    public CatalogoView() {
        super("Catálogo de productos");
        ctrl = new CatalogoController(this::pintarProductos);

        /* ---------- barra de filtros ---------- */
        cboCat = new JComboBox<>();
        cboCat.addItem(new Categoria(0, "Todas", ""));
        ctrl.cargarCategorias().forEach(cboCat::addItem);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(e -> refrescar());

        JPanel barra = new JPanel();
        barra.add(new JLabel("Categoría:")); barra.add(cboCat);
        barra.add(new JLabel("Buscar:"));    barra.add(txtBuscar);
        barra.add(btnBuscar);

        /* ---------- grid scroll ---------- */
        grid.setBackground(Color.WHITE);
        JScrollPane scroll = new JScrollPane(grid);

        /* ---------- frame ---------- */
        setLayout(new BorderLayout());
        add(barra, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);

        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        refrescar();          // carga inicial
    }

    private void refrescar() {
        int idCat = ((Categoria) cboCat.getSelectedItem()).getId();
        String q  = txtBuscar.getText().trim();
        ctrl.filtrar(idCat, q);
    }

    /* ---------- callback de controlador ---------- */
    private void pintarProductos(List<Producto> lista) {
        grid.removeAll();
        if (lista.isEmpty()) {
            grid.add(new JLabel("Sin resultados"));
        } else {
            lista.stream()
                    .map(ProductoCard::of)
                    .forEach(grid::add);
        }
        grid.revalidate();
        grid.repaint();
    }

    /* --- para probar de forma independiente --- */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CatalogoView().setVisible(true));
    }
}
