package Vistas.Swing;

import Controladores.Usuario.LoginController;
import Modelos.Usuarios.CuentaUsuario;

import javax.swing.*;
import java.awt.*;

/**
 * Ventana Swing que pide correo y contraseña.
 * Si las credenciales coinciden, abre VistaCliente o VistaAdmin.
 */
public class LoginView extends JFrame {

    private final JTextField txtCorreo  = new JTextField();
    private final JPasswordField txtPwd = new JPasswordField();
    private final JLabel lblEstado      = new JLabel(" ", SwingConstants.CENTER);

    public LoginView() {
        super("Iniciar sesión");
        setSize(400, 260);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        txtCorreo.setBorder(BorderFactory.createTitledBorder("Correo electrónico"));
        txtPwd   .setBorder(BorderFactory.createTitledBorder("Contraseña"));

        JButton btnContinuar = new JButton("Continuar");
        btnContinuar.setBackground(new Color(0x448AFF));
        btnContinuar.setForeground(Color.WHITE);
        btnContinuar.addActionListener(e -> procesarLogin());

        JPanel panel = new JPanel(new GridLayout(4,1,10,10));
        panel.setBorder(BorderFactory.createEmptyBorder(20,40,20,40));
        panel.add(txtCorreo);
        panel.add(txtPwd);
        panel.add(btnContinuar);
        panel.add(lblEstado);

        add(panel);
    }

    private void procesarLogin() {
        String correo = txtCorreo.getText().trim();
        String pass   = new String(txtPwd.getPassword());

        CuentaUsuario u = new LoginController().autenticar(correo, pass);
        if (u == null) {
            lblEstado.setText("❌ Datos incorrectos o usuario inactivo");
            return;
        }

        /* — redirigir según rol — */
        dispose();
        if (u.getIdRol() == 2) {
            new VistaAdmin().setVisible(true);
        } else {
            new VistaCliente(u).setVisible(true);
        }

    }

    /** Utilidad para abrirla fácilmente desde otras vistas */
    public static void mostrar() {
        SwingUtilities.invokeLater(() -> new LoginView().setVisible(true));
    }
}
