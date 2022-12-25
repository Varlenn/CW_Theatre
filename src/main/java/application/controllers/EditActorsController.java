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

public class EditActorsController extends ManageController implements Initializable {
    @FXML
    private Label add_label;

    @FXML
    private TextField id_field;

    @FXML
    private Label id_label;

    @FXML
    private Button insert_button;

    @FXML
    private TextField actor_fn_field;

    @FXML
    private TextField actor_ln_field;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        if(ManageController.upd) {
            id_field.setText(String.valueOf(ManageController.actor.getIdActor()));
            actor_fn_field.setText(ManageController.actor.getActorLn());
            actor_ln_field.setText(ManageController.actor.getActorFn());
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
            query = "Update actors set id_actor = " + Integer.parseInt(id_field.getText()) + ", actor_ln = '" + actor_ln.getText() +
                    "', actor_fn = '" + actor_fn_field.getText() + "' where id_actor = " + Integer.parseInt(id_field.getText()) + "";
            add_label.setText("Актер успешно изменен");
            upd = false;
        } else {
            query = "insert into actors(id_actor, actor_ln, actor_fn) " + "values(" +
                    id_field.getText() + ",'" + actor_ln_field.getText() + "', '"
                    + actor_fn_field.getText() + "')";
            add_label.setText("Актер успешно добавлен");
        }

        DBConnection.inst.executeQuery(query);
    }
}
