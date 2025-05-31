package Modelos.Catalogo;

/**
 * Representa una fila de la tabla productos.
 *  id_producto, nombre, descripcion, precio, stock, id_categoria, ruta_imagen, activo …
 */
public class Producto {
    private final int    id;
    private final String nombre;
    private final String descripcion;
    private final double precio;
    private final int    stock;
    private final int    idCategoria;
    private final String rutaImagen;   // URL o path relativa

    public Producto(int id, String nombre, String descripcion,
                    double precio, int stock, int idCategoria, String rutaImagen) {
        this.id          = id;
        this.nombre      = nombre;
        this.descripcion = descripcion;
        this.precio      = precio;
        this.stock       = stock;
        this.idCategoria = idCategoria;
        this.rutaImagen  = rutaImagen;
    }

    /* ---- getters ---- */
    public int getId()              { return id; }
    public String getNombre()       { return nombre; }
    public String getDescripcion()  { return descripcion; }
    public double getPrecio()       { return precio; }
    public int getStock()           { return stock; }
    public int getIdCategoria()     { return idCategoria; }
    public String getRutaImagen()   { return rutaImagen; }
}
