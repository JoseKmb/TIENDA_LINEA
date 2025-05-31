package Modelos.Catalogo;

public class Categoria {
    private final int id;
    private final String nombre;
    private final String descripcion;

    public Categoria(int id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public int getId()            { return id; }
    public String getNombre()     { return nombre; }
    public String getDescripcion(){ return descripcion; }

    /** Para que aparezca legible en el JComboBox */
    @Override public String toString() { return nombre; }
}
