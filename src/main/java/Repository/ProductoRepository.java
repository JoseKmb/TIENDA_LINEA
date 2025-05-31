package Repository;

import Modelos.Catalogo.Producto;
import util.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoRepository extends AbstractRepository<Producto,Integer> {

    public ProductoRepository(Connection c) { super(c); }

    /* ---------- consultas públicas ---------- */

    @Override
    public Producto buscarPorId(Integer id) {
        String sql = "SELECT * FROM productos WHERE id_producto = ?";
        return ejecutarYMapearUno(sql, id);
    }

    @Override
    public List<Producto> listarTodos() {
        return ejecutarYMapearLista("SELECT * FROM productos");
    }

    public List<Producto> listarPorCategoria(int idCat) {
        String sql = "SELECT * FROM productos WHERE id_categoria = ?";
        return ejecutarYMapearLista(sql, idCat);
    }

    /** Búsqueda LIKE en nombre o descripción */
    public List<Producto> buscarTexto(String texto) {
        String like = "%" + texto + "%";
        String sql  = "SELECT * FROM productos WHERE nombre LIKE ? OR descripcion LIKE ?";
        return ejecutarYMapearLista(sql, like, like);
    }

    /* ---------- mapeo interno ---------- */
    private Producto map(ResultSet r) throws SQLException {
        return new Producto(
                r.getInt("id_producto"),
                r.getString("nombre"),
                r.getString("descripcion"),
                r.getDouble("precio"),
                r.getInt("stock"),
                r.getInt("id_categoria"),
                r.getString("ruta_imagen")  // puede ser null
        );
    }

    /* helpers */
    private List<Producto> ejecutarYMapearLista(String sql, Object... p) {
        List<Producto> list = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            for (int i = 0; i < p.length; i++) ps.setObject(i + 1, p[i]);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(map(rs));
        } catch (SQLException ignored) {}
        return list;
    }
    private Producto ejecutarYMapearUno(String sql, Object... p) {
        List<Producto> l = ejecutarYMapearLista(sql, p);
        return l.isEmpty() ? null : l.get(0);
    }

    /* ——— atajos estáticos para uso rápido ——— */
    public static List<Producto> todos() {
        try (Connection c = ConexionBD.getInstancia().getConexion()) {
            return new ProductoRepository(c).listarTodos();
        } catch (Exception e) { return List.of(); }
    }
}
