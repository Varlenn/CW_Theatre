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

public class UserController extends SceneController implements Initializable {

    @FXML
    private Button actors_button;
    @FXML
    private Button back_button;

    @FXML
    private Button buy_ticket_button;

    @FXML
    private Button exit_button;

    @FXML
    private Button perform_button;

    @FXML
    private Label discount_label;

    @FXML
    private Label name_bar_label;

    @FXML
    private Button pfp_button;

    @FXML
    private Label role_label;

    @FXML
    private Label name_label;

    @FXML
    private Button shows_button;

    @FXML
    private Button manage_button;

    @FXML
    private Label t_count_label;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        int role = User.authorizedUser.getUserRole();

        if(role > 1)
            manage_button.setVisible(false);

        pfp_button.graphicProperty().setValue(new ImageView("C:/Users/Varlenn/IdeaProjects/KP_Theatre/src/main/resources/assets/pfp-1.png"));
        name_bar_label.setText(User.authorizedUser.returnUserName());

        name_label.setText(User.authorizedUser.returnUserName());
        t_count_label.setText("Всего приобретено билетов: " + User.authorizedUser.getTicketsCount());


        if(role == 0)
            role_label.setText("Роль: администратор");
        else if(role == 1)
            role_label.setText("Роль: менеджер");
        else if(role == 2)
            role_label.setText("Роль: пользователь");

        if(!User.authorizedUser.getDiscount())
            discount_label.setText("Наличие скидки: нет");
        else
            discount_label.setText("Наличие скидки: есть");


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

        back_button.setOnAction(actionEvent -> {
            try {
                switchTo(actionEvent, "Home.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        exit_button.setOnAction(actionEvent -> {
            try {
                switchTo(actionEvent, "Authorization.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        manage_button.setOnAction(actionEvent -> {
            try {
                switchTo(actionEvent, "Manage.fxml");
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
