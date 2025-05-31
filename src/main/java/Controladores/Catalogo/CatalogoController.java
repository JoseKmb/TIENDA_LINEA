package Controladores.Catalogo;

import Modelos.Catalogo.Categoria;
import Modelos.Catalogo.Producto;
import Repository.CategoriaRepository;
import Repository.ProductoRepository;

import java.util.List;
import java.util.function.Consumer;

public class CatalogoController {

    private final Consumer<List<Producto>> onSuccess;      // callback a la vista

    public CatalogoController(Consumer<List<Producto>> callback) {
        this.onSuccess = callback;
    }

    /* --- categorías para ComboBox --- */
    public List<Categoria> cargarCategorias() {
        return CategoriaRepository.obtenerTodas();
    }

    /* --- filtra según cat (=0 todas) o texto --- */
    public void filtrar(int idCat, String texto) {
        List<Producto> lista;

        if (!texto.isBlank()) {
            lista = new ProductoRepository(
                    util.ConexionBD.getInstancia().getConexion())
                    .buscarTexto(texto);
        } else if (idCat > 0) {
            lista = new ProductoRepository(
                    util.ConexionBD.getInstancia().getConexion())
                    .listarPorCategoria(idCat);
        } else {
            lista = ProductoRepository.todos();
        }
        onSuccess.accept(lista);
    }
}
