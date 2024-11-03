package eu.andreatt.ejerciciol_dein.application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Clase que representa una ventana de activación y desactivación de aviones.
 * Extiende la clase {@link Stage} para crear una ventana JavaFX con una interfaz
 * específica para activar o desactivar aviones.
 */
public class L_ActivarDesactivarAvion extends Stage {

    /**
     * Constructor que configura y muestra la ventana de activación y desactivación de aviones.
     * Carga la interfaz desde un archivo FXML, establece el título, el tamaño y el ícono de la ventana,
     * y muestra la ventana en modo de espera hasta que se cierre.
     */
    public L_ActivarDesactivarAvion() {
        try {
            // Carga la interfaz desde el archivo FXML especificado
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/eu/andreatt/ejerciciol_dein/fxml/L_ActivarDesactivarAvion.fxml"));
            GridPane root = loader.load();

            // Configura la escena con el tamaño especificado
            Scene scene = new Scene(root, 300, 300);
            setTitle("AVIONES - ACTIVAR / DESACTIVAR AVION");
            setResizable(false);
            setScene(scene);

            // Establece el ícono de la ventana
            Image icon = new Image(getClass().getResourceAsStream("/eu/andreatt/ejerciciol_dein/images/avion.png"));
            getIcons().add(icon);

            // Muestra la ventana y espera a que se cierre antes de continuar
            showAndWait();
        } catch (Exception e) {
            // Maneja errores de carga y muestra un mensaje de error en la consola
            System.err.println("Error al abrir la ventana de Activar / Desactivar Avion: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
