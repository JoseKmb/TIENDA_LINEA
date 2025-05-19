package Controladores.Usuario;

import Modelos.Usuarios.CuentaUsuario;
import Repository.UsuarioRepository;
import util.ConexionBD;

import java.sql.Connection;
import java.sql.SQLException;

public class RegistroCuentaController {

    /**
     * Este método se encarga de recibir un objeto CuentaUsuario y
     * solicitar al repositorio que lo persista en la base de datos.
     *
     * @param usuario Objeto CuentaUsuario con los datos del nuevo usuario.
     * @return true si el registro fue exitoso, false si ocurrió un error.
     */
    public boolean registrarCuenta(CuentaUsuario usuario) {
        try (Connection conn = ConexionBD.getInstancia().getConexion()) {

            // Crea el repositorio pasando la conexión como dependencia (inyección)
            UsuarioRepository repo = new UsuarioRepository(conn);

            // Intenta guardar el usuario en la base de datos
            return repo.guardar(usuario);

        } catch (SQLException e) {
            System.err.println("Error al registrar cuenta: " + e.getMessage());
            return false;
        }
    }
}
