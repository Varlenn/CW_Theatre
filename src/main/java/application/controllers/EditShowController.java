package application.controllers;

import database.connection.DBConnection;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EditShowController extends ManageController implements Initializable {

    @FXML
    private Label add_label;

    @FXML
    private TextField date_field;

    @FXML
    private TextField id_per_field;

    @FXML
    private TextField id_show_field;

    @FXML
    private Button insert_button;

    @FXML
    private TextField time_field;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(ManageController.upd) {
            id_show_field.setText(String.valueOf(ManageController.show.getIdShow()));
            id_per_field.setText(String.valueOf(ManageController.show.getIdPer()));
            date_field.setText(ManageController.show.getShowDate());
            time_field.setText(String.valueOf(ManageController.show.getShowTime()));
        }

        insert_button.setOnAction(actionEvent -> {
            try {
                insertUpdateRecord();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    private void insertUpdateRecord() throws SQLException {
        String  query;
        if(ManageController.upd) {
            query = "Update show set id_show = " + Integer.parseInt(id_show_field.getText()) + ", id_per = " + Integer.parseInt(id_per_field.getText()) +
                    ", show_date = '" + date_field.getText() + "', show_time = '" + time_field.getText() + "' where id_show = " +
                    Integer.parseInt(id_show_field.getText()) + "";
            add_label.setText("Показ успешно изменен");
            upd = false;
        } else {
            query = "insert into show(id_show, id_per, show_date, show_time) " + "values(" +
                    id_show_field.getText() + ", " + id_per_field.getText() + ", '"
                    + date_field.getText() + "', '" + time_field.getText() + "')";
            add_label.setText("Показ успешно добавлен");
        }
        DBConnection.inst.executeQuery(query);
    }
}