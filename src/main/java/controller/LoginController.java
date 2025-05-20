package controller;

import dao.UsuarioDAO;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.Usuario;

public class LoginController {
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;

    public void handleLogin() {
        String email = emailField.getText();
        String password = passwordField.getText();

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.findByEmail(email);

        if (usuario != null && Usuario.hashPassword(password).equals(usuario.getPassword())) {
            System.out.println("Login successful!");
            // Navigate to the main application view
        } else {
            System.out.println("Invalid credentials.");
        }
    }
}