package eu.andreatt.ejerciciol_dein.controllers;

import eu.andreatt.ejerciciol_dein.application.L_ListadoAeropuertos;
import eu.andreatt.ejerciciol_dein.dao.UsuariosDAO;
import eu.andreatt.ejerciciol_dein.model.Usuarios;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controlador para la ventana de inicio de sesión.
 * Este controlador maneja la lógica de autenticación del usuario.
 */
public class L_LoginController {

    @FXML
    private Button btnLogin;  // Botón para iniciar sesión

    @FXML
    private PasswordField txtPassw;  // Campo para la contraseña del usuario

    @FXML
    private TextField txtUser;  // Campo para el nombre de usuario

    /**
     * Maneja el evento de clic en el botón de inicio de sesión.
     * Valida las entradas del usuario y verifica las credenciales en la base de datos.
     *
     * @param event El evento de acción que desencadena este método.
     */
    @FXML
    void login(ActionEvent event) {
        // Obtener los valores ingresados
        String usuario = txtUser.getText().trim();
        String password = txtPassw.getText().trim();

        // Validaciones
        if (usuario.isEmpty()) {
            mostrarAlerta("Introduce Usuario", Alert.AlertType.WARNING);
        } else if (password.isEmpty()) {
            mostrarAlerta("Introduce Password", Alert.AlertType.WARNING);
        } else {
            // Crear objeto Usuarios y verificar en la base de datos
            Usuarios usuarioObj = new Usuarios(usuario, password);
            UsuariosDAO usuarioDAO = new UsuariosDAO();

            if (usuarioDAO.verificarUsuario(usuarioObj)) {
                System.out.println("LOGIN CORRECTO");

                // Cerrar ventana modal
                Stage stage = (Stage) btnLogin.getScene().getWindow();
                stage.close();

                new L_ListadoAeropuertos();  // Abrir la ventana de listado de aeropuertos
            } else {
                mostrarAlerta("El usuario o la contraseña son incorrectos", Alert.AlertType.ERROR);
            }
        }
    }

    /**
     * Muestra una alerta con un mensaje específico.
     *
     * @param mensaje El mensaje a mostrar en la alerta.
     * @param tipo El tipo de alerta (advertencia, error, etc.).
     */
    private void mostrarAlerta(String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setHeaderText(null);  // No se muestra un encabezado en la alerta
        alert.setContentText(mensaje);  // Establecer el mensaje en el contenido
        alert.showAndWait();  // Mostrar la alerta y esperar a que el usuario la cierre
    }
}
