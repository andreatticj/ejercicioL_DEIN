package eu.andreatt.ejerciciol_dein.controllers;

import eu.andreatt.ejerciciol_dein.dao.AeropuertosDao;
import eu.andreatt.ejerciciol_dein.dao.AvionesDao;
import eu.andreatt.ejerciciol_dein.model.Aeropuertos;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class L_AddAvionController {

    @FXML
    private Button btnCancelarAviones;

    @FXML
    private Button btnGuardarAviones;

    @FXML
    private ComboBox<Aeropuertos> cmbAeropuerto;

    @FXML
    private RadioButton rbActivado;

    @FXML
    private RadioButton rbDesactivado;

    @FXML
    private ToggleGroup rbGroup;

    @FXML
    private TextField txtAsientos;

    @FXML
    private TextField txtModelo;

    @FXML
    private Label txtVelMax;

    private AeropuertosDao aeropuertosDao;
    private AvionesDao avionesDao;

    private ObservableList<Aeropuertos> elementosCombo;


    @FXML
    void actCancelarAviones(ActionEvent event) {
        //Cerrar ventana modal
        Stage stage = (Stage) btnCancelarAviones.getScene().getWindow();
        stage.close();
    }

    @FXML
    void actGuardarAviones(ActionEvent event) {
        String errores = validarDatos();

        if(errores.length()!=0) {
            //Mensaje de alerta
            Alert alerta = generarVentana(Alert.AlertType.ERROR, errores, "ERROR");
            alerta.show();

            //Cerrar ventana modal
            Stage stage = (Stage) btnCancelarAviones.getScene().getWindow();
            stage.close();
        }else {

            //Validar que no existe el modelo en el aeropuerto
            Aeropuertos aeropuertoSeleccionado = cmbAeropuerto.getSelectionModel().getSelectedItem();
            int idAeropuerto = aeropuertosDao.dameIdDeAeropuerto(aeropuertoSeleccionado.getNombre(),aeropuertoSeleccionado.getAnioInauguracion(),aeropuertoSeleccionado.getCapacidad(), aeropuertoSeleccionado.getId_direccion());
            boolean existeModelo = avionesDao.existeModeloEnAeropuerto(txtModelo.getText(), idAeropuerto);

            if(existeModelo) {
                //Mensaje de alerta
                Alert alerta = generarVentana(Alert.AlertType.ERROR, "El MODELO existe en el AEROPUERTO seleccionado", "ERROR");
                alerta.show();
            }else {

                int activado = rbActivado.isSelected() ? 1 : 0;
                avionesDao.insertarAvion(txtModelo.getText(), Integer.parseInt(txtAsientos.getText()), Float.parseFloat(txtVelMax.getText()), activado, idAeropuerto);

                //Mensaje de alerta
                Alert alerta = generarVentana(Alert.AlertType.INFORMATION, "Se ha AÑADIDO un avión", "INFO");
                alerta.show();

                limpiarVentana();
            }
        }
    }

    /** VALIDAR TEXTFIELDS */
    private String validarDatos() {
        String errores="";

        //Validar campos numéricos
        if(!esNumeroEntero(txtAsientos.getText())) {
            errores+="El NÚMERO DE ASIENTOS debe ser un número\n";
        }

        if(!esNumeroDecimal(txtVelMax.getText())) {
            errores+="La VELOCIDAD MÁXIMA debe ser un número decimal\n";
        }

        //Validar modelo
        if(txtModelo.getText().length()==0) {
            errores+="Se debe asignar el MODELO del avión\n";
        }

        //Validar Radios
        if(rbActivado.isSelected()==false && rbDesactivado.isSelected()==false) {
            errores+="Se debe SELECCIONAR uno de los radios\n";
        }

        return errores;
    }

    /** VALIDAR SI ES UN NUMERO DECIMAL */
    private static boolean esNumeroDecimal(String valor) {
        return valor.matches("-?\\d+(\\.\\d+)?");
    }

    /** VALIDAR SI ES UN NUMERO */
    private static boolean esNumeroEntero(String valor) {
        return valor.matches("-?\\d+");
    }

    /** GENERAR VENTANA DE ALERTA */
    private Alert generarVentana(Alert.AlertType tipoDeAlerta, String mensaje, String title) {
        Alert alerta = new Alert(tipoDeAlerta);
        alerta.setContentText(mensaje);
        alerta.setHeaderText(null);
        alerta.setTitle(title);
        return alerta;
    }

    /** LIMPIAR DATOS DE VENTANA */
    private void limpiarVentana() {
        txtAsientos.setText("");
        txtModelo.setText("");
        txtVelMax.setText("");
        rbActivado.setSelected(false);
        rbDesactivado.setSelected(false);
        cmbAeropuerto.setValue(elementosCombo.get(0));
    }

}
