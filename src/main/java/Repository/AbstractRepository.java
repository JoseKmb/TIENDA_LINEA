package Repository;

import java.sql.Connection;
import java.util.List;

/** DAO genérico: T = entidad, K = tipo de la PK */
public abstract class AbstractRepository<T, K> {
    protected final Connection conn;
    protected AbstractRepository(Connection c) { this.conn = c; }

    public abstract T            buscarPorId(K id);
    public abstract List<T>      listarTodos();
}
