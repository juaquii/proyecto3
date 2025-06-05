package app.view;

import app.model.dao.RoomDAO;
import app.App;
import app.model.entity.Room;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DeleteRoom extends Controller implements Initializable {
    @FXML
    private VBox vBox;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TextField fieldCodRoom;
    @FXML
    private Button deleteButton;
    @FXML
    private ComboBox<Room> ComboBoxRoom;
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
    private void deleteRoom() throws Exception {
        Room room = new Room();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Habitacion borrada con exito");
        room.setCodRoom(fieldCodRoom.getText());
        RoomDAO.build().delete(room);
        alert.showAndWait();
        App.currentController.changeScene(Scenes.ADMINMENUOPTIONS,null);
    }
}
