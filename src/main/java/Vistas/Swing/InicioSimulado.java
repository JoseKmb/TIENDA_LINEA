package Vistas.Swing;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import Vistas.Swing.ProductosPanel;

public class InicioSimulado extends JFrame {

    private static final Color AMARILLO_ML    = new Color(0xFFE600);
    private static final Font  FONT_BUSCADOR  = new Font("Roboto", Font.PLAIN, 14);
    private static final Font  FONT_MENU      = new Font("Roboto", Font.PLAIN, 14);
    private static final Font  FONT_LINK      = new Font("Roboto", Font.PLAIN, 13);
    private static final Font  FONT_TITULO    = new Font("Roboto", Font.BOLD, 26);

    public InicioSimulado() {
        super("Mercado Libre – Simulación");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);

        // ------------------ CABECERA (fila1 + fila2) ------------------
        JPanel panelCabecera = new JPanel();
        panelCabecera.setLayout(new BoxLayout(panelCabecera, BoxLayout.Y_AXIS));
        panelCabecera.setBackground(AMARILLO_ML);

        // FILA 1: Logo + buscador + enlaces
        JPanel fila1 = new JPanel(new BorderLayout());
        fila1.setOpaque(false);
        fila1.setBorder(BorderFactory.createEmptyBorder(8, 10, 4, 10));
        JLabel lblLogo = new JLabel();
        URL urlLogo = getClass().getClassLoader()
                .getResource("img/mercado-libre-logo-png_seeklogo-284500.png");
        if (urlLogo != null) {
            ImageIcon icon = new ImageIcon(urlLogo);
            Image imgRaw = icon.getImage();
            Image imgEsc = imgRaw.getScaledInstance(-1, 60, Image.SCALE_SMOOTH);
            lblLogo.setIcon(new ImageIcon(imgEsc));
        } else {
            lblLogo.setText("<html><b>mercado libre</b></html>");
            lblLogo.setFont(new Font("Roboto", Font.BOLD, 20));
        }
        lblLogo.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));
        fila1.add(lblLogo, BorderLayout.WEST);

        JTextField txtBuscar = new JTextField();
        txtBuscar.setFont(FONT_BUSCADOR);
        txtBuscar.setPreferredSize(new Dimension(400, 30));
        txtBuscar.setMaximumSize(new Dimension(400, 30));
        txtBuscar.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        fila1.add(txtBuscar, BorderLayout.CENTER);

        JPanel panelLinks = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 0));
        panelLinks.setOpaque(false);
        JLabel linkCrear      = crearLink("Crea tu cuenta");
        JLabel linkIngresa    = crearLink("Ingresa");
        JLabel linkMisCompras = crearLink("Mis compras");
        JLabel iconCarrito    = crearLink("\uD83D\uDED2");
        panelLinks.add(linkCrear);
        panelLinks.add(linkIngresa);
        panelLinks.add(linkMisCompras);
        panelLinks.add(iconCarrito);
        fila1.add(panelLinks, BorderLayout.EAST);

        // FILA 2: Menú categorías
        JPanel fila2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        fila2.setOpaque(false);
        fila2.setBorder(BorderFactory.createEmptyBorder(0, 10, 8, 10));
        String[] itemsMenu = {
                "Categorías", "Ofertas", "Cupones", "Supermercado",
                "Moda", "Mercado Play", "Vender", "Ayuda"
        };
        for (String texto : itemsMenu) {
            JLabel lblMenu = new JLabel(
                    "<html><span style='color: #000000;'>" + texto + "</span></html>"
            );
            lblMenu.setFont(FONT_MENU);
            lblMenu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            fila2.add(lblMenu);
        }

        panelCabecera.add(fila1);
        panelCabecera.add(fila2);

        setLayout(new BorderLayout());
        add(panelCabecera, BorderLayout.NORTH);

        // ------------------ PANEL CENTRAL: anuncios + productos + bienvenida ------------------
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));

        // Carrusel de anuncios (igual que antes)
        JPanel panelAnuncios = new JPanel(new BorderLayout());
        panelAnuncios.setBackground(Color.WHITE);
        panelAnuncios.setPreferredSize(new Dimension(0, 250));
        panelAnuncios.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));

        List<String> rutasAnuncios = List.of(
                "img/Anuncio1.png",
                "img/Anuncio2.png",
                "img/Anuncio3.png",
                "img/Anuncio4.png",
                "img/Anuncio5.png"
        );
        JLabel etiquetaAnuncio = new JLabel();
        etiquetaAnuncio.setHorizontalAlignment(SwingConstants.CENTER);
        etiquetaAnuncio.setVerticalAlignment(SwingConstants.CENTER);
        panelAnuncios.add(etiquetaAnuncio, BorderLayout.CENTER);

        List<ImageIcon> iconsAnuncios = new ArrayList<>();
        for (String ruta : rutasAnuncios) {
            URL url = getClass().getClassLoader().getResource(ruta);
            if (url != null) {
                ImageIcon original = new ImageIcon(url);
                Image img = original.getImage();
                int altoDeseado = 250;
                int anchoPropor  = (int) ((double) original.getIconWidth()
                        / original.getIconHeight() * altoDeseado);
                Image imgEscalado = img.getScaledInstance(
                        anchoPropor, altoDeseado, Image.SCALE_SMOOTH
                );
                iconsAnuncios.add(new ImageIcon(imgEscalado));
            }
        }
        if (iconsAnuncios.isEmpty()) {
            etiquetaAnuncio.setText("No hay anuncios disponibles");
        } else {
            Timer timer = new Timer(3000, null);
            timer.addActionListener(e -> {
                int idx = (int) (Math.random() * iconsAnuncios.size());
                etiquetaAnuncio.setIcon(iconsAnuncios.get(idx));
            });
            timer.setInitialDelay(0);
            timer.start();
        }
        panelCentral.add(panelAnuncios);

        // AQUI agregamos la nueva clase que maneja todo el listado de productos:
        ProductosPanel productosPanel = new ProductosPanel();
        panelCentral.add(productosPanel);

        // Texto de bienvenida
        JLabel lblBienvenida = new JLabel(
                "<html><div style='text-align: center;'>" +
                        "<h1>Bienvenido a Mercado Libre</h1>" +
                        "</div></html>",
                SwingConstants.CENTER
        );
        lblBienvenida.setFont(FONT_TITULO);
        lblBienvenida.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        panelCentral.add(lblBienvenida);

        add(panelCentral, BorderLayout.CENTER);

        // ------------------ LISTENERS de enlaces ------------------
        linkCrear.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override public void mouseClicked(java.awt.event.MouseEvent e) {
                Vistas.Swing.FormularioRegistro.mostrar();
            }
        });
        linkIngresa.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override public void mouseClicked(java.awt.event.MouseEvent e) {
                try {
                    Vistas.Swing.LoginView.mostrar();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(
                            InicioSimulado.this,
                            "Error al abrir Login:\n" + ex.getMessage(),
                            "Login", JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });
        linkMisCompras.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override public void mouseClicked(java.awt.event.MouseEvent e) {
                JOptionPane.showMessageDialog(
                        InicioSimulado.this,
                        "El módulo 'Mis compras' aún no está implementado.",
                        "Mis Compras", JOptionPane.INFORMATION_MESSAGE
                );
            }
        });
        iconCarrito.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override public void mouseClicked(java.awt.event.MouseEvent e) {
                JOptionPane.showMessageDialog(
                        InicioSimulado.this,
                        "El carrito aún no está implementado.",
                        "Carrito", JOptionPane.INFORMATION_MESSAGE
                );
            }
        });

        setVisible(true);
    }

    private static JLabel crearLink(String texto) {
        JLabel lbl = new JLabel(
                "<html><span style='color: #0000EE; text-decoration: none;'>" +
                        texto + "</span></html>"
        );
        lbl.setFont(FONT_LINK);
        lbl.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return lbl;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(InicioSimulado::new);
    }
}
