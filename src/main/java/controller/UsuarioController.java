package controller;

import dao.UsuarioDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.Usuario;

public class UsuarioController {
    @FXML
    private TextField nombreField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField passwordField;
    @FXML
    private ListView<Usuario> usuarioListView;

    private UsuarioDAO usuarioDAO;
    private ObservableList<Usuario> usuarios;

    public UsuarioController() {
        usuarioDAO = new UsuarioDAO();
        usuarios = FXCollections.observableArrayList();
        usuarioListView.setItems(usuarios);
    }

    public Usuario registrarUsuario() {
        String nombre = nombreField.getText();
        String usuario = emailField.getText();
        String contrasena = passwordField.getText();
        String contrasenaHash = Usuario.hashPassword(contrasena);
        String correoElectronico = emailField.getText();

        // Verificar si el usuario ya existe
        if (usuarioDAO.buscarUsuario(usuario, contrasenaHash) != null) {
            // Usuario ya existe, no crear un nuevo usuario
            return null;
        }

        // Crear un nuevo usuario
        Usuario nuevoUsuario = new Usuario(usuario, contrasenaHash, nombre, correoElectronico, false);

        // Guardar el usuario en la base de datos
        usuarioDAO.guardarUsuario(nuevoUsuario);

        // Actualizar la lista de usuarios
        usuarios.add(nuevoUsuario);

        return nuevoUsuario;
    }
}