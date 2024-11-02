package eu.andreatt.ejerciciol_dein.controllers;

import eu.andreatt.ejerciciol_dein.dao.AeropuertosDao;
import eu.andreatt.ejerciciol_dein.dao.AvionesDao;
import eu.andreatt.ejerciciol_dein.model.Aeropuertos;
import eu.andreatt.ejerciciol_dein.model.Aviones;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class L_BorrarAvionController {

    @FXML
    private Button btnBorrar;

    @FXML
    private Button btnCancelar;

    @FXML
    private ComboBox<Aeropuertos> cmbAeropuertos;

    @FXML
    private ComboBox<Aviones> cmbAviones;

    private AeropuertosDao aeropuertosDao;
    private AvionesDao avionesDao;

    private ObservableList<Aeropuertos> elementosComboAeropuertos;
    private ObservableList<Aviones> elementosComboAviones;

    /** INITIALIZE - INICIALIZAR CARGA DE DATOS */
    @FXML
    void initialize() {
        // Instanciar Dao
        aeropuertosDao = new AeropuertosDao();
        avionesDao = new AvionesDao();

        // Cargar combos
        elementosComboAeropuertos = aeropuertosDao.cargarAeropuertos();
        if (!elementosComboAeropuertos.isEmpty()) {
            cmbAeropuertos.setItems(elementosComboAeropuertos);
            cmbAeropuertos.setValue(elementosComboAeropuertos.get(0));

            cargarAvionesPorAeropuerto(elementosComboAeropuertos.get(0).getId());
        }

        // Actualizar combo de aviones en base al aeropuerto seleccionado
        cmbAeropuertos.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                cargarAvionesPorAeropuerto(newValue.getId());
            }
        });
    }

    /** Carga los aviones en base al ID del aeropuerto */
    private void cargarAvionesPorAeropuerto(int aeropuertoId) {
        elementosComboAviones = avionesDao.dameAvionesPorAeropuerto(aeropuertoId);
        if (!elementosComboAviones.isEmpty()) {
            cmbAviones.setItems(elementosComboAviones);
            cmbAviones.setValue(elementosComboAviones.get(0));
        } else {
            cmbAviones.setItems(FXCollections.observableArrayList());
        }
    }

    @FXML
    void actBorrarAvion(ActionEvent event) {
        if (cmbAviones.getSelectionModel().getSelectedItem() != null) {
            int id = cmbAviones.getSelectionModel().getSelectedItem().getId();
            avionesDao.borrarAvion(id);

            // Actualizar la lista de aviones tras el borrado
            cargarAvionesPorAeropuerto(cmbAeropuertos.getSelectionModel().getSelectedItem().getId());

            // Mensaje de alerta
            Alert alerta = generarVentana(Alert.AlertType.INFORMATION, "Se ha BORRADO el AVIÓN", "INFO");
            alerta.show();
        } else {
            // Mensaje de alerta
            Alert alerta = generarVentana(Alert.AlertType.ERROR, "No se ha seleccionado ningún AVIÓN", "ERROR");
            alerta.show();
        }
    }

    @FXML
    void btnCancelarAvion(ActionEvent event) {
        // Cerrar ventana modal
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }

    /** Generar ventana de alerta */
    private Alert generarVentana(Alert.AlertType tipoDeAlerta, String mensaje, String title) {
        Alert alerta = new Alert(tipoDeAlerta);
        alerta.setContentText(mensaje);
        alerta.setHeaderText(null);
        alerta.setTitle(title);
        return alerta;
    }
}
