package Repository;

import Modelos.Usuarios.CuentaUsuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * DAO para la tabla 'usuarios'.
 */
public class UsuarioRepository {

    private final Connection conexion;

    public UsuarioRepository(Connection conexion) {
        this.conexion = conexion;
    }

    /* -----------------------------------------------------------
       MÉTODO GUARDAR (ya lo tenías; no se modifica)
       ----------------------------------------------------------- */
    public boolean guardar(CuentaUsuario usuario) {
        String sql = """
        INSERT INTO usuarios
          (id_usuario, nombre, apellido, email, password,
           telefono, direccion, id_rol, activo)
        VALUES (?, ?, ?, ?, SHA1(?), ?, ?, ?, ?)
        """;
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString (1, usuario.getIdUsuario());
            stmt.setString (2, usuario.getNombre());
            stmt.setString (3, usuario.getApellido());
            stmt.setString (4, usuario.getEmail());
            stmt.setString (5, usuario.getPassword());   // 🔑 plano → SHA1 en SQL
            stmt.setString (6, usuario.getTelefono());
            stmt.setString (7, usuario.getDireccion());
            stmt.setInt    (8, usuario.getIdRol());
            stmt.setBoolean(9, usuario.isActivo());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al guardar usuario: " + e.getMessage());
            return false;
        }
    }


    /* -----------------------------------------------------------
       NUEVO MÉTODO: autenticación por correo + password en texto
       ----------------------------------------------------------- */
    public CuentaUsuario buscarPorCredenciales(String email, String pwdPlano) {
        String sql = """
            SELECT * FROM usuarios
            WHERE email = ? AND password = SHA1(?) AND activo = TRUE
            """;
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setString(2, pwdPlano);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new CuentaUsuario(
                        rs.getString("id_usuario"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("telefono"),
                        rs.getString("direccion"),
                        rs.getInt   ("id_rol"),
                        rs.getBoolean("activo")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error autenticar usuario: " + e.getMessage());
        }
        return null;
    }
}
