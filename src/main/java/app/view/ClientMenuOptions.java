package app.view;

import app.model.dao.ReserveDAO;
import app.model.entity.Reserve;
import app.App;
import app.model.entity.Reserve2;
import app.model.session.Session;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class ClientMenuOptions extends Controller implements Initializable {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button deleteReserves;
    @FXML
    private Button findAllReserves;
    @FXML
    private Button updateData;
    @FXML
    private Button createReserve;
    @FXML
    private TableView<Reserve2> tableViewReserve;
    @FXML
    private TableColumn<Reserve, String> columnCodReserve;
    @FXML
    private TableColumn<Reserve, Date> columnDate;
    @FXML
    private TableColumn<Reserve, String> columnCodRoom;
    @FXML
    private ImageView closeSession;
    private ObservableList<Reserve2> reserves;


    @Override
    public void onOpen(Object input) throws IOException {

    }

    @Override
    public void onClose(Object output) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        columnCodReserve.setCellValueFactory(reserve -> new SimpleStringProperty(reserve.getValue().getCodReserve()));
        columnDate.setCellValueFactory(reserve -> new SimpleObjectProperty<>(reserve.getValue().getDate()));
        columnCodRoom.setCellValueFactory(reserve -> new SimpleStringProperty(reserve.getValue().getRoom().getCodRoom()));
    }

    @FXML
    private void goToDeleteReserves() throws Exception {
        App.currentController.openModal(Scenes.DELETERESERVESFROMCLIENT, "Borrar reservas de la lista", this, null);
    }

    @FXML
    private void goToUpdateDataFromClient() throws Exception {
        App.currentController.openModal(Scenes.UPDATEDATAFROMCLIENT, "Actualizar datos ", this, null);
    }

    @FXML
    private void goToCreateReserve() throws Exception {
        App.currentController.openModal(Scenes.CREATERESERVE, "Crear reservas", this, null);
    }



    @FXML
    private void findAllReserves() {
        List<Reserve2> reserves = ReserveDAO.build().findByReservesByClient();
        this.reserves = FXCollections.observableList(reserves);
        tableViewReserve.setItems(this.reserves);
    }
    @FXML
    private void logOut(){
        Session.LogOut();
        System.exit(0);
    }
}
