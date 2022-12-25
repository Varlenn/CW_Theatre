package application.controllers;

import database.connection.DBConnection;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EditUsersController extends ManageController implements Initializable {

    @FXML
    private Label add_label;

    @FXML
    private TextField discount_field;

    @FXML
    private Button insert_button;

    @FXML
    private TextField id_field;

    @FXML
    private Label id_label;

    @FXML
    private TextField login_field;

    @FXML
    private TextField password_field;

    @FXML
    private TextField role_field;

    @FXML
    private TextField tickets_count_field;

    @FXML
    private TextField user_fn_field;

    @FXML
    private TextField user_ln_field;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(ManageController.upd) {
            id_field.setText(String.valueOf(ManageController.user.getIdUser()));
            user_ln_field.setText(ManageController.user.getUserLn());
            user_fn_field.setText(ManageController.user.getUserFn());
            tickets_count_field.setText(String.valueOf(ManageController.user.getTicketsCount()));
            discount_field.setText(String.valueOf(ManageController.user.getDiscount()));
            role_field.setText(String.valueOf(ManageController.user.getUserRole()));
            login_field.setText(ManageController.user.getLogin());
            password_field.setText(ManageController.user.getPassword());
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
            query = "Update users set id_user = " + Integer.parseInt(id_field.getText()) + ", user_ln = '" + user_ln_field.getText() +
                    "', user_fn = '" + user_fn_field.getText() + "', tickets_count = " + Integer.parseInt(tickets_count_field.getText()) + ", discount = " +
                    Boolean.parseBoolean(discount_field.getText()) + ", user_role = " + Integer.parseInt(role_field.getText()) + ", login = '" + login_field.getText() +
                    "', password = '" + password_field.getText().hashCode() + "' where id_user = " + Integer.parseInt(id_field.getText()) + "";
            add_label.setText("Пользователь успешно изменен");
            upd = false;
        } else {
            query = "insert into users(id_user, user_ln, user_fn, " +
                    "tickets_count, discount, user_role, login, password) values(" + id_field.getText() + ",'" + user_ln_field.getText() + "', '"
                    + user_fn_field.getText() + "', " + Integer.parseInt(tickets_count_field.getText()) + ", " +
                    Boolean.parseBoolean(discount_field.getText()) + ", " + Integer.parseInt(role_field.getText()) + ", '" + login_field.getText() +
                    "', '" + password_field.getText().hashCode() + "')";
            add_label.setText("Пользователь успешно добавлен");
        }

        DBConnection.inst.executeQuery(query);
    }
}
