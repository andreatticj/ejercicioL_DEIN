package eu.andreatt.ejerciciol_dein.application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Clase que representa la ventana para borrar un avión en la aplicación.
 * Extiende la clase {@link Stage} y configura la interfaz de usuario para la funcionalidad de borrar un avión.
 */
public class L_BorrarAvion extends Stage {

    /**
     * Constructor que configura y muestra la ventana de borrar avión.
     * Carga la interfaz desde un archivo FXML, establece el título, el tamaño y el ícono de la ventana,
     * y muestra la ventana en modo de espera hasta que el usuario la cierre.
     */
    public L_BorrarAvion() {
        try {
            // Carga la interfaz desde el archivo FXML especificado
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/eu/andreatt/ejerciciol_dein/fxml/L_BorrarAvion.fxml"));
            GridPane root = loader.load();

            // Configura la escena con el tamaño especificado
            Scene scene = new Scene(root, 400, 200);
            setTitle("AVIONES - BORRAR AVION");
            setResizable(false);
            setScene(scene);

            // Establece el ícono de la ventana
            Image icon = new Image(getClass().getResourceAsStream("/eu/andreatt/ejerciciol_dein/images/avion.png"));
            getIcons().add(icon);

            // Muestra la ventana y espera a que se cierre antes de continuar
            showAndWait();
        } catch (Exception e) {
            // Maneja errores de carga y muestra un mensaje de error en la consola
            System.err.println("Error al abrir la ventana de Borrar Avion: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
