package eu.andreatt.ejerciciol_dein.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class L_AddAeropuerto extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(L_AddAeropuerto.class.getResource("/eu/andreatt/ejerciciol_dein/fxml/L_AddAerpuerto.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("AVIONES - AÑADIR AEROPUERTO");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}