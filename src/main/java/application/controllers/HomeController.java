package application.controllers;

import database.tables.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController extends SceneController implements Initializable {

    @FXML
    private Button actors_button;

    @FXML
    private Button buy_ticket_button;

    @FXML
    private Label name_bar_label;

    @FXML
    private Button perform_button;

    @FXML
    private Button shows_button;

    @FXML
    private Button pfp_button;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        pfp_button.graphicProperty().setValue(new ImageView("C:/Users/Varlenn/IdeaProjects/KP_Theatre/src/main/resources/assets/pfp-1.png"));
        name_bar_label.setText(User.authorizedUser.returnUserName());

        buy_ticket_button.setOnAction(actionEvent -> {
            try {
                switchTo(actionEvent, "Ticket.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        shows_button.setOnAction(actionEvent -> {
            try {
                switchTo(actionEvent, "Show.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        perform_button.setOnAction(actionEvent -> {
            try {
                switchTo(actionEvent, "Performance.fxml");
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
