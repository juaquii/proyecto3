package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        loadView("/login.fxml");
    }

    public static void loadView(String fxmlPath) throws Exception {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource(fxmlPath));
        primaryStage.setScene(new Scene(loader.load()));
        primaryStage.setTitle("Application");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}