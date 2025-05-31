package Vistas.Swing;

import Controladores.Usuario.RegistroCuentaController;
import Modelos.Usuarios.CuentaUsuario;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class RegistroSimulado extends Stage {

    public RegistroSimulado() {
        setTitle("Crear tu cuenta");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(12);
        grid.setPadding(new Insets(20));
        grid.setAlignment(Pos.CENTER);

        TextField idField         = new TextField();      idField.setPromptText("ID de usuario");
        TextField nombreField     = new TextField();      nombreField.setPromptText("Nombre completo");
        TextField apellidoField   = new TextField();      apellidoField.setPromptText("Apellido");
        TextField emailField      = new TextField();      emailField.setPromptText("Correo electrónico");
        TextField telefonoField   = new TextField();      telefonoField.setPromptText("Teléfono");
        TextField direccionField  = new TextField();      direccionField.setPromptText("Dirección");
        PasswordField passField   = new PasswordField();  passField.setPromptText("Contraseña");

        Button btnRegistrar = new Button("Registrar");
        btnRegistrar.getStyleClass().add("button-registrar");
        btnRegistrar.setOnAction(evt -> {
            // Ahora pasamos 'activo = true' en el constructor:
            CuentaUsuario usuario = new CuentaUsuario(
                    idField.getText(),
                    nombreField.getText(),
                    apellidoField.getText(),
                    emailField.getText(),
                    passField.getText(),
                    telefonoField.getText(),
                    direccionField.getText(),
                    1,     // id_rol = 1 (Cliente)
                    true   // activo = true
            );

            boolean exito = new RegistroCuentaController().registrarCuenta(usuario);

            Alert alerta = new Alert(
                    exito
                            ? Alert.AlertType.INFORMATION
                            : Alert.AlertType.ERROR
            );
            alerta.setHeaderText(null);
            alerta.setContentText(
                    exito
                            ? "✅ Cuenta creada exitosamente"
                            : "❌ Error al crear cuenta"
            );
            alerta.showAndWait();

            if (exito) {
                this.close();
                new InicioSimulado();
            }
        });

        grid.add(idField,        0, 0);
        grid.add(nombreField,    0, 1);
        grid.add(apellidoField,  0, 2);
        grid.add(emailField,     0, 3);
        grid.add(telefonoField,  0, 4);
        grid.add(direccionField, 0, 5);
        grid.add(passField,      0, 6);
        grid.add(btnRegistrar,   0, 7);

        Scene scene = new Scene(grid, 400, 450);
        if (getClass().getResource("/inicio.css") != null) {
            scene.getStylesheets().add(
                    getClass().getResource("/inicio.css").toExternalForm()
            );
        }
        setScene(scene);
    }
}
