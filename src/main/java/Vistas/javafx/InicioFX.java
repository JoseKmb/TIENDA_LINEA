package Vistas.javafx;

import Vistas.Swing.RegistroSimulado;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class InicioFX extends Application {

    @Override
    public void start(Stage stage) {
        // --- HEADER ---
        HBox header = new HBox(10);
        header.getStyleClass().add("header");
        header.setPadding(new Insets(10));
        header.setAlignment(Pos.CENTER_LEFT);

        // Logo
        ImageView logo = new ImageView(new Image(getClass().getResourceAsStream("/media/icons/logo-meli.svg")));
        logo.setFitWidth(120);
        logo.setPreserveRatio(true);

        // Buscador
        TextField buscador = new TextField();
        buscador.setId("buscador");
        buscador.setPromptText("Buscar productos, marcas y más...");
        buscador.setFont(Font.font("Roboto", 14));

        header.getChildren().addAll(logo, buscador);

        // --- CENTER ---
        VBox center = new VBox(20);
        center.setAlignment(Pos.CENTER);
        center.setPadding(new Insets(40));

        Text welcome = new Text("Bienvenido a Mercado Libre");
        welcome.setFont(Font.font("Roboto", 24));

        Button btnCrear = new Button("Crear cuenta");
        btnCrear.getStyleClass().add("button-registrar");
        btnCrear.setOnAction(e -> {
            // Abrir ventana de registro (JavaFX)
            RegistroSimulado registro = new RegistroSimulado();
            registro.show();
            stage.close();
        });

        center.getChildren().addAll(welcome, btnCrear);

        // --- LAYOUT PRINCIPAL ---
        BorderPane root = new BorderPane();
        root.setTop(header);
        root.setCenter(center);

        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add(getClass().getResource("/inicio.css").toExternalForm());

        stage.setTitle("Mercado Libre – Simulación");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
