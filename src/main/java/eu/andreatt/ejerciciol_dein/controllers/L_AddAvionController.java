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
    private TextField txtVelMax;

    @FXML
    private RadioButton rbPrivado;

    @FXML
    private RadioButton rbPublico;

    private AeropuertosDao aeropuertosDao;
    private AvionesDao avionesDao;

    private ObservableList<Aeropuertos> elementosCombo;

    // Metodo de inicialización de los componentes
    @FXML
    public void initialize() {
        aeropuertosDao = new AeropuertosDao();
        avionesDao = new AvionesDao();
        // Cargar aeropuertos al inicio con la opción predeterminada seleccionada
        cargarAeropuertosPublicos();

    }

    /**
     * Metodo que se ejecuta al hacer clic en el botón de cancelar.
     * Cierra la ventana modal.
     *
     * @param event El evento de acción.
     */
    @FXML
    void actCancelarAviones(ActionEvent event) {
        Stage stage = (Stage) btnCancelarAviones.getScene().getWindow();
        stage.close();
    }

    /**
     * Metodo que se ejecuta al hacer clic en el botón de guardar.
     * Valida los datos ingresados y, si son correctos,
     * inserta un nuevo avión en la base de datos.
     *
     * @param event El evento de acción.
     */
    @FXML
    void actGuardarAviones(ActionEvent event) {

        String errores = validarDatos();

        if (errores.length() != 0) {
            // Mensaje de alerta si hay errores en los datos.
            Alert alerta = generarVentana(Alert.AlertType.ERROR, errores, "ERROR");
            alerta.show();

            Stage stage = (Stage) btnCancelarAviones.getScene().getWindow();
            stage.close();
        } else {
            // Validar que no existe el modelo en el aeropuerto.
            Aeropuertos aeropuertoSeleccionado = cmbAeropuerto.getSelectionModel().getSelectedItem();

            int idAeropuerto = aeropuertosDao.dameIdDeAeropuerto(aeropuertoSeleccionado.getNombre(), aeropuertoSeleccionado.getAnioInaguracion(), aeropuertoSeleccionado.getCapacidad(), aeropuertoSeleccionado.getId_direccion());

            boolean existeModelo = avionesDao.existeModeloEnAeropuerto(txtModelo.getText(), idAeropuerto);
            System.out.println("hola.------");

            if (existeModelo) {
                // Mensaje de alerta si el modelo ya existe en el aeropuerto.
                Alert alerta = generarVentana(Alert.AlertType.ERROR, "El MODELO existe en el AEROPUERTO seleccionado", "ERROR");
                alerta.show();
            } else {
                int activado = rbActivado.isSelected() ? 1 : 0;
                System.out.println(txtModelo.getText()+","+ Integer.parseInt(txtAsientos.getText())+","+ Float.parseFloat(txtVelMax.getText())+","+ activado+","+ idAeropuerto);
                avionesDao.insertarAvion(txtModelo.getText(), Integer.parseInt(txtAsientos.getText()), Float.parseFloat(txtVelMax.getText()), activado, idAeropuerto);

                // Mensaje de alerta de éxito.
                Alert alerta = generarVentana(Alert.AlertType.INFORMATION, "Se ha AÑADIDO un avión", "INFO");
                alerta.show();

                limpiarVentana();
            }
        }
    }

    /**
     * Valida los datos ingresados en los campos de texto.
     *
     * @return Un String que contiene los errores encontrados.
     */
    private String validarDatos() {
        String errores = "";

        // Validar campos numéricos
        if (!esNumeroEntero(txtAsientos.getText())) {
            errores += "El NÚMERO DE ASIENTOS debe ser un número\n";
        }

        if (!esNumeroDecimal(txtVelMax.getText())) {
            errores += "La VELOCIDAD MÁXIMA debe ser un número decimal\n";
        }

        // Validar modelo
        if (txtModelo.getText().length() == 0) {
            errores += "Se debe asignar el MODELO del avión\n";
        }

        // Validar Radios
        if (rbActivado.isSelected() == false && rbDesactivado.isSelected() == false) {
            errores += "Se debe SELECCIONAR uno de los radios\n";
        }

        return errores;
    }

    /**
     * Verifica si el valor dado es un número decimal.
     *
     * @param valor El valor a verificar.
     * @return true si es un número decimal, false en caso contrario.
     */
    private static boolean esNumeroDecimal(String valor) {
        return valor.matches("-?\\d+(\\.\\d+)?");
    }

    /**
     * Verifica si el valor dado es un número entero.
     *
     * @param valor El valor a verificar.
     * @return true si es un número entero, false en caso contrario.
     */
    private static boolean esNumeroEntero(String valor) {
        return valor.matches("-?\\d+");
    }

    /**
     * Genera una ventana de alerta con el mensaje y tipo de alerta especificados.
     *
     * @param tipoDeAlerta El tipo de alerta (ERROR, INFORMATION, etc.).
     * @param mensaje      El mensaje a mostrar en la alerta.
     * @param title        El título de la ventana de alerta.
     * @return Un objeto Alert configurado.
     */
    private Alert generarVentana(Alert.AlertType tipoDeAlerta, String mensaje, String title) {
        Alert alerta = new Alert(tipoDeAlerta);
        alerta.setContentText(mensaje);
        alerta.setHeaderText(null);
        alerta.setTitle(title);
        return alerta;
    }

    /**
     * Limpia los datos de la ventana de entrada.
     */
    private void limpiarVentana() {
        txtAsientos.setText("");
        txtModelo.setText("");
        txtVelMax.setText("");
        rbActivado.setSelected(false);
        rbDesactivado.setSelected(false);
        cmbAeropuerto.setValue(elementosCombo.get(0));  // Volver a seleccionar el primer aeropuerto
    }

    /**
     * Metodo que se ejecuta al seleccionar el tipo de aeropuerto (Público o Privado).
     * Carga los aeropuertos correspondientes en el ComboBox.
     */
    @FXML
    private void actualizarComboAeropuerto() {
        if (rbPrivado.isSelected()) {
            cargarAeropuertosPrivados();
        } else if (rbPublico.isSelected()) {
            cargarAeropuertosPublicos();
        }
    }

    /**
     * Carga los aeropuertos privados en el ComboBox.
     */
    private void cargarAeropuertosPrivados() {
        elementosCombo = aeropuertosDao.cargarAeropuertos();
        cmbAeropuerto.setItems(elementosCombo);
    }

    /**
     * Carga los aeropuertos públicos en el ComboBox.
     */
    private void cargarAeropuertosPublicos() {
        elementosCombo = aeropuertosDao.cargarAeropuertos();
        cmbAeropuerto.setItems(elementosCombo);
    }


}
