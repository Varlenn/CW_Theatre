package application;

import database.connection.DBConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class TheatreAppRun extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        DBConnection dbConn = new DBConnection();
        dbConn.connect();

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/scenes/Authorization.fxml")));
        Scene scene = new Scene(root,1200, 800);
        stage.setTitle("EXPERIMENTAL Theatre");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}