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

/**
 * Controlador de la ventana de activación/desactivación de aviones.
 * Permite seleccionar un aeropuerto y un avión asociados, así como actualizar
 * el estado de activación de un avión en la base de datos.
 */
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

    /**
     * Metodo de inicialización llamado automáticamente al cargar la vista.
     * Configura los ComboBoxes de aeropuertos y aviones, y agrega listeners para actualizar el estado.
     */
    @FXML
    void initialize() {
        // Instanciar DAOs
        aeropuertosDao = new AeropuertosDao();
        avionesDao = new AvionesDao();

        // Cargar aeropuertos en el ComboBox
        elementosComboAeropuertos = aeropuertosDao.cargarAeropuertos();
        cmbAeropuertos.setItems(elementosComboAeropuertos);

        // Seleccionar el primer aeropuerto y cargar sus aviones
        if (!elementosComboAeropuertos.isEmpty()) {
            cmbAeropuertos.setValue(elementosComboAeropuertos.get(0));
            cargarAvionesPorAeropuerto(elementosComboAeropuertos.get(0));
        }

        // Listener para actualizar aviones al cambiar el aeropuerto
        cmbAeropuertos.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                cargarAvionesPorAeropuerto(newValue);
            }
        });

        // Listener para actualizar el estado de activación del avión seleccionado
        cmbAviones.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            actualizarEstadoRadioButton();
        });
    }

    /**
     * Carga los aviones del aeropuerto seleccionado en el ComboBox correspondiente.
     *
     * @param aeropuerto El aeropuerto seleccionado.
     */
    private void cargarAvionesPorAeropuerto(Aeropuertos aeropuerto) {
        elementosComboAviones = avionesDao.dameAvionesPorAeropuerto(aeropuerto.getId());
        cmbAviones.setItems(elementosComboAviones);

        // Seleccionar el primer avión en el ComboBox
        if (!elementosComboAviones.isEmpty()) {
            cmbAviones.setValue(elementosComboAviones.get(0));
        } else {
            cmbAviones.setValue(null);
        }

        // Actualizar el estado de activación del avión
        actualizarEstadoRadioButton();
    }

    /**
     * Actualiza el estado de los RadioButton según el estado de activación del avión seleccionado.
     */
    private void actualizarEstadoRadioButton() {
        if (cmbAviones.getSelectionModel().getSelectedItem() != null) {
            int activado = cmbAviones.getSelectionModel().getSelectedItem().getActivado();
            rbActivado.setSelected(activado == 1);
            rbDesactivado.setSelected(activado == 0);
        } else {
            rbActivado.setSelected(false);
            rbDesactivado.setSelected(false);
        }
    }

    /**
     * Cierra la ventana actual al pulsar el botón "Cancelar".
     *
     * @param event Evento de acción del botón "Cancelar".
     */
    @FXML
    void actCancelar(ActionEvent event) {
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }

    /**
     * Guarda el estado de activación del avión seleccionado al pulsar el botón "Guardar".
     * Muestra una alerta de confirmación o error según el caso.
     *
     * @param event Evento de acción del botón "Guardar".
     */
    @FXML
    void actGuardar(ActionEvent event) {
        if (cmbAviones.getSelectionModel().getSelectedItem() != null) {
            int id = cmbAviones.getSelectionModel().getSelectedItem().getId();
            int activado = rbActivado.isSelected() ? 1 : 0;
            avionesDao.actualizarAvionActivo(id, activado);

            // Mostrar alerta de confirmación
            Alert alerta = generarVentana(Alert.AlertType.INFORMATION, "Se ha actualizado el estado del AVIÓN", "INFO");
            alerta.show();
        } else {
            // Mostrar alerta de error
            Alert alerta = generarVentana(Alert.AlertType.ERROR, "No se ha seleccionado ningún AVIÓN", "ERROR");
            alerta.show();
        }
    }

    /**
     * Genera una ventana de alerta personalizada.
     *
     * @param tipoDeAlerta Tipo de alerta (información o error).
     * @param mensaje Mensaje a mostrar en la alerta.
     * @param title Título de la alerta.
     * @return La alerta configurada.
     */
    private Alert generarVentana(Alert.AlertType tipoDeAlerta, String mensaje, String title) {
        Alert alerta = new Alert(tipoDeAlerta);
        alerta.setContentText(mensaje);
        alerta.setHeaderText(null);
        alerta.setTitle(title);
        return alerta;
    }
}
