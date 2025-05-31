package Vistas.Swing;

import Modelos.Usuarios.CuentaUsuario;

import javax.swing.*;
import java.awt.*;

public class VistaCliente extends JFrame {

    public VistaCliente(CuentaUsuario usuario) {
        super("Panel de cliente");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JLabel lbl = new JLabel("¡Hola, " + usuario.getNombre() + "!",
                SwingConstants.CENTER);
        lbl.setFont(new Font("Roboto", Font.BOLD, 24));

        add(lbl);
    }
}

