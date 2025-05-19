package Vistas;

import java.awt.Desktop;
import java.io.File;
import java.net.URL;

public class Inicio {
    public static void main(String[] args) {
        try {
            URL recurso = Inicio.class.getClassLoader().getResource("menu.html");

            if (recurso == null) {
                System.out.println("No se encontró el archivo menu.html");
                return;
            }

            File archivo = new File(recurso.toURI());
            Desktop.getDesktop().browse(archivo.toURI());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
