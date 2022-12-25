package application.controllers;

import database.connection.DBConnection;
import database.tables.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AuthorizationController extends SceneController implements Initializable {

    @FXML
    private Label add_label;

    @FXML
    private TextField logField;

    @FXML
    private TextField pwdField;

    @FXML
    private Button signInButton;

    @FXML
    private Button signUpButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        add_label.setVisible(false);
        signInButton.setOnAction(actionEvent -> {
            String loginLog = logField.getText().trim();
            String loginPwd = pwdField.getText().trim();


            if (!loginLog.equals("") && !loginPwd.equals("")) {
                try {
                    if(loginUser()) {
                        try {
                            switchTo(actionEvent, "Home.fxml");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else if (loginLog.equals("") && !loginPwd.equals("")) {
                add_label.setVisible(true);
                add_label.setText("Заполните поле логина");
            } else if (!loginLog.equals("")) {
                add_label.setVisible(true);
                add_label.setText("Заполните поле пароля");
            } else {
                add_label.setVisible(true);
                add_label.setText("Заполните поля логина и пароля");
            }
        });


        signUpButton.setOnAction(actionEvent -> {
            try {
                switchTo(actionEvent, "SignUp.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }


    private boolean loginUser() throws SQLException {

        String query = "select * from users where login = '" + logField.getText() + "' and password = '" + pwdField.getText().hashCode() +"'";

        ResultSet res = DBConnection.inst.getRSet(query);

        int counter = 0;
        User user = null;

        while (res.next()) {
            counter++;
            user = new User(res.getInt("id_user"), res.getString("user_ln"),
                    res.getString("user_fn"), res.getInt("tickets_count"),
                    res.getBoolean("discount"), res.getInt("user_role"),
                    res.getString("login"), res.getString("password"));
        }

        if (counter >= 1) {
            User.authorizedUser = user;
            add_label.setVisible(false);
            return true;
        } else
            add_label.setVisible(true);
            add_label.setText("Неправильный ввод логина или пароля");
            logField.clear();
            pwdField.clear();
        return false;
    }
}
