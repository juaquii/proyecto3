package controller;

import dao.UsuarioDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.Usuario;

public class RegisterController {
    @FXML
    private TextField nameField;
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;

    public void handleRegister() {
        String name = nameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();

        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            showAlert("Error", "All fields are required.");
            return;
        }

        Usuario usuario = new Usuario(0, name, email, Usuario.hashPassword(password));
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        if (usuarioDAO.save(usuario)) {
            showAlert("Success", "User registered successfully.");
        } else {
            showAlert("Error", "Failed to register user.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}