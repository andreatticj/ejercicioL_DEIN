package eu.andreatt.ejerciciol_dein.application;

import eu.andreatt.ejerciciol_dein.controllers.L_EditAeropuertoController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Clase que representa la ventana de edición de un aeropuerto en la aplicación.
 * Extiende la clase {@link Stage} y permite cargar y mostrar una interfaz de edición
 * de datos de un aeropuerto. Los datos del aeropuerto se configuran a través del controlador.
 */
public class L_EditarAeropuerto extends Stage {

    private L_EditAeropuertoController controller;

    /**
     * Constructor que configura y muestra la ventana de edición de aeropuerto.
     * Carga la interfaz desde un archivo FXML, establece el título, el tamaño y el ícono de la ventana,
     * y muestra la ventana en modo de espera hasta que el usuario la cierre.
     *
     * @param nombre            Nombre del aeropuerto.
     * @param pais              País donde se encuentra el aeropuerto.
     * @param ciudad            Ciudad donde se ubica el aeropuerto.
     * @param calle             Calle de la dirección del aeropuerto.
     * @param numero            Número de la dirección del aeropuerto.
     * @param anioInauguracion  Año de inauguración del aeropuerto.
     * @param capacidad         Capacidad del aeropuerto.
     * @param radioPublico      Indicador de si el aeropuerto es público.
     * @param radioPrivado      Indicador de si el aeropuerto es privado.
     * @param numSocios         Número de socios (aplicable si el aeropuerto es privado).
     * @param financiacion      Financiamiento del aeropuerto.
     * @param numTrabajadores   Número de trabajadores del aeropuerto.
     * @param idDireccion       ID de la dirección asociada al aeropuerto.
     * @param idAeropuerto      ID del aeropuerto.
     */
    public L_EditarAeropuerto(String nombre, String pais, String ciudad, String calle, int numero, int anioInauguracion, int capacidad, boolean radioPublico, boolean radioPrivado, int numSocios, float financiacion, int numTrabajadores, int idDireccion, int idAeropuerto) {
        try {
            // Carga la interfaz desde el archivo FXML especificado
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/eu/andreatt/ejerciciol_dein/fxml/L_EditarAeropuerto.fxml"));
            GridPane root = loader.load();

            // Obtener el controlador de la interfaz
            controller = loader.getController();

            // Configurar los datos del aeropuerto en el controlador
            controller.setTextFieldNombre(nombre);
            controller.setTextFieldPais(pais);
            controller.setTextFieldCiudad(ciudad);
            controller.setTextFieldCalle(calle);
            controller.setTextFieldNumero(String.valueOf(numero));
            controller.setTextFieldAnioInauguracion(String.valueOf(anioInauguracion));
            controller.setTextFieldCapacidad(String.valueOf(capacidad));
            controller.setPublicSelected(radioPublico);
            controller.setPrivadoSelected(radioPrivado);
            controller.setTextFieldNumSocios(String.valueOf(numSocios));
            controller.setTextFieldFinanciacion(String.valueOf(financiacion));
            controller.setTextFieldNumTrabajadores(String.valueOf(numTrabajadores));
            controller.setIdDireccion(idDireccion);
            controller.setIdAeropuerto(idAeropuerto);

            // Configurar la visibilidad de los campos según el tipo de aeropuerto
            if (controller.isPrivadoSelected()) {
                controller.habilitarRadioPrivados();
            } else {
                controller.habilitarRadioPublicos();
            }

            // Configurar la escena y los parámetros de la ventana
            Scene scene = new Scene(root);
            initModality(Modality.APPLICATION_MODAL);
            setTitle("AVIONES - EDITAR AEROPUERTO");
            setResizable(false);
            setScene(scene);

            // Configurar el ícono de la ventana
            Image icon = new Image(getClass().getResourceAsStream("/eu/andreatt/ejerciciol_dein/images/avion.png"));
            getIcons().add(icon);

            // Muestra la ventana y espera a que se cierre antes de continuar
            showAndWait();
        } catch (Exception e) {
            // Maneja errores de carga y muestra un mensaje de error en la consola
            System.err.println("Error al abrir la ventana de Editar Aeropuerto: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Obtiene el controlador de la ventana de edición del aeropuerto.
     *
     * @return El controlador de la ventana de edición del aeropuerto.
     */
    public L_EditAeropuertoController getController() {
        return controller;
    }
}
