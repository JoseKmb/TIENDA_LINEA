package Controladores.Usuario;

import Modelos.Usuarios.CuentaUsuario;
import Repository.UsuarioRepository;
import util.ConexionBD;

import java.sql.Connection;

/**
 * Lógica de inicio de sesión: valida correo + password
 * y devuelve el objeto CuentaUsuario si es correcto.
 */
public class LoginController {

    public CuentaUsuario autenticar(String email, String passwordPlano) {

        Connection conn = ConexionBD.getInstancia().getConexion();
        if (conn == null) {
            System.err.println("Sin conexión a BD");
            return null;
        }

        UsuarioRepository repo = new UsuarioRepository(conn);
        CuentaUsuario user = repo.buscarPorCredenciales(email, passwordPlano);

        try { conn.close(); } catch (Exception ignored) {}
        return user;    // null = fallo
    }
}
