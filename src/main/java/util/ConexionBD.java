package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    private static ConexionBD instancia;
    private Connection conexion;

    // Ajusta estos valores a los tuyos
    private final String url = "jdbc:mysql://127.0.0.1:3306/sistema_ventas?useSSL=false&serverTimezone=UTC";
    private final String usuario = "root";
    private final String contrasena = "Hola123";

    private ConexionBD() {
        try {
            conexion = DriverManager.getConnection(url, usuario, contrasena);
        } catch (SQLException e) {
            System.err.println("Error al conectar con la base de datos: " + e.getMessage());
        }
    }

    public static ConexionBD getInstancia() {
        if (instancia == null) {
            instancia = new ConexionBD();
        }
        return instancia;
    }

    public Connection getConexion() {
        return conexion;
    }
}
