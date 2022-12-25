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

public class EditPerformanceController extends ManageController implements Initializable {

    @FXML
    private Label add_label;

    @FXML
    private TextField count_field;

    @FXML
    private TextField id_field;

    @FXML
    private Button insert_button;

    @FXML
    private TextField name_field;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(ManageController.upd) {
            id_field.setText(String.valueOf(ManageController.per.getIdPer()));
            name_field.setText(ManageController.per.getPerName());
            count_field.setText(String.valueOf(ManageController.per.getShowCount()));
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
            query = "Update performance set id_per = " + Integer.parseInt(id_field.getText()) + ", per_name = '" + name_field.getText() +
                    "', show_count = " + Integer.parseInt(count_field.getText()) + " where id_per = " + Integer.parseInt(id_field.getText()) + "";
            add_label.setText("Спектакль успешно изменен");
            upd = false;
        } else {
            query = "insert into performance(id_per, per_name, show_count) values(" +
                    id_field.getText() + ",'" + name_field.getText() + "', "
                    + count_field.getText() + ")";
                    add_label.setText("Спектакль успешно добавлен");
        }

        DBConnection.inst.executeQuery(query);
    }
}

