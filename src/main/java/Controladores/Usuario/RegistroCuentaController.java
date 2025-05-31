package Controladores.Usuario;

import Modelos.Usuarios.CuentaUsuario;
import Repository.UsuarioRepository;
import util.ConexionBD;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Controlador para registrar una cuenta de usuario.
 */
public class RegistroCuentaController {

    public boolean registrarCuenta(CuentaUsuario usuario) {
        Connection conn = ConexionBD.getInstancia().getConexion();

        if (conn == null) {
            System.err.println("❌ No se pudo abrir la conexión a la BD (sistema_ventas).");
            return false;
        }

        UsuarioRepository repo = new UsuarioRepository(conn);
        boolean exito         = false;

        try {
            exito = repo.guardar(usuario);
        } catch (Exception e) {
            System.err.println("❌ Excepción inesperada al guardar el usuario: " + e.getMessage());
            exito = false;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                System.err.println("❌ Error cerrando la conexión: " + e.getMessage());
            }
        }

        return exito;
    }
}
