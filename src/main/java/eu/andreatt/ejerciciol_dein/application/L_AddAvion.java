package eu.andreatt.ejerciciol_dein.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class L_AddAvion extends Stage {
    public L_AddAvion() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/eu/andreatt/ejerciciol_dein/fxml/L_AddAvion.fxml"));
            GridPane root = loader.load();
            Scene scene = new Scene(root,300,300);
            setTitle("AVIONES - AÑADIR AVION");
            setResizable(false);
            setScene(scene);

            Image icon = new Image(getClass().getResourceAsStream("/eu/andreatt/ejerciciol_dein/images/avion.png"));
            getIcons().add(icon);

            // initModality(Modality.APPLICATION_MODAL); // Comenta esta línea temporalmente si hay problemas de modalidad
            showAndWait();
        } catch (Exception e) {
            System.err.println("Error al abrir la ventana de Añadir Avion: " + e.getMessage());
            e.printStackTrace();
        }
    }
}