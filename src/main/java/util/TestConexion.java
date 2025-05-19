package util;

import util.ConexionBD;

import java.sql.Connection;
import java.sql.SQLException;

public class TestConexion {
    public static void main(String[] args) {
        Connection conn = ConexionBD.getInstancia().getConexion();

        if (conn != null) {
            System.out.println("✅ Conexión exitosa a la base de datos.");
            try {
                if (!conn.isClosed()) {
                    System.out.println("🔁 La conexión está abierta y funcionando.");
                }
            } catch (SQLException e) {
                System.err.println("❌ Error al verificar conexión: " + e.getMessage());
            }
        } else {
            System.err.println("❌ No se pudo establecer la conexión.");
        }
    }
}

