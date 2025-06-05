package app.view;

public enum Scenes {
    ROOT("view/layout.fxml"),
    MAIN("view/main.fxml"),
    REGISTRER("view/registrer.fxml"),
    LOGIN("view/login.fxml"),
    ADMINMENUOPTIONS("view/adminMenuOptions.fxml"),
    CREATEROOMS("view/createRooms.fxml"),
    DELETECLIENTS("view/deleteClients.fxml"),
    UPDATEDATAFROMADMIN("view/updateDataFromAdmin.fxml"),
    DELETEROOM("view/deleteRoom.fxml"),
    CLIENTMENUOPTIONS("view/clientMenuOptions.fxml"),
    DELETERESERVESFROMCLIENT("view/deleteReservesFromClient.fxml"),
    UPDATEDATAFROMCLIENT("view/updateDataFromClient.fxml"),
    CREATERESERVE("view/createReserveFromClient.fxml");

    private final String url;

    Scenes(String url) {
        this.url = url;
    }

    public String getURL() {
        return url;
    }
}
