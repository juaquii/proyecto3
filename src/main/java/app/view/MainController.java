package app.view;

import app.App;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class MainController extends Controller implements Initializable {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button loginButton;
    @FXML
    private Button registrerButton;
    @FXML
    private ImageView exitButton;



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
    private void closeApp() {
        System.exit(0);
    }

    @FXML
    private void goToRegistrer() throws Exception {
        App.currentController.changeScene(Scenes.REGISTRER,null);
    }
    @FXML
    private void goToLogin() throws Exception {
        App.currentController.changeScene(Scenes.LOGIN,null);
    }

}
