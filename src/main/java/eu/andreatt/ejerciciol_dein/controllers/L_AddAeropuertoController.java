package eu.andreatt.ejerciciol_dein.controllers;

import eu.andreatt.ejerciciol_dein.dao.AeropuertosDao;
import eu.andreatt.ejerciciol_dein.dao.AeropuertosPrivadosDao;
import eu.andreatt.ejerciciol_dein.dao.AeropuertosPublicosDao;
import eu.andreatt.ejerciciol_dein.dao.DireccionesDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * Controlador para gestionar la creación de un nuevo aeropuerto.
 * Proporciona la interfaz para seleccionar si el aeropuerto es privado o público
 * y captura la información necesaria para cada caso.
 */
public class L_AddAeropuertoController {

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
    private TextField txtAnioInauguracion;

    @FXML
    private TextField txtCalle;

    @FXML
    private TextField txtCapacidad;

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

    private AeropuertosDao aeropuertosDao;
    private DireccionesDao direccionesDao;
    private AeropuertosPrivadosDao aeropuertosPrivadosDao;
    private AeropuertosPublicosDao aeropuertosPublicosDao;

    /**
     * Metodo de acción cuando se selecciona el radio botón de aeropuerto privado.
     * Muestra los campos específicos de un aeropuerto privado y oculta los de un público.
     *
     * @param event el evento de acción
     */
    @FXML
    void actPrivado(ActionEvent event) {
        lblSocios.setVisible(true);
        txtSocios.setVisible(true);
        lblFinanciacion.setVisible(false);
        txtFinanciacion.setVisible(false);
        txtNumTrabajadores.setVisible(false);
        lblNumTrabajadores.setVisible(false);
    }

    /**
     * Metodo de acción cuando se selecciona el radio botón de aeropuerto público.
     * Muestra los campos específicos de un aeropuerto público y oculta los de un privado.
     *
     * @param event el evento de acción
     */
    @FXML
    void actPublico(ActionEvent event) {
        lblSocios.setVisible(false);
        txtSocios.setVisible(false);
        lblFinanciacion.setVisible(true);
        txtFinanciacion.setVisible(true);
        lblNumTrabajadores.setVisible(true);
        txtNumTrabajadores.setVisible(true);
    }

    /**
     * Metodo de acción para cancelar la operación de añadir un aeropuerto.
     * Cierra la ventana actual sin guardar cambios.
     *
     * @param event el evento de acción
     */
    @FXML
    void cancelar(ActionEvent event) {
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }

    /**
     * Metodo de acción para guardar el aeropuerto en la base de datos.
     * Verifica y captura los datos ingresados, los procesa, y realiza las inserciones en las tablas correspondientes
     * según el tipo de aeropuerto (privado o público).
     *
     * @param event el evento de acción
     */
    @FXML
    void guardar(ActionEvent event) {
        try {
            String nombre = txtNombre.getText();
            String calle = txtCalle.getText();
            int numero = Integer.parseInt(txtNumero.getText());
            String ciudad = txtCiudad.getText();
            String pais = txtPais.getText();
            int anioInauguracion = Integer.parseInt(txtAnioInauguracion.getText());
            int capacidad = Integer.parseInt(txtCapacidad.getText());
            int numTrabajadores = Integer.parseInt(txtNumTrabajadores.getText());
            int socios = rbPrivado.isSelected() ? Integer.parseInt(txtSocios.getText()) : 0; // Solo si es privado
            float financiacion = rbPublico.isSelected() ? Float.parseFloat(txtFinanciacion.getText()) : 0; // Solo si es público

            // Inicializar DAOs
            aeropuertosDao = new AeropuertosDao();
            direccionesDao = new DireccionesDao();
            aeropuertosPrivadosDao = new AeropuertosPrivadosDao();
            aeropuertosPublicosDao = new AeropuertosPublicosDao();

            // Verificar si la dirección ya existe
            int direccionId = direccionesDao.existeDireccion(pais, ciudad, calle, numero);

            // Si la dirección no existe, insertarla
            if (direccionId == -1) { // Cambiar 0 por -1 para verificar existencia
                if (!direccionesDao.insertarDireccion(pais, ciudad, calle, numero)) {
                    System.out.println("Error al insertar la dirección.");
                    return; // Salir del método si no se puede insertar la dirección
                }
                direccionId = direccionesDao.existeDireccion(pais, ciudad, calle, numero); // Obtener el nuevo ID
            }

            // Insertar el aeropuerto basado en si es privado o público
            aeropuertosDao.insertarAeropuerto(nombre, anioInauguracion, capacidad, direccionId);
            if (rbPrivado.isSelected()) {
                aeropuertosPrivadosDao.insertarAeropuertoPrivado(aeropuertosDao.dameIdDeAeropuerto(nombre, anioInauguracion, capacidad, direccionId), socios);
            } else {
                aeropuertosPublicosDao.insertarAeropuertoPublico(aeropuertosDao.dameIdDeAeropuerto(nombre, anioInauguracion, capacidad, direccionId), financiacion, numTrabajadores);
            }

            // Cerrar la ventana
            Stage stage = (Stage) btnGuardar.getScene().getWindow();
            stage.close();

        } catch (NumberFormatException e) {
            System.out.println("Error: Entrada inválida. Por favor, verifique los datos ingresados.");
        } catch (Exception e) {
            System.out.println("Error al guardar el aeropuerto: " + e.getMessage());
        }
    }

    /**
     * Metodo de inicialización de la interfaz.
     * Configura los componentes iniciales, si es necesario.
     */
    @FXML
    void initialize() {}
}
