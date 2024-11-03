package eu.andreatt.ejerciciol_dein.application;

import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.image.Image;
import javafx.fxml.FXMLLoader;

/**
 * Clase que representa la ventana para añadir un aeropuerto en la aplicación.
 * Extiende la clase {@link Stage} y configura la interfaz de usuario a partir de un archivo FXML.
 */
public class L_AddAeropuerto extends Stage {

    /**
     * Constructor que configura y muestra la ventana de añadir aeropuerto.
     * Carga la interfaz desde un archivo FXML, establece el título, el tamaño y el ícono de la ventana.
     * La ventana se muestra en modo de espera hasta que el usuario la cierre.
     */
    public L_AddAeropuerto() {
        try {
            // Carga la interfaz desde el archivo FXML especificado
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/eu/andreatt/ejerciciol_dein/fxml/L_AddAeropuerto.fxml"));
            GridPane root = loader.load();

            // Configura la escena con el tamaño especificado
            Scene scene = new Scene(root, 300, 550);
            setTitle("AVIONES - AÑADIR AEROPUERTO");
            setResizable(false);
            setScene(scene);

            // Establece el ícono de la ventana
            Image icon = new Image(getClass().getResourceAsStream("/eu/andreatt/ejerciciol_dein/images/avion.png"));
            getIcons().add(icon);

            // Muestra la ventana y espera a que se cierre antes de continuar
            showAndWait();
        } catch (Exception e) {
            // Maneja errores de carga y muestra un mensaje de error en la consola
            System.err.println("Error al abrir la ventana de Añadir Aeropuerto: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
