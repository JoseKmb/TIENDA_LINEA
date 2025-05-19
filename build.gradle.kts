plugins {
    id("application")
    id("org.openjfx.javafxplugin") version "0.0.13"
}

repositories {
    mavenCentral()
}

val javafxVersion = "20"

dependencies {
    implementation("org.openjfx:javafx-controls:$javafxVersion:win")
    implementation("org.openjfx:javafx-fxml:$javafxVersion:win")
    implementation("org.openjfx:javafx-web:$javafxVersion:win")
    implementation("com.mysql:mysql-connector-j:9.2.0")

}

application {
    mainClass.set("Vistas.Inicio") // Reemplaza con tu clase principal
}

javafx {
    version = javafxVersion
    modules = listOf("javafx.controls", "javafx.fxml", "javafx.web")
}
