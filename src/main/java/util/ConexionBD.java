package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Singleton que administra la conexión JDBC a MySQL para 'sistema_ventas'.
 */
public class ConexionBD {

    private static ConexionBD instancia;
    private Connection conexion;

    // — Ajusta estos valores a tu servidor / base de datos / credenciales —
    private final String url       = "jdbc:mysql://localhost:3306/sistema_ventas?useSSL=false&serverTimezone=UTC";
    private final String usuario   = "root";
    private final String contrasena = "Hola123";

    private ConexionBD() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Opcional con Connector/J 8+
            conexion = DriverManager.getConnection(url, usuario, contrasena);
            System.out.println("✅ Conexión a base de datos establecida correctamente.");
        } catch (ClassNotFoundException e) {
            System.err.println("❌ No se encontró el driver de MySQL: " + e.getMessage());
            conexion = null;
        } catch (SQLException e) {
            System.err.println("❌ Error al conectar con la base de datos: " + e.getMessage());
            conexion = null;
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
