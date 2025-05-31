package Modelos.Usuarios;

/**
 * Modelo que representa un registro en la tabla 'usuarios' de 'sistema_ventas'.
 * Equivale a:
 *  id_usuario VARCHAR(20) PRIMARY KEY,
 *  nombre     VARCHAR(100),
 *  apellido   VARCHAR(100),
 *  email      VARCHAR(100) UNIQUE,
 *  password   VARCHAR(255),
 *  telefono   VARCHAR(15),
 *  direccion  VARCHAR(255),
 *  id_rol     INT NOT NULL,
 *  fecha_registro DATETIME DEFAULT CURRENT_TIMESTAMP,
 *  activo     BOOLEAN DEFAULT TRUE
 */
public class CuentaUsuario {
    private String  idUsuario;
    private String  nombre;
    private String  apellido;
    private String  email;
    private String  password;
    private String  telefono;
    private String  direccion;
    private int     idRol;
    private boolean activo;

    public CuentaUsuario(String idUsuario,
                         String nombre,
                         String apellido,
                         String email,
                         String password,
                         String telefono,
                         String direccion,
                         int    idRol,
                         boolean activo) {
        this.idUsuario = idUsuario;
        this.nombre    = nombre;
        this.apellido  = apellido;
        this.email     = email;
        this.password  = password;
        this.telefono  = telefono;
        this.direccion = direccion;
        this.idRol     = idRol;
        this.activo    = activo;
    }

    // Getters y setters:
    public String getIdUsuario() { return idUsuario; }
    public void setIdUsuario(String idUsuario) { this.idUsuario = idUsuario; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public int getIdRol() { return idRol; }
    public void setIdRol(int idRol) { this.idRol = idRol; }

    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }
}
