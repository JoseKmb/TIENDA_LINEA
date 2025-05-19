package Modelos.Usuarios;

public class CuentaUsuario {
    private String email;
    private String nombre;
    private String celular;
    private String contrasena;

    public CuentaUsuario(String email, String nombre, String celular, String contrasena) {
        this.email      = email;
        this.nombre     = nombre;
        this.celular    = celular;
        this.contrasena = contrasena;
    }

    // Getters y setters
    public String getEmail()            { return email; }
    public void   setEmail(String email){ this.email = email; }

    public String getNombre()            { return nombre; }
    public void   setNombre(String nombre){ this.nombre = nombre; }

    public String getCelular()             { return celular; }
    public void   setCelular(String celular){ this.celular = celular; }

    public String getContrasena()                 { return contrasena; }
    public void   setContrasena(String contrasena){ this.contrasena = contrasena; }
}
