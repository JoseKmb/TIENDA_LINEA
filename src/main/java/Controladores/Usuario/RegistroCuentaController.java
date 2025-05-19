package Controladores.Usuario;

import Modelos.Usuarios.CuentaUsuario;
import repository.CuentaUsuarioRepository;  // tu capa de acceso a datos

public class RegistroCuentaController {
    private final CuentaUsuarioRepository repo;

    public RegistroCuentaController(CuentaUsuarioRepository repo) {
        this.repo = repo;
    }

    /**
     * Crea la cuenta en la base de datos (o donde la guardes).
     */
    public void crearCuenta(CuentaUsuario cuenta) {
        // Aquí podrías validar formato de email, fuerza de contraseña, etc.
        repo.guardar(cuenta);
    }
}
