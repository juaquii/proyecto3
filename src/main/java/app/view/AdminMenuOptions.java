package app.view;

import app.model.dao.ClientDAO;
import app.model.dao.RoomDAO;
import app.model.entity.Client;
import app.App;
import app.model.entity.Room;

import app.model.entity.enums.TypeR;
import app.model.session.Session;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.converter.IntegerStringConverter;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AdminMenuOptions extends Controller implements Initializable {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button findClients;
    @FXML
    private Button deleteClients;
    @FXML
    private Button updateData;
    @FXML
    private Button createRooms;
    @FXML
    private ComboBox<String> TypeRComboBox;
    @FXML
    private Button deleteRooms;
    @FXML
    private Button findAllRooms;
    @FXML
    private TableView<Client> tableViewClient;
    @FXML
    private TableColumn<Client, String> columnDNI;
    @FXML
    private TableColumn<Client, String> columnName;
    @FXML
    private TableColumn<Client, String> columnSurnames;
    @FXML
    private TableColumn<Client, String> columnPhone;
    @FXML
    private TableColumn<Client, String> columnMail;
    @FXML
    private TableColumn<Client, Integer> columnAdmin;
    @FXML
    private TableView<Room> tableViewRoom;
    @FXML
    private TableColumn<Room, String> columnCodRoom;
    @FXML
    private TableColumn<Room, Integer> columnBeds;
    @FXML
    private TableColumn<Room, Integer> columnWindows;
    @FXML
    private TableColumn<Room, String> columnTypeR;
    @FXML
    private TableColumn<Room, Integer> columnPrice;
    @FXML
    private ImageView closeSession;
    private ObservableList<Client> clients;
    private ObservableList<Room> rooms;

    @Override
    public void onOpen(Object input) throws IOException {

    }

    @Override
    public void onClose(Object output) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        columnDNI.setCellValueFactory(clients -> new SimpleStringProperty(clients.getValue().getDni()));
        columnName.setCellValueFactory(clients -> new SimpleStringProperty(clients.getValue().getName()));
        columnSurnames.setCellValueFactory(clients -> new SimpleStringProperty(clients.getValue().getSurnames()));
        columnPhone.setCellValueFactory(clients -> new SimpleStringProperty(clients.getValue().getPhone()));
        columnMail.setCellValueFactory(clients -> new SimpleStringProperty(clients.getValue().getMail()));
        columnAdmin.setCellValueFactory(clients -> new SimpleIntegerProperty(clients.getValue().getAdmin()).asObject());

        tableViewRoom.setEditable(true);
        columnCodRoom.setCellValueFactory(rooms -> new SimpleStringProperty(rooms.getValue().getCodRoom()));

        columnBeds.setCellValueFactory(rooms -> new SimpleIntegerProperty(rooms.getValue().getBeds()).asObject());
        columnBeds.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        columnWindows.setCellValueFactory(rooms -> new SimpleIntegerProperty(rooms.getValue().getWindows()).asObject());
        columnWindows.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        columnTypeR.setCellValueFactory(rooms -> new SimpleStringProperty(rooms.getValue().getTypeR().toString()));
        columnTypeR.setCellFactory(TextFieldTableCell.forTableColumn());


        columnPrice.setCellValueFactory(rooms -> new SimpleIntegerProperty(rooms.getValue().getPrice()).asObject());
        columnPrice.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        columnBeds.setOnEditCommit(event -> {
            if (event.getNewValue() == event.getOldValue()) {
                return;
            }

            if (event.getNewValue() > 0 && event.getNewValue() < 10) {
                Room roomUpdated = event.getRowValue();
                roomUpdated.setBeds(event.getNewValue());
                RoomDAO.build().update(roomUpdated);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Escribe un número de camas, minimo 1 y maximo 9");
                alert.show();
            }
        });
        columnWindows.setOnEditCommit(event -> {
            if (event.getNewValue() == event.getOldValue()) {
                return;
            }

            if (event.getNewValue() > 0 && event.getNewValue() < 10) {
                Room roomUpdated = event.getRowValue();
                roomUpdated.setWindows(event.getNewValue());
                RoomDAO.build().update(roomUpdated);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Escribe un número de ventanas, mínimo 1 y máximo 9");
                alert.show();
            }
        });
        columnPrice.setOnEditCommit(event -> {
                if (event.getNewValue() == event.getOldValue()) {
                    return;
                }
                if (event.getNewValue() > 0 && event.getNewValue() < 9999) {
                    Room roomUpdated = event.getRowValue();
                    roomUpdated.setPrice(event.getNewValue());
                    RoomDAO.build().update(roomUpdated);
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Escribe un precio entre 1 y 9999");
                    alert.show();
                }
        });
        columnTypeR.setOnEditCommit(event -> {
            if (event.getNewValue() == event.getOldValue()) {
                return;
            }
            if (event.getNewValue() == null || event.getNewValue().equals(event.getOldValue())) {
                Room roomUpdated = event.getRowValue();
                roomUpdated.setTypeR(TypeR.valueOf(event.getNewValue()));
                RoomDAO.build().update(roomUpdated);
            }
        });
        TypeRComboBox.setItems(FXCollections.observableArrayList("DELUXE", "BIGGER", "SUITE"));
    }


    @FXML
    private void goToDeleteClients() throws Exception {
        App.currentController.openModal(Scenes.DELETECLIENTS, "Eliminar cliente", this, null);
    }

    @FXML
    private void goToCreateRooms() throws Exception {
        App.currentController.openModal(Scenes.CREATEROOMS, "Crear habitacion", this, null);
    }

    @FXML
    private void goToDeleteRooms() throws Exception {
        App.currentController.openModal(Scenes.DELETEROOM, "Eliminar habitacion", this, null);
    }
    @FXML
    private void goToUpdateDataFromAdmin() throws Exception{
        App.currentController.openModal(Scenes.UPDATEDATAFROMADMIN,"Actualizar datos", this, null);
    }

    @FXML
    private void findAllClients() {
        List<Client> clients = ClientDAO.build().findAll();
        this.clients = FXCollections.observableList(clients);
        tableViewClient.setItems(this.clients);
    }

    @FXML
    private void findAllRooms() {
        List<Room> rooms = RoomDAO.build().findAll();
        this.rooms = FXCollections.observableList(rooms);
        tableViewRoom.setItems(this.rooms);
    }
    @FXML
    private void updateTypeR(){

    }
    @FXML
    private void logOut(){
        Session.LogOut();
        System.exit(0);
    }
}
