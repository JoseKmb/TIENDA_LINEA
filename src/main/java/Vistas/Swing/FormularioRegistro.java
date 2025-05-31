package Vistas.Swing;

import Controladores.Usuario.RegistroCuentaController;
import Modelos.Usuarios.CuentaUsuario;

import javax.swing.*;
import java.awt.*;

public class FormularioRegistro {

    public static void mostrar() {
        JFrame frame = new JFrame("Crear tu cuenta");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Crear tu cuenta", SwingConstants.CENTER);
        titulo.setFont(new Font("Roboto", Font.BOLD, 28));
        titulo.setBorder(BorderFactory.createEmptyBorder(30, 0, 30, 0));
        frame.add(titulo, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridLayout(8, 1, 12, 12));
        form.setBorder(BorderFactory.createEmptyBorder(20, 450, 20, 450));
        form.setBackground(Color.WHITE);

        JTextField idField        = new JTextField();
        JTextField nombreField    = new JTextField();
        JTextField apellidoField  = new JTextField();
        JTextField emailField     = new JTextField();
        JTextField telefonoField  = new JTextField();
        JTextField direccionField = new JTextField();
        JPasswordField passField  = new JPasswordField();

        idField.setBorder(BorderFactory.createTitledBorder("ID de usuario"));
        nombreField.setBorder(BorderFactory.createTitledBorder("Nombre"));
        apellidoField.setBorder(BorderFactory.createTitledBorder("Apellido"));
        emailField.setBorder(BorderFactory.createTitledBorder("Correo"));
        telefonoField.setBorder(BorderFactory.createTitledBorder("Teléfono"));
        direccionField.setBorder(BorderFactory.createTitledBorder("Dirección"));
        passField.setBorder(BorderFactory.createTitledBorder("Contraseña"));

        form.add(idField);
        form.add(nombreField);
        form.add(apellidoField);
        form.add(emailField);
        form.add(telefonoField);
        form.add(direccionField);
        form.add(passField);

        JButton registrar = new JButton("Registrar");
        registrar.setBackground(new Color(255, 230, 0));
        registrar.setFont(new Font("Roboto", Font.BOLD, 20));
        registrar.setFocusPainted(false);

        // ——— Lógica de guardado (ahora con 'activo' = true) ———
        registrar.addActionListener(e -> {
            CuentaUsuario usuario = new CuentaUsuario(
                    idField.getText(),
                    nombreField.getText(),
                    apellidoField.getText(),
                    emailField.getText(),
                    new String(passField.getPassword()),
                    telefonoField.getText(),
                    direccionField.getText(),
                    1,     // Rol = Cliente (id_rol = 1)
                    true   // activo = true
            );

            boolean exito = new RegistroCuentaController().registrarCuenta(usuario);

            if (exito) {
                JOptionPane.showMessageDialog(frame,
                        "✅ Cuenta creada exitosamente",
                        "Registro", JOptionPane.INFORMATION_MESSAGE);
                frame.dispose();
            } else {
                JOptionPane.showMessageDialog(frame,
                        "❌ Error al registrar",
                        "Registro", JOptionPane.ERROR_MESSAGE);
            }
        });

        form.add(registrar);
        frame.add(form, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
