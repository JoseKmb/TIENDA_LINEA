package Repository;

import Modelos.Catalogo.Categoria;
import util.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaRepository extends AbstractRepository<Categoria,Integer> {

    public CategoriaRepository(Connection c) { super(c); }

    @Override
    public Categoria buscarPorId(Integer id) {
        final String sql = "SELECT * FROM categorias WHERE id_categoria = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                return map(rs);
        } catch (SQLException ignored) {}
        return null;
    }

    @Override
    public List<Categoria> listarTodos() {
        List<Categoria> list = new ArrayList<>();
        String sql = "SELECT * FROM categorias ORDER BY nombre";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) list.add(map(rs));
        } catch (SQLException ignored) {}
        return list;
    }

    private Categoria map(ResultSet r) throws SQLException {
        return new Categoria(
                r.getInt("id_categoria"),
                r.getString("nombre"),
                r.getString("descripcion")
        );
    }

    /* ——— Atajo ——— */
    public static List<Categoria> obtenerTodas() {
        try (Connection c = ConexionBD.getInstancia().getConexion()) {
            return new CategoriaRepository(c).listarTodos();
        } catch (Exception e) { return List.of(); }
    }
}
