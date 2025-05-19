package Vistas;

import java.awt.Desktop;
import java.io.File;
import java.net.URL;

public class VistaAdmin {

    public static void abrirAdmin() {
        try {
            URL recurso = VistaAdmin.class.getClassLoader().getResource("admin.html");

            if (recurso == null) {
                System.out.println("No se encontró admin.html");
                return;
            }

            File archivo = new File(recurso.toURI());
            Desktop.getDesktop().browse(archivo.toURI());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
