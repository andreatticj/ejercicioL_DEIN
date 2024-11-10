package eu.andreatt.ejerciciol_dein.controllers;

import eu.andreatt.ejerciciol_dein.dao.AeropuertosDao;
import eu.andreatt.ejerciciol_dein.dao.AeropuertosPrivadosDao;
import eu.andreatt.ejerciciol_dein.dao.AeropuertosPublicosDao;
import eu.andreatt.ejerciciol_dein.dao.DireccionesDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class L_EditAeropuertoController {

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnGuardar;

    @FXML
    private Label lblFinanciacion;

    @FXML
    private Label lblNumTrabajadores;

    @FXML
    private Label lblSocios;

    @FXML
    private ToggleGroup rbGroup;

    @FXML
    private RadioButton rbPrivado;

    @FXML
    private RadioButton rbPublico;

    @FXML
    private TextField txtAnioInaguracion;

    @FXML
    private TextField txtCalle;

    @FXML
    private TextField txtCapaciad;

    @FXML
    private TextField txtCiudad;

    @FXML
    private TextField txtFinanciacion;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtNumTrabajadores;

    @FXML
    private TextField txtNumero;

    @FXML
    private TextField txtPais;

    @FXML
    private TextField txtSocios;

    /** Indica si se ha seleccionado un aeropuerto privado */
    private boolean isPrivadoSelected;

    /** Indica si se ha seleccionado un aeropuerto público */
    private boolean isPublicSelected;

    /** ID de la dirección asociada al aeropuerto */
    private int idDireccion;

    /** ID del aeropuerto que se está editando */
    private int idAeropuerto;

    private AeropuertosDao aeropuertosDao;
    private DireccionesDao direccionesDao;
    private AeropuertosPrivadosDao aeropuertosPrivadosDao;
    private AeropuertosPublicosDao aeropuertosPublicosDao;

    /**
     * Metodo que se ejecuta al inicializar el controlador.
     * Carga los datos iniciales y configura los DAOs necesarios.
     */
    @FXML
    void initialize() {
        // Instanciar DAO
        aeropuertosDao = new AeropuertosDao();
        direccionesDao = new DireccionesDao();
        aeropuertosPrivadosDao = new AeropuertosPrivadosDao();
        aeropuertosPublicosDao = new AeropuertosPublicosDao();
    }

    /**
     * Evento que se ejecuta al pulsar el botón Cancelar.
     * Muestra un mensaje de alerta y cierra la ventana modal.
     *
     * @param event el evento de acción
     */
    @FXML
    void cancelar(ActionEvent event) {
        // Mensaje de alerta
        Alert alerta = generarVentana(Alert.AlertType.INFORMATION, "INFO","Se ha CANCELADO la edición del aeropuerto");
        alerta.show();

        // Cerrar ventana modal
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }

    /**
     * Evento que se ejecuta al pulsar el botón Guardar.
     * Valida los datos ingresados, actualiza la información del aeropuerto y muestra un mensaje de alerta.
     *
     * @param event el evento de acción
     */
    @FXML
    void guardar(ActionEvent event) {
        String errores = "";

        // Existe Direccion
        if (direccionesDao.existeDireccion(txtPais.getText(), txtCiudad.getText(), txtCalle.getText(), Integer.parseInt(txtNumero.getText())) != -1) {
            errores += "-La DIRECCIÓN, porque ya existe\n";
        }

        // Existe Aeropuerto
        if (aeropuertosDao.existeNombreAeropuerto(txtNombre.getText())) {
            errores += "-El AEROPUERTO, porque ya existe\n";
        }

        // Actualizar
        direccionesDao.actualizarDireccion(idDireccion, txtPais.getText(), txtCiudad.getText(), txtCalle.getText(), Integer.parseInt(txtNumero.getText()));
        aeropuertosDao.actualizarAeropuerto(idAeropuerto, txtNombre.getText(), Integer.parseInt(txtAnioInaguracion.getText()), Integer.parseInt(txtCapaciad.getText()), idDireccion);
        if (isPrivadoSelected) {
            aeropuertosPrivadosDao.actualizarAeropuertoPrivado(idAeropuerto, Integer.parseInt(txtSocios.getText()));
        } else {
            aeropuertosPublicosDao.actualizarAeropuertoPublico(idAeropuerto, Double.parseDouble(txtFinanciacion.getText()), Integer.parseInt(txtNumTrabajadores.getText()));
        }

        // Mostrar información
        if (errores.trim().length() == 0) {
            // Mensaje de alerta
            Alert alerta = generarVentana(Alert.AlertType.INFORMATION, "INFO","Se han ACTUALIZADO los datos del aeropuerto");
            alerta.show();
        } else {
            // Mensaje de alerta
            Alert alerta = generarVentana(Alert.AlertType.INFORMATION, "INFO","Se han ACTUALIZADO los datos del aeropuerto a excepción de:\n" + errores);
            alerta.show();
        }

        // Cerrar ventana modal
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }

    /**
     * Metodos getter y setter para los atributos privados.
     * Se utilizan para acceder y modificar el estado de los atributos desde fuera de la clase.
     */

    public RadioButton getRadioPrivado() {
        return rbPrivado;
    }

    public void setRadioPrivado(RadioButton radioPrivado) {
        this.rbPrivado = radioPrivado;
    }

    public RadioButton getRadioPublico() {
        return rbPublico;
    }

    public void setRadioPublico(RadioButton radioPublico) {
        this.rbPublico = radioPublico;
    }

    public TextField getTextFieldAnioInauguracion() {
        return txtAnioInaguracion;
    }

    public void setTextFieldAnioInauguracion(String textFieldAnioInauguracion) {
        this.txtAnioInaguracion.setText(textFieldAnioInauguracion);
    }

    public TextField getTextFieldCalle() {
        return txtCalle;
    }

    public void setTextFieldCalle(String textFieldCalle) {
        this.txtCalle.setText(textFieldCalle);
    }

    public TextField getTextFieldCapacidad() {
        return txtCapaciad;
    }

    public void setTextFieldCapacidad(String textFieldCapacidad) {
        this.txtCapaciad.setText(textFieldCapacidad);
    }

    public TextField getTextFieldCiudad() {
        return txtCiudad;
    }

    public void setTextFieldCiudad(String textFieldCiudad) {
        this.txtCiudad.setText(textFieldCiudad);
    }

    public TextField getTextFieldFinanciacion() {
        return txtFinanciacion;
    }

    public void setTextFieldFinanciacion(String textFieldFinanciacion) {
        this.txtFinanciacion.setText(textFieldFinanciacion);
    }

    public TextField getTextFieldNombre() {
        return txtNombre;
    }

    public void setTextFieldNombre(String textFieldNombre) {
        this.txtNombre.setText(textFieldNombre);
    }

    public TextField getTextFieldNumSocios() {
        return txtSocios;
    }

    public void setTextFieldNumSocios(String textFieldNumSocios) {
        this.txtSocios.setText(textFieldNumSocios);
    }

    public TextField getTextFieldNumTrabajadores() {
        return txtNumTrabajadores;
    }

    public void setTextFieldNumTrabajadores(String textFieldNumTrabajadores) {
        this.txtNumTrabajadores.setText(textFieldNumTrabajadores);
    }

    public TextField getTextFieldNumero() {
        return txtNumero;
    }

    public void setTextFieldNumero(String textFieldNumero) {
        this.txtNumero.setText(textFieldNumero);
    }

    public TextField getTextFieldPais() {
        return txtPais;
    }

    public void setTextFieldPais(String textFieldPais) {
        this.txtPais.setText(textFieldPais);
    }

    public boolean isPrivadoSelected() {
        return isPrivadoSelected;
    }

    public boolean isPublicSelected() {
        return isPublicSelected;
    }

    public void setPrivadoSelected(boolean isPrivadoSelected) {
        this.isPrivadoSelected = isPrivadoSelected;
    }

    public void setPublicSelected(boolean isPublicSelected) {
        this.isPublicSelected = isPublicSelected;
    }

    public int getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(int idDireccion) {
        this.idDireccion = idDireccion;
    }

    public int getIdAeropuerto() {
        return idAeropuerto;
    }

    public void setIdAeropuerto(int idAeropuerto) {
        this.idAeropuerto = idAeropuerto;
    }

    /**
     * Establece el radio de Aeropuerto Privado como seleccionado
     * y ajusta la visibilidad de los componentes relacionados.
     */
    public void habilitarRadioPrivados() {
        rbPrivado.setSelected(true);
        rbPublico.setSelected(false);
        lblSocios.setVisible(true);
        txtSocios.setVisible(true);
        lblFinanciacion.setVisible(false);
        txtFinanciacion.setVisible(false);
        lblNumTrabajadores.setVisible(false);
        txtNumTrabajadores.setVisible(false);
    }

    /**
     * Establece el radio de Aeropuerto Público como seleccionado
     * y ajusta la visibilidad de los componentes relacionados.
     */
    public void habilitarRadioPublicos() {
        rbPrivado.setSelected(false);
        rbPublico.setSelected(true);
        lblSocios.setVisible(false);
        txtSocios.setVisible(false);
        lblFinanciacion.setVisible(true);
        txtFinanciacion.setVisible(true);
        lblNumTrabajadores.setVisible(true);
        txtNumTrabajadores.setVisible(true);
    }

    /**
     * Genera una ventana de alerta con el tipo de alerta, título y mensaje especificados.
     *
     * @param tipo el tipo de alerta
     * @param titulo el título de la ventana
     * @param mensaje el mensaje a mostrar
     * @return una instancia de Alert configurada
     */
    private Alert generarVentana(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setContentText(mensaje);
        return alert;
    }
}
