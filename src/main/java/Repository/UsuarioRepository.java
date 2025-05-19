package Repository;

import Modelos.Usuarios.CuentaUsuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UsuarioRepository {

    private final Connection conexion;

    // Inyección de dependencia: el Repository no sabe de dónde viene la conexión
    public UsuarioRepository(Connection conexion) {
        this.conexion = conexion;
    }

    public boolean guardar(CuentaUsuario usuario) {
        String sql = """
            INSERT INTO usuarios 
            (id_usuario, nombre, apellido, email, password, telefono, direccion, id_rol, activo) 
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {

            stmt.setString(1, usuario.getIdUsuario());
            stmt.setString(2, usuario.getNombre());
            stmt.setString(3, usuario.getApellido());
            stmt.setString(4, usuario.getEmail());
            stmt.setString(5, usuario.getPassword());
            stmt.setString(6, usuario.getTelefono());
            stmt.setString(7, usuario.getDireccion());
            stmt.setInt(8, usuario.getIdRol());
            stmt.setBoolean(9, usuario.isActivo());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al registrar usuario: " + e.getMessage());
            return false;
        }
    }
}
