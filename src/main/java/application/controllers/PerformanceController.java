package application.controllers;

import database.tables.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PerformanceController extends SceneController implements Initializable {

    @FXML
    private AnchorPane PerformanceTable;

    @FXML
    private Button actors_button;

    @FXML
    private Button buy_ticket_button;

    @FXML
    private Label name_bar_label;

    @FXML
    private Button show_button;

    @FXML
    private Button pfp_button;

    @FXML
    private Button to_home_button;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        pfp_button.graphicProperty().setValue(new ImageView("C:/Users/Varlenn/IdeaProjects/KP_Theatre/src/main/resources/assets/pfp-1.png"));
        name_bar_label.setText(User.authorizedUser.returnUserName());

        to_home_button.setOnAction(actionEvent -> {
            try {
                switchTo(actionEvent,"Home.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        buy_ticket_button.setOnAction(actionEvent -> {
            try {
                switchTo(actionEvent, "Ticket.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        show_button.setOnAction(actionEvent -> {
            try {
                switchTo(actionEvent, "Show.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        actors_button.setOnAction(actionEvent -> {
            try {
                switchTo(actionEvent, "Actors.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        pfp_button.setOnAction(actionEvent -> {
            try {
                switchTo(actionEvent, "User.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
