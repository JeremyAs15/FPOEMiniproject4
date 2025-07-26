package com.example.fpoeminiproject4;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Clase principal que inicia la aplicación del juego.
 */
public class Main extends Application {
    /**
     * Método principal de inicio de la aplicación JavaFX.
     * Configura la ventana principal y carga la vista inicial.
     * @param stage Escenario principal proporcionado por JavaFX.
     * @throws IOException Si ocurre un error al cargar el archivo FXML.
     */
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fpoeminiproject4/StartView.fxml"));
        Parent root = loader.load();
        stage.setTitle("Battle Ship Game");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Método principal que inicia la aplicación.
     * @param args Argumentos de la línea de comandos.
     */
    public static void main(String[] args) {
        launch(args);
    }
}