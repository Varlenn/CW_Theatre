package application.controllers;

import database.connection.DBConnection;
import database.tables.Actor;
import database.tables.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ActorsController extends SceneController implements Initializable {

    @FXML
    private TableColumn<Actor, String> actor_name;

    @FXML
    private Label name_bar_label;

    @FXML
    private Button buy_ticket_button;

    @FXML
    private TableColumn<Actor, String> per_name;

    @FXML
    private Button perform_button;

    @FXML
    private Button show_button;

    @FXML
    private Button pfp_button;

    @FXML
    private TableView<Actor> tableActors;

    @FXML
    private Button to_home_button;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        pfp_button.graphicProperty().setValue(new ImageView("C:/Users/Varlenn/IdeaProjects/KP_Theatre/src/main/resources/assets/pfp-1.png"));
        name_bar_label.setText(User.authorizedUser.returnUserName());

        try {
            showActors();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        to_home_button.setOnAction(actionEvent -> {
            try {
                switchTo(actionEvent, "Home.fxml");
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

        perform_button.setOnAction(actionEvent -> {
            try {
                switchTo(actionEvent, "Performance.fxml");
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

        pfp_button.setOnAction(actionEvent -> {
            try {
                switchTo(actionEvent, "User.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void showActors() throws SQLException {
        actor_name.setCellValueFactory(new PropertyValueFactory<Actor, String>("actorName"));
        per_name.setCellValueFactory(new PropertyValueFactory<Actor, String>("perName"));

        tableActors.setItems(getActorsList());
    }

    public ObservableList<Actor> getActorsList() throws SQLException {

        ObservableList<Actor> actorList = FXCollections.observableArrayList();

        String query = "select actor_ln || ' ' || actor_fn, per_name from actors " +
                "join roles on roles.id_actor = actors.id_actor " +
                "join performance on performance.id_per = roles.id_per";
        ResultSet res = DBConnection.inst.getRSet(query);

        Actor actor;
        while(res.next()) {
            actor = new Actor(res.getString(1), res.getString(2));
            actorList.add(actor);
        }
        return actorList;
    }
}