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

public class EditRolesController extends ManageController implements Initializable {

    @FXML
    private Label add_label;

    @FXML
    private TextField id_actor_field;

    @FXML
    private Label id_label;

    @FXML
    private TextField id_per_field;

    @FXML
    private Button insert_button;

    @FXML
    private TextField role_field;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(ManageController.upd) {
            id_per_field.setText(String.valueOf(ManageController.role_.getIdPer()));
            id_actor_field.setText(String.valueOf(ManageController.role_.getIdActor()));
            role_field.setText(ManageController.role_.getRole());
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
            query = "Update roles set id_per = " + Integer.parseInt(id_per_field.getText()) + ", id_actor = " + Integer.parseInt(id_actor_field.getText()) +
                    ", role = '" + role_field.getText() + "' where id_per = " + Integer.parseInt(id_per_field.getText()) + " and id_actor = " +
                    Integer.parseInt(id_actor_field.getText()) + "";
            add_label.setText("Роль успешно изменена");
            upd = false;
        } else {
            query = "insert into roles(id_per, id_actor, role) values(" +
                    id_per_field.getText() + "," + id_actor_field.getText() + ", '"
                    + role_field.getText() + "')";
            add_label.setText("Роль успешно добавлена");
        }

        DBConnection.inst.executeQuery(query);
    }
}
