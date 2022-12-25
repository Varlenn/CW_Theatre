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

public class EditTicketsController extends ManageController implements Initializable {

    @FXML
    private Label add_label;

    @FXML
    private TextField id_show_field;

    @FXML
    private TextField id_ticket_field;

    @FXML
    private TextField id_user_field;

    @FXML
    private Button insert_button;

    @FXML
    private TextField price_field;

    @FXML
    private TextField row_field;

    @FXML
    private TextField seat_field;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(ManageController.upd) {
            id_ticket_field.setText(String.valueOf(ManageController.ticket.getIdTicket()));
            id_show_field.setText(String.valueOf(ManageController.ticket.getIdShow()));
            id_user_field.setText(String.valueOf(ManageController.ticket.getIdUser()));
            row_field.setText(String.valueOf(ManageController.ticket.getRowVal()));
            seat_field.setText(String.valueOf(ManageController.ticket.getSeat()));
            price_field.setText(String.valueOf(ManageController.ticket.getPrice()));
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
            query = "Update tickets set id_ticket = " + Integer.parseInt(id_ticket_field.getText()) + ", id_show = " + Integer.parseInt(id_show_field.getText()) +
                    ", id_user = " + Integer.parseInt(id_user_field.getText()) + ", row = " + Integer.parseInt(row_field.getText()) + ", seat = " +
                    Integer.parseInt(seat_field.getText()) + ", price = " + Integer.parseInt(price_field.getText()) + " where id_ticket = " + Integer.parseInt(id_ticket_field.getText()) + "";
            add_label.setText("Билет успешно изменен");
            upd = false;
        } else {
            query = "insert into tickets(id_ticket, id_show, id_user, row, seat, price) " + "values(" +
                    id_ticket_field.getText() + ", " + id_show_field.getText() + ", "
                    + id_user_field.getText() + ", " + row_field.getText() + ", " + seat_field.getText() + ", " + price_field.getText() + ")";
            add_label.setText("Билет успешно добавлен");
        }

        DBConnection.inst.executeQuery(query);
    }
}
