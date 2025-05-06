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
    private ListView<Usuario> usuarioListView;

    private UsuarioDAO usuarioDAO;
    private ObservableList<Usuario> usuarios;

    public void initialize() {
        usuarioDAO = new UsuarioDAO();
        usuarios = FXCollections.observableArrayList(usuarioDAO.obtenerUsuarios());
        usuarioListView.setItems(usuarios);
    }

    @FXML
    private void agregarUsuario() {
        String nombre = nombreField.getText();
        String email = emailField.getText();
        usuarioDAO.agregarUsuario(nombre, email);
        usuarios.setAll(usuarioDAO.obtenerUsuarios());
    }
}
