package application.controllers;

import database.connection.DBConnection;
import database.tables.Show;
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

public class ShowController extends SceneController implements Initializable {

    @FXML
    private Button actors_button;

    @FXML
    private Button buy_ticket_button;

    @FXML
    private TableColumn<Show, String> per_name;

    @FXML
    private Button perform_button;

    @FXML
    private Label name_bar_label;

    @FXML
    private TableColumn<Show, String> show_date;

    @FXML
    private TableColumn<Show, String> show_time;

    @FXML
    private Button pfp_button;

    @FXML
    private TableView<Show> tableUser;

    @FXML
    private Button to_home_button;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        pfp_button.graphicProperty().setValue(new ImageView("C:/Users/Varlenn/IdeaProjects/KP_Theatre/src/main/resources/assets/pfp-1.png"));
        name_bar_label.setText(User.authorizedUser.returnUserName());

        try {
            showShow();
        } catch (SQLException e) {
            e.printStackTrace();
        }

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

    public void showShow() throws SQLException {
        per_name.setCellValueFactory(new PropertyValueFactory<Show, String>("perName"));
        show_date.setCellValueFactory(new PropertyValueFactory<Show, String>("showDate"));
        show_time.setCellValueFactory(new PropertyValueFactory<Show, String>("showTime"));

        tableUser.setItems(getShowList());
    }

    public ObservableList<Show> getShowList() throws SQLException {

        ObservableList<Show> showList = FXCollections.observableArrayList();

        String query = "select p.per_name, s.show_date, s.show_time  from show s " +
                "join performance p on s.id_per = p.id_per";
        ResultSet res = DBConnection.inst.getRSet(query);

        Show show;
        while(res.next()) {
            show = new Show(res.getString(1), res.getDate(2),
                    res.getTime(3));
            showList.add(show);
        }
        return showList;
    }
}
