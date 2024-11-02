package eu.andreatt.ejerciciol_dein.application;

import eu.andreatt.ejerciciol_dein.controllers.L_EditAeropuertoController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class L_EditarAeropuerto extends Stage {

    private L_EditAeropuertoController controller;

    public L_EditarAeropuerto(String nombre, String pais, String ciudad, String calle, int numero, int anioInauguracion, int capacidad, boolean radioPublico, boolean radioPrivado, int numSocios, float financiacion, int numTrabajadores, int idDireccion, int idAeropuerto) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/eu/andreatt/ejerciciol_dein/fxml/L_EditarAeropuerto.fxml"));
            GridPane root = loader.load();

            // Obtener el controlador
            controller = loader.getController();

            // Configurar los datos del aeropuerto
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

            // Configuración de visibilidad según tipo de aeropuerto
            if (controller.isPrivadoSelected()) {
                controller.habilitarRadioPrivados();
            } else {
                controller.habilitarRadioPublicos();
            }

            Scene scene = new Scene(root);
            initModality(Modality.APPLICATION_MODAL);
            setTitle("AVIONES - EDITAR AEROPUERTO");
            setResizable(false);
            setScene(scene);

            // Configurar icono de la ventana
            Image icon = new Image(getClass().getResourceAsStream("/eu/andreatt/ejerciciol_dein/images/avion.png"));
            getIcons().add(icon);

            showAndWait();
        } catch (Exception e) {
            System.err.println("Error al abrir la ventana de Editar Aeropuerto: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public L_EditAeropuertoController getController() {
        return controller;
    }
}
