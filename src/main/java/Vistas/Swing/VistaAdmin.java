package Vistas.Swing;

import javax.swing.*;
import java.awt.*;

public class VistaAdmin extends JFrame {

    public VistaAdmin() {
        super("Panel de administrador");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JLabel lbl = new JLabel("Bienvenido, administrador",
                SwingConstants.CENTER);
        lbl.setFont(new Font("Roboto", Font.BOLD, 24));

        add(lbl);
    }
}
