plugins {
    application
    id("org.openjfx.javafxplugin") version "0.0.14"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

javafx {
    version = "21.0.6"
    modules("javafx.controls", "javafx.web")
}

application {
    mainClass.set("org.example.Inicio")
}

tasks.withType<Jar> {
    from("src/main/resources") {
        into("/")
    }
}
