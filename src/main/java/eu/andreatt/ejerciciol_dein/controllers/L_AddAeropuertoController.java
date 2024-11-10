package eu.andreatt.ejerciciol_dein.controllers;

import eu.andreatt.ejerciciol_dein.dao.AeropuertosDao;
import eu.andreatt.ejerciciol_dein.dao.AeropuertosPrivadosDao;
import eu.andreatt.ejerciciol_dein.dao.AeropuertosPublicosDao;
import eu.andreatt.ejerciciol_dein.dao.DireccionesDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
            // Obtener los errores de los campos
            String errores = validarCampos();

            // Si hay errores, mostrar la alerta
            if (!errores.isEmpty()) {
                mostrarAlerta(AlertType.ERROR, "Errores en los campos", errores);
                return;
            }

            // Si no hay errores, proceder con el guardado
            String nombre = txtNombre.getText();
            String calle = txtCalle.getText();
            int numero = Integer.parseInt(txtNumero.getText());
            String ciudad = txtCiudad.getText();
            String pais = txtPais.getText();
            int anioInauguracion = Integer.parseInt(txtAnioInaguracion.getText());
            int capacidad = Integer.parseInt(txtCapaciad.getText());
            int numTrabajadores = rbPublico.isSelected() ? Integer.parseInt(txtNumTrabajadores.getText()) : 0;
            int socios = rbPrivado.isSelected() ? Integer.parseInt(txtSocios.getText()) : 0;
            double financiacion = rbPublico.isSelected() ? Double.parseDouble(txtFinanciacion.getText()) : 0;

            // Inicializar DAOs
            aeropuertosDao = new AeropuertosDao();
            direccionesDao = new DireccionesDao();
            aeropuertosPrivadosDao = new AeropuertosPrivadosDao();
            aeropuertosPublicosDao = new AeropuertosPublicosDao();

            // Verificar si la dirección ya existe
            int direccionId = direccionesDao.existeDireccion(pais, ciudad, calle, numero);
            if (direccionId == -1) {
                if (!direccionesDao.insertarDireccion(pais, ciudad, calle, numero)) {
                    mostrarAlerta(AlertType.ERROR, "Error", "Error al insertar la dirección.");
                    return;
                }
                direccionId = direccionesDao.existeDireccion(pais, ciudad, calle, numero);
            }

            // Insertar el aeropuerto
            boolean aeropuertoInsertado = aeropuertosDao.insertarAeropuerto(nombre, anioInauguracion, capacidad, direccionId);
            if (!aeropuertoInsertado) {
                mostrarAlerta(AlertType.ERROR, "Error", "Error al insertar el aeropuerto.");
                return;
            }

            // Obtener el ID del aeropuerto recién insertado
            int aeropuertoId = aeropuertosDao.dameIdDeAeropuerto(nombre, anioInauguracion, capacidad, direccionId);
            if (aeropuertoId == -1) {
                mostrarAlerta(AlertType.ERROR, "Error", "No se pudo obtener el ID del aeropuerto insertado.");
                return;
            }

            // Insertar según el tipo de aeropuerto
            if (rbPrivado.isSelected()) {
                if (!aeropuertosPrivadosDao.insertarAeropuertoPrivado(aeropuertoId, socios)) {
                    mostrarAlerta(AlertType.ERROR, "Error", "Error al insertar el aeropuerto privado.");
                    return;
                }
            } else if (rbPublico.isSelected()) {
                if (!aeropuertosPublicosDao.insertarAeropuertoPublico(aeropuertoId, financiacion, numTrabajadores)) {
                    mostrarAlerta(AlertType.ERROR, "Error", "Error al insertar el aeropuerto público.");
                    return;
                }
            }

            // Cerrar la ventana
            Stage stage = (Stage) btnGuardar.getScene().getWindow();
            stage.close();

        } catch (Exception e) {
            mostrarAlerta(AlertType.ERROR, "Error", "Error al guardar el aeropuerto: " + e.getMessage());
            e.printStackTrace(); // Para depuración en consola
        }
    }



    /**
     * Muestra una alerta con el tipo, título y mensaje proporcionados.
     *
     * @param tipo Tipo de alerta (ERROR, INFORMATION, etc.)
     * @param titulo Título de la alerta
     * @param mensaje Mensaje que se mostrará en la alerta
     */
    private void mostrarAlerta(AlertType tipo, String titulo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private String validarCampos() {
        StringBuilder errores = new StringBuilder();

        // Verificar campos obligatorios y con formato
        if (txtNombre.getText().isEmpty()) {
            errores.append("El campo 'Nombre' es obligatorio.\n");
        }
        if (txtCalle.getText().isEmpty()) {
            errores.append("El campo 'Calle' es obligatorio.\n");
        }
        if (txtCiudad.getText().isEmpty()) {
            errores.append("El campo 'Ciudad' es obligatorio.\n");
        }
        if (txtPais.getText().isEmpty()) {
            errores.append("El campo 'País' es obligatorio.\n");
        }

        // Verificar que los campos numéricos sean válidos
        try {
            Integer.parseInt(txtNumero.getText());
        } catch (NumberFormatException e) {
            errores.append("El campo 'Número' debe ser un número válido.\n");
        }

        try {
            Integer.parseInt(txtAnioInaguracion.getText());
        } catch (NumberFormatException e) {
            errores.append("El campo 'Año de Inauguración' debe ser un número válido.\n");
        }

        try {
            Integer.parseInt(txtCapaciad.getText());
        } catch (NumberFormatException e) {
            errores.append("El campo 'Capacidad' debe ser un número válido.\n");
        }

        // Verificar los campos que dependen de si es privado o público
        if (rbPublico.isSelected()) {
            try {
                Integer.parseInt(txtNumTrabajadores.getText());
            } catch (NumberFormatException e) {
                errores.append("El campo 'Número de Trabajadores' debe ser un número válido.\n");
            }
        }

        if (rbPrivado.isSelected()) {
            try {
                Integer.parseInt(txtSocios.getText());
            } catch (NumberFormatException e) {
                errores.append("El campo 'Socios' debe ser un número válido.\n");
            }
        }

        if (rbPublico.isSelected()) {
            try {
                Float.parseFloat(txtFinanciacion.getText());
            } catch (NumberFormatException e) {
                errores.append("El campo 'Financiación' debe ser un número válido.\n");
            }
        }

        // Devolver los errores encontrados, o un mensaje vacío si no hay errores
        return errores.toString();
    }


    /**
     * Metodo de inicialización de la interfaz.
     * Configura los componentes iniciales, si es necesario.
     */
    @FXML
    void initialize() { System.out.println(txtAnioInaguracion); }
}
