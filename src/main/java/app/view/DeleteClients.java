package app.view;

import app.model.dao.ClientDAO;
import app.model.entity.Client;
import app.App;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class DeleteClients extends Controller implements Initializable {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TextField fieldDni;
    @FXML
    private Button deleteButton;
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
    private void deleteClient() throws Exception {
        Client client = new Client();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Cliente borrado con exito");
        client.setDni(fieldDni.getText());
        ClientDAO.build().delete(client);
        alert.showAndWait();
        App.currentController.changeScene(Scenes.ADMINMENUOPTIONS,null);
    }
}
