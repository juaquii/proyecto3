package app.view;

import app.model.dao.ClientDAO;
import app.model.entity.Client;
import app.App;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class RegistrerController extends Controller implements Initializable {
    @FXML
    private VBox vBox;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TextField fieldDni;
    @FXML
    private TextField fieldName;
    @FXML
    private TextField fieldSurnames;
    @FXML
    private TextField fieldPhone;
    @FXML
    private TextField fieldMail;
    @FXML
    private TextField fieldPassword;
    @FXML
    private TextField fieldAdmin;
    @FXML
    private Button registrerButton;
    @FXML
    private ImageView returnButton;
    private ClientDAO clientDAO = new ClientDAO();

    @Override
    public void onOpen(Object input) throws IOException {

    }

    @Override
    public void onClose(Object output) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private void registerClient() throws Exception {
        Client client = new Client();
        client.setDni(fieldDni.getText());
        client.setName(fieldName.getText());
        client.setSurnames(fieldSurnames.getText());
        client.setPhone(fieldPhone.getText());
        client.setMail(fieldMail.getText());
        client.setPassword(fieldPassword.getText());
        client.setAdmin(Integer.parseInt(fieldAdmin.getText()));
        Alert alertSuccess = new Alert(Alert.AlertType.CONFIRMATION, "Registrado con exito");
        Alert alertError = new Alert(Alert.AlertType.ERROR,
                "-El DNI es nulo o tiene más de 10 caracteres \n" +
                        "-El nombre es demasiado largo \n" +
                        "-Los apellidos son demasiado largos \n" +
                        "-El teléfono esta registrado en el sistema o es nulo o es demasiado largo \n" +
                        "-El mail es nulo , esta registrado o no cumple con los requisitos o es demasiado largo \n" +
                        "-La contraseña no cumple con los requisitos de seguridad o es nula o es demasiado larga \n" +
                        "-El numero de admin es negativo o mayor a 10 o es nulo \n");


        if (client.getDni().isEmpty() || client.getDni().length() > 10) {
            alertError.showAndWait();
            return;
        }

        if (client.getName().length() > 20) {
            alertError.showAndWait();
            return;
        }
        if (client.getSurnames().length() > 50) {
            alertError.showAndWait();
            return;
        }
        if (client.getPhone().length() > 9 || client.getPhone().isEmpty()) {
            alertError.showAndWait();
            return;
        }
        Client clientOnDB = clientDAO.findByMail(client.getMail());
        if (clientOnDB != null) {
            alertError.showAndWait();
            return;
        } else if (client.getMail().length() > 40 || client.getMail().isEmpty()) {
            alertError.showAndWait();
            return;
        }

        if (client.getPassword().length() > 140 || client.getPassword().isEmpty()) {
            alertError.showAndWait();
            return;
        }
        if (client.getAdmin() < 0 || client.getAdmin() > 10) {
            alertError.showAndWait();
            return;
        }
        ClientDAO.build().save(client);
        alertSuccess.showAndWait();
        App.currentController.changeScene(Scenes.MAIN, null);
    }

    @FXML
    private void goToMainController() throws Exception {
        App.currentController.changeScene(Scenes.MAIN, null);

    }

}
