package eu.andreatt.ejerciciol_dein.application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class L_BorrarAvion extends Stage {
    public L_BorrarAvion() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/eu/andreatt/ejerciciol_dein/fxml/L_BorrarAvion.fxml"));
            GridPane root = loader.load();
            Scene scene = new Scene(root,400,200);
            setTitle("AVIONES - BORRAR AVION");
            setResizable(false);
            setScene(scene);

            Image icon = new Image(getClass().getResourceAsStream("/eu/andreatt/ejerciciol_dein/images/avion.png"));
            getIcons().add(icon);

            // initModality(Modality.APPLICATION_MODAL); // Comenta esta línea temporalmente si hay problemas de modalidad
            showAndWait();
        } catch (Exception e) {
            System.err.println("Error al abrir la ventana de Borrar Avion: " + e.getMessage());
            e.printStackTrace();
        }
    }
}