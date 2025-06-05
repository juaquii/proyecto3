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

public class UpdateDataFromClient extends Controller implements Initializable {
    @FXML
    private VBox vBox;
    @FXML
    private AnchorPane anchorPane;
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
    private Button updateButton;
    @FXML
    private ImageView returnButton;

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
    private void updateData() throws Exception {
        Client client = new Client();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Sus datos han sido actualizados con exito");
        client.setDni(fieldName.getText());
        client.setSurnames(fieldSurnames.getText());
        client.setPhone(fieldPhone.getText());
        client.setMail(fieldMail.getText());
        client.setPassword(fieldPassword.getText());
        ClientDAO.build().update(client);
        alert.showAndWait();
        App.currentController.changeScene(Scenes.CLIENTMENUOPTIONS, null);
    }

}
