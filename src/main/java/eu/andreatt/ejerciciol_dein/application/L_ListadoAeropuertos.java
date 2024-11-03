package eu.andreatt.ejerciciol_dein.application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Clase que representa la ventana de listado de aeropuertos en la aplicación.
 * Extiende la clase {@link Stage} y permite cargar y mostrar una interfaz que
 * lista los aeropuertos existentes.
 */
public class L_ListadoAeropuertos extends Stage {

    /**
     * Constructor que configura y muestra la ventana de listado de aeropuertos.
     * Carga la interfaz desde un archivo FXML, establece el título, el tamaño y el ícono de la ventana,
     * y muestra la ventana en modo de espera hasta que el usuario la cierre.
     */
    public L_ListadoAeropuertos() {
        try {
            // Cargar la interfaz desde el archivo FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/eu/andreatt/ejerciciol_dein/fxml/L_ListadoAeropuertos.fxml"));
            GridPane root = loader.load();
            Scene scene = new Scene(root);

            // Configuración de la ventana
            setTitle("AVIONES - AEROPUERTOS");
            setResizable(false);
            setScene(scene);

            // Configurar el ícono de la ventana
            Image icon = new Image(getClass().getResourceAsStream("/eu/andreatt/ejerciciol_dein/images/avion.png"));
            getIcons().add(icon);

            // Muestra la ventana y espera a que se cierre antes de continuar
            showAndWait();
        } catch (Exception e) {
            // Maneja errores de carga y muestra un mensaje de error en la consola
            System.err.println("Error al abrir la ventana de Listado de Aeropuertos: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
