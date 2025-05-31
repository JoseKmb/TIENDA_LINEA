plugins {
    application
    // Actualiza a la última versión del plugin JavaFX
    id("org.openjfx.javafxplugin") version "0.0.14"
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}

repositories {
    mavenCentral()
}

val javafxVersion = "21.0.6" // Asegúrate de coincidir con la SDK que descargaste

dependencies {
    // Ya no necesitamos los classifiers ":win", el plugin se encarga de traer el runtime correcto
    implementation("org.openjfx:javafx-controls:$javafxVersion")
    implementation("org.openjfx:javafx-fxml:$javafxVersion")
    implementation("org.openjfx:javafx-web:$javafxVersion")

    implementation("com.mysql:mysql-connector-j:9.2.0")
    implementation("com.google.code.gson:gson:2.10.1")
}

application {
    // Clase que extiende javafx.application.Application
    mainClass.set("Vistas.InicioFX")
}

javafx {
    version = javafxVersion
    modules = listOf("javafx.controls", "javafx.fxml",  "javafx.web", "javafx.graphics"// si usas WebView más adelante
    )
}
