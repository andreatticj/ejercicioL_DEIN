package eu.andreatt.ejerciciol_dein.controllers;

import eu.andreatt.ejerciciol_dein.dao.AeropuertosDao;
import eu.andreatt.ejerciciol_dein.dao.AvionesDao;
import eu.andreatt.ejerciciol_dein.model.Aeropuertos;
import eu.andreatt.ejerciciol_dein.model.Aviones;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

public class L_ActivarDesactivarAvionController {

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnGuardar;

    @FXML
    private ComboBox<Aeropuertos> cmbAeropuertos;

    @FXML
    private ComboBox<Aviones> cmbAviones;

    @FXML
    private RadioButton rbActivado;

    @FXML
    private RadioButton rbDesactivado;

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
        cmbAeropuertos.setItems(elementosComboAeropuertos);

        // Seleccionar el primer aeropuerto por defecto
        if (!elementosComboAeropuertos.isEmpty()) {
            cmbAeropuertos.setValue(elementosComboAeropuertos.get(0));
            cargarAvionesPorAeropuerto(elementosComboAeropuertos.get(0));
        }

        // Listener para el ComboBox de aeropuertos
        cmbAeropuertos.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                cargarAvionesPorAeropuerto(newValue);
            }
        });

        // Listener para el ComboBox de aviones
        cmbAviones.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            actualizarEstadoRadioButton();
        });
    }

    private void cargarAvionesPorAeropuerto(Aeropuertos aeropuerto) {
        elementosComboAviones = avionesDao.dameAvionesPorAeropuerto(aeropuerto.getId());
        cmbAviones.setItems(elementosComboAviones);

        if (!elementosComboAviones.isEmpty()) {
            cmbAviones.setValue(elementosComboAviones.get(0)); // Seleccionar el primer avión
        } else {
            cmbAviones.setValue(null); // Si no hay aviones, asegurarse de que esté vacío
        }
        // Actualizar el estado del radio button
        actualizarEstadoRadioButton();
    }

    private void actualizarEstadoRadioButton() {
        if (cmbAviones.getSelectionModel().getSelectedItem() != null) {
            int activado = cmbAviones.getSelectionModel().getSelectedItem().getActivado();
            if (activado == 0) {
                rbDesactivado.setSelected(true);
                rbActivado.setSelected(false);
            } else {
                rbDesactivado.setSelected(false);
                rbActivado.setSelected(true);
            }
        } else {
            // Si no hay avión seleccionado, desmarcar ambos
            rbActivado.setSelected(false);
            rbDesactivado.setSelected(false);
        }
    }

    @FXML
    void actCancelar(ActionEvent event) {
        // Cerrar ventana modal
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }

    @FXML
    void actGuardar(ActionEvent event) {
        // Actualizar campo activado en base al radio seleccionado
        if (cmbAviones.getSelectionModel().getSelectedItem() != null) {
            int id = cmbAviones.getSelectionModel().getSelectedItem().getId();
            int activado = rbActivado.isSelected() ? 1 : 0;
            avionesDao.actualizarAvionActivo(id, activado);

            // Mensaje de alerta
            Alert alerta = generarVentana(Alert.AlertType.INFORMATION, "Se ha actualizado el estado del AVIÓN", "INFO");
            alerta.show();
        } else {
            // Mensaje de alerta
            Alert alerta = generarVentana(Alert.AlertType.ERROR, "No se ha seleccionado ningún AVIÓN", "ERROR");
            alerta.show();
        }
    }

    /** GENERAR VENTANA DE ALERTA */
    private Alert generarVentana(Alert.AlertType tipoDeAlerta, String mensaje, String title) {
        Alert alerta = new Alert(tipoDeAlerta);
        alerta.setContentText(mensaje);
        alerta.setHeaderText(null);
        alerta.setTitle(title);
        return alerta;
    }
}
