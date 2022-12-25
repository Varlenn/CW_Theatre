package application.controllers;

import database.connection.DBConnection;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EditStringController implements Initializable {

    @FXML
    private TextArea editString_area;

    @FXML
    private Button enter_button;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        enter_button.setOnAction(actionEvent -> {
            try {
                sendQuery();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    private void sendQuery() throws SQLException {
        String query = editString_area.getText();

        DBConnection.inst.executeQuery(query);
    }
}
