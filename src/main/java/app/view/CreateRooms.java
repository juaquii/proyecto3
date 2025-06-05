package app.view;

import app.model.dao.RoomDAO;
import app.App;
import app.model.entity.Room;
import app.model.entity.enums.TypeR;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class CreateRooms extends Controller implements Initializable {
    @FXML
    private VBox vBox;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TextField fieldCodRoom;
    @FXML
    private TextField fieldBeds;
    @FXML
    private TextField fieldWindows;
    @FXML
    private TextField fieldTypeR;
    @FXML
    private TextField fieldPrice;
    @FXML
    private Button createButton;

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
    private void createRooms() throws Exception {
        Room room = new Room();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Habitacion creada con exito");
        room.setCodRoom(fieldCodRoom.getText());
        String stringB = fieldBeds.getText();
        Integer.parseInt(stringB);
        room.setBeds(Integer.parseInt(stringB));

        String stringW = fieldWindows.getText();
        Integer.parseInt(stringW);
        room.setWindows(Integer.parseInt(stringW));

        room.setTypeR(TypeR.valueOf(fieldTypeR.getText()));

        String stringP = fieldPrice.getText();
        Integer.parseInt(stringP);
        room.setPrice(Integer.parseInt(stringP));

        RoomDAO.build().save(room);
        alert.showAndWait();
        App.currentController.changeScene(Scenes.ADMINMENUOPTIONS,null);
    }
}
