package application.controllers;

import database.connection.DBConnection;
import database.tables.Performance;
import database.tables.Show;
import database.tables.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Set;

public class TicketController extends SceneController implements Initializable {

    @FXML
    private Label add_label;

    @FXML
    private Button back_button;

    @FXML
    private ComboBox<String> per_scroll;

    @FXML
    private Button buy_button;

    @FXML
    private ComboBox<String> date_scroll;

    @FXML
    private Button pfp_button;

    @FXML
    private ComboBox<Integer> seat_scroll;

    @FXML
    private Label name_bar_label;

    @FXML
    private Label price_label;

    @FXML
    private ComboBox<Integer> row_scroll;

    @FXML
    private ComboBox<String> time_scroll;

    private Integer id_per = 0;
    private String per = "Выберите спектакль";
    private String date = "2009-01-01";
    private String time = "00:00:00";
    private Integer row_ = 0;
    private Integer seat = 0;
    private Double price = 0.0;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        pfp_button.graphicProperty().setValue(new ImageView("C:/Users/Varlenn/IdeaProjects/KP_Theatre/src/main/resources/assets/pfp-1.png"));
        name_bar_label.setText(User.authorizedUser.returnUserName());

        if(!User.authorizedUser.getDiscount())
            price = 1600.0;
        else
            price = 1360.0;

        date_scroll.setVisibleRowCount(4);
        time_scroll.setVisibleRowCount(4);
        row_scroll.setVisibleRowCount(4);
        seat_scroll.setVisibleRowCount(4);

        price_label.setText("Цена " + price);

        try {
            per_scroll.getItems().addAll(getPer());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            date_scroll.getItems().addAll(getDates());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
           time_scroll.getItems().addAll(getTimes());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            row_scroll.getItems().addAll(getRows());
        } catch (SQLException e) {
            e.printStackTrace();
        }


        date_scroll.setOnAction(event -> {
            date = date_scroll.getSelectionModel().getSelectedItem();

            try {
                time_scroll.getItems().addAll(getTimes());
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                seat_scroll.getItems().addAll(getSeats());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        time_scroll.setOnAction(event -> {
            time = time_scroll.getSelectionModel().getSelectedItem();

            try {
                seat_scroll.getItems().addAll(getSeats());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        row_scroll.setOnAction(event -> {
            row_ = row_scroll.getSelectionModel().getSelectedItem();

            try {
                seat_scroll.getItems().addAll(getSeats());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        per_scroll.setOnAction(event -> {
            per = per_scroll.getSelectionModel().getSelectedItem();

            try {
                date_scroll.getItems().addAll(getDates());
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                time_scroll.getItems().addAll(getTimes());
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                seat_scroll.getItems().addAll(getSeats());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        seat_scroll.setOnAction(event -> {
            seat = seat_scroll.getSelectionModel().getSelectedItem();
        });


        buy_button.setOnAction(actionEvent -> {
            try {
                newTicket();
                newTicketsCount();
                switchTo(actionEvent, "SuccessTransaction.fxml");
            } catch (IOException | SQLException e) {
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

        pfp_button.setOnAction(actionEvent -> {
            try {
                switchTo(actionEvent, "User.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }


    private ObservableList<String> getPer() throws SQLException {
        ObservableList<String> perList = FXCollections.observableArrayList();

        String query = "select per_name from performance";
        ResultSet res = DBConnection.inst.getRSet(query);

        while(res.next()) {
            perList.add(res.getString(1));
        }
        return perList;
    }

    private Integer getIDPer() throws SQLException {
        ObservableList<String> idPerList = FXCollections.observableArrayList();

        String query = "select id_per from performance where per_name = '" + per + "'";
        ResultSet res = DBConnection.inst.getRSet(query);

        Performance per = null;
        while (res.next()) {
            per = new Performance(res.getInt("id_per"));
        }

        assert per != null;
        return per.getIdPer();
    }

    private ObservableList<String> getDates() throws SQLException {
        ObservableList<String> dateList = FXCollections.observableArrayList();

        String query = "select distinct show_date from show where id_per in (select id_per from performance " +
                "where per_name = '" + per + "') order by show_date asc";
        ResultSet res = DBConnection.inst.getRSet(query);

        while(res.next()) {
            dateList.add(res.getString(1));
        }
        return dateList;
    }

    private ObservableList<String> getTimes() throws SQLException {
        ObservableList<String> dateList = FXCollections.observableArrayList();

        String query = "select distinct show_time from show where show_date = '" + date + "' order by show_time asc";
        ResultSet res = DBConnection.inst.getRSet(query);

        while(res.next()) {
            dateList.add(res.getString(1));
        }
        return dateList;
    }

    private ObservableList<Integer> getRows() throws SQLException {
        ObservableList<Integer> rowList = FXCollections.observableArrayList();

        for(int i = 1; i <= 20; i++) {
            rowList.add(i);
        }
        return rowList;
    }


    private ObservableList<Integer> getSeats() throws SQLException {
        ObservableList<Integer> rowList = FXCollections.observableArrayList();

        String query = "select seat from tickets where row = " + row_ + " and id_show in" +
                "(select id_show from show where show_date = '" + date + "' and show_time = '" + time + "' " +
                "and id_per in (select id_per from performance where per_name = '" + per + "'))";
        ResultSet res = DBConnection.inst.getRSet(query);
        System.out.println(query);

        Set<Integer> noFreeSeats = new HashSet<>();
        while(res.next()) {
            noFreeSeats.add(res.getInt("seat"));
        }
        for(int i = 1; i <= 34; i++) {
            if (noFreeSeats.contains(i)) continue;
            rowList.add(i);
        }

        return rowList;
    }


    private void newTicket() throws SQLException {


        String query = "insert into tickets(id_show, id_user, row, seat, price) values(" + getIDPer() + ", '" +
                User.authorizedUser.getIdUser() + "', " + row_ + ", " + seat + ", "+ price + ")";
        System.out.println(query);
        DBConnection.inst.executeQuery(query);
    }

    private void  newTicketsCount() throws SQLException {
        User.authorizedUser.setTicketsCount(User.authorizedUser.getTicketsCount() + 1);
    }
}