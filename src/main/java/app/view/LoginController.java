package app.view;

import app.model.dao.ClientDAO;
import app.model.entity.Client;
import app.App;
import app.model.session.Session;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController extends Controller implements Initializable {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button enterButton;
    @FXML
    private TextField fieldMail;
    @FXML
    private TextField fieldPassword;
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
    private void returnButton() throws Exception {
        App.currentController.changeScene(Scenes.MAIN, null);
    }

    @FXML
    private void enterButton() throws Exception {
        //Client client = new Client();
        //client.setMail(fieldMail.getText());
        //client.setPassword(fieldPassword.getText());

        Client client = ClientDAO.build().findByMail(fieldMail.getText());
        Session.getInstance().logIn(client);
        if (Session.getInstance().getUserLogged().getAdmin() == 0) {
            App.currentController.changeScene(Scenes.ADMINMENUOPTIONS,null);
        }
        else if (Session.getInstance().getUserLogged().getAdmin() > 0 && Session.getInstance().getUserLogged().getAdmin() < 10) {
            App.currentController.changeScene(Scenes.CLIENTMENUOPTIONS, null);
        }
    }
}