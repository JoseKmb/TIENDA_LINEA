package Vistas;

import java.awt.Desktop;
import java.io.File;
import java.net.URL;

public class VistaCliente {

    public static void abrirCliente() {
        try {
            URL recurso = VistaCliente.class.getClassLoader().getResource("index.html");

            if (recurso == null) {
                System.out.println("No se encontró index.html");
                return;
            }

            File archivo = new File(recurso.toURI());
            Desktop.getDesktop().browse(archivo.toURI());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
