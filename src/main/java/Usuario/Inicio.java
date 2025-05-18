package Usuario;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import javafx.scene.web.WebEngine;

import java.util.Objects;

public class Inicio extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Crear un WebView y obtener su motor
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();

        // Cargar el archivo HTML desde los recursos
        String htmlPath = Objects.requireNonNull(getClass().getResource("/index.html")).toExternalForm();
        webEngine.load(htmlPath);

        // Crear la escena con el WebView
        Scene scene = new Scene(webView, 1280, 720);

        // Configurar el escenario
        primaryStage.setTitle("Inicio");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
