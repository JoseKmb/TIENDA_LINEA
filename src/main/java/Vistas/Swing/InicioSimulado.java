package Vistas.Swing;

import javax.swing.*;
import java.awt.*;

/**
 * Ventana principal de la simulación de Mercado Libre (Swing).
 * Muestra una barra amarilla con logo, buscador y enlaces (“Crea tu cuenta”,
 * “Ingresa”, “Mis compras”, carrito). Los enlaces abren otras vistas.
 */
public class InicioSimulado extends JFrame {

    private static final Color AMARILLO_ML = new Color(0xFFE600);
    private static final Font FONT_LINK = new Font("Roboto", Font.PLAIN, 14);

    public InicioSimulado() {
        setTitle("Mercado Libre – Simulación");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);

        /* ============= BARRA AMARILLA ============= */
        JPanel barra = new JPanel();
        barra.setBackground(AMARILLO_ML);
        barra.setLayout(new BoxLayout(barra, BoxLayout.X_AXIS));
        barra.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));

        // Logo
        JLabel logo = new JLabel("mercado libre");
        logo.setFont(new Font("Roboto", Font.BOLD, 18));
        logo.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 25));

        // Buscador con tamaño fijo para no tapar enlaces
        JTextField buscador = new JTextField();
        buscador.setMaximumSize(new Dimension(400, 30));
        buscador.setPreferredSize(new Dimension(400, 30));
        buscador.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 2));

        // Panel de enlaces a la derecha
        JPanel enlaces = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 0));
        enlaces.setOpaque(false);
        JLabel crea = link("Crea tu cuenta");
        JLabel login = link("Ingresa");
        JLabel compras = link("Mis compras");
        JLabel carrito = link("\uD83D\uDED2"); // 🛒
        enlaces.add(crea);
        enlaces.add(login);
        enlaces.add(compras);
        enlaces.add(carrito);

        // Montaje de la barra
        barra.add(logo);
        barra.add(buscador);
        barra.add(Box.createHorizontalGlue());
        barra.add(enlaces);

        /* ============= CONTENIDO CENTRAL ============= */
        JLabel bienv = new JLabel("Bienvenido a Mercado Libre", SwingConstants.CENTER);
        bienv.setFont(new Font("Roboto", Font.BOLD, 28));

        setLayout(new BorderLayout());
        add(barra, BorderLayout.NORTH);
        add(bienv, BorderLayout.CENTER);

        /* ============= LISTENERS ============= */
        crea.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                // Abre el formulario de registro (clase FormularioRegistro)
                Vistas.Swing.FormularioRegistro.mostrar();
            }
        });

        login.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                // Abre la ventana de login (clase LoginView)
                try {
                    Vistas.Swing.LoginView.mostrar();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(
                            InicioSimulado.this,
                            "Ocurrió un error al abrir el login:\n" + ex.getMessage(),
                            "Login", JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });

        compras.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                JOptionPane.showMessageDialog(
                        InicioSimulado.this,
                        "Módulo 'Mis compras' pendiente",
                        "Mis Compras",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        });

        carrito.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                JOptionPane.showMessageDialog(
                        InicioSimulado.this,
                        "Carrito aún no implementado",
                        "Carrito",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        });

        setVisible(true);
    }

    /**
     * Crea un JLabel con estilo de enlace clicable.
     */
    private static JLabel link(String texto) {
        JLabel l = new JLabel(texto);
        l.setFont(FONT_LINK);
        l.setForeground(Color.BLUE.darker());
        l.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return l;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(InicioSimulado::new);
    }
}
