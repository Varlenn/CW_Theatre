package application.controllers;

import database.connection.DBConnection;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class SignUpController extends SceneController implements Initializable {

    @FXML
    private TextField firstname_field;

    @FXML
    private Label add_label;

    @FXML
    private TextField login_field;

    @FXML
    private CheckBox notifications_check_box;

    @FXML
    private TextField patronymic_field;

    @FXML
    private TextField password_field;

    @FXML
    private TextField secondname_field;

    @FXML
    private Button signUp_button;

    @FXML
    private Button signInButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        add_label.setVisible(false);

        signUp_button.setOnAction(actionEvent -> {
            try {
                if (Objects.equals(firstname_field.getText(), "") || Objects.equals(secondname_field.getText(), "") ||
                        Objects.equals(patronymic_field.getText(), "") || Objects.equals(login_field.getText(), "") ||
                        Objects.equals(password_field.getText(), ""))
                    add_label.setVisible(true);
                else {
                    add_label.setVisible(false);
                    newUser();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                switchTo(actionEvent, "Authorization.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        signInButton.setOnAction(actionEvent -> {
            try {
                switchTo(actionEvent, "Authorization.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void newUser() throws SQLException {
        String query = "insert into users(user_ln, user_fn, login, password) values('" + secondname_field.getText() + "', '" +
                firstname_field.getText() + " " + patronymic_field.getText() + "', '" + login_field.getText() + "', '" + password_field.getText().hashCode() + "')";
        DBConnection.inst.executeQuery(query);
    }
}

