package application.controllers;

import database.connection.DBConnection;
import database.tables.*;
import database.tables.TableRow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Objects;
import java.util.ResourceBundle;

public class ManageController extends SceneController implements Initializable {

    @FXML
    private ComboBox<String> choice_box;

    @FXML
    private TableView<TableRow> tableUsersManage;

    @FXML
    private Button edit_button;

    @FXML
    private Button pfp_button;

    @FXML
    private Label name_bar_label;

    @FXML
    private Button cansel_button;

    @FXML
    private Button add_button;

    @FXML
    private Button del_button;

    @FXML
    private Button upd_button;

    @FXML
    private Button enter_button;

    @FXML
    private Button to_home_button;

    @FXML
    private Button editor_button;


    public static Boolean upd = false;
    private Integer choiceId = 0;

    public static User user = null;
    public static Actor actor = null;
    public static Performance per = null;
    public static Role role_ = null;
    public static Show show = null;
    public static Ticket ticket = null;


    TableColumn<TableRow, Integer> id_user;
    TableColumn<TableRow, String> user_ln;
    TableColumn<TableRow, String> user_fn;
    TableColumn<TableRow, Integer> tickets_count;
    TableColumn<TableRow, Boolean> discount;
    TableColumn<TableRow, Integer> user_role;
    TableColumn<TableRow, String> login;
    TableColumn<TableRow, String> password;

    TableColumn<TableRow, Integer> id_actor;
    TableColumn<TableRow, String> actor_ln;
    TableColumn<TableRow, String> actor_fn;

    TableColumn<TableRow, Integer> id_show;
    TableColumn<TableRow, Integer> id_per_s;
    TableColumn<TableRow, String> show_date;
    TableColumn<TableRow, String> show_time;

    TableColumn<TableRow, Integer> id_ticket;
    TableColumn<TableRow, Integer> id_show_t;
    TableColumn<TableRow, Integer> id_user_t;
    TableColumn<TableRow, Integer> row;
    TableColumn<TableRow, Integer> seat;
    TableColumn<TableRow, Integer> price;

    TableColumn<TableRow, Integer> id_per_r;
    TableColumn<TableRow, Integer> id_actor_r;
    TableColumn<TableRow, String> role;

    TableColumn<TableRow, Integer> id_per;
    TableColumn<TableRow, String> per_name;
    TableColumn<TableRow, Integer> show_count;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        enter_button.setVisible(false);
        pfp_button.graphicProperty().setValue(new ImageView("C:/Users/Varlenn/IdeaProjects/KP_Theatre/src/main/resources/assets/pfp-1.png"));
        name_bar_label.setText(User.authorizedUser.returnUserName());

        String[] variations = {};
        if (User.authorizedUser.getUserRole() == 1) {
            variations = new String[]{"Актеры", "Показы", "Роли", "Спектакли"};
            choice_box.getItems().addAll(variations);
            choice_box.setValue("Актеры");
            try {
                setTableActors();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else if (User.authorizedUser.getUserRole() == 0) {
            variations = new String[]{"Пользователи", "Актеры", "Билеты", "Показы", "Роли", "Спектакли"};
            enter_button.setVisible(true);
            choice_box.getItems().addAll(variations);
            choice_box.setValue("Пользователи");
            try {
                setTableUser();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        choice_box.setOnAction(event -> {
            if (Objects.equals(choice_box.getSelectionModel().getSelectedItem(), "Пользователи")) {
                try {
                    updateTables();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else if (Objects.equals(choice_box.getSelectionModel().getSelectedItem(), "Актеры")) {
                try {
                    updateTables();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else if (Objects.equals(choice_box.getSelectionModel().getSelectedItem(), "Показы")) {
                try {
                    updateTables();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else if (Objects.equals(choice_box.getSelectionModel().getSelectedItem(), "Билеты")) {
                try {
                    updateTables();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else if (Objects.equals(choice_box.getSelectionModel().getSelectedItem(), "Роли")) {
                try {
                    updateTables();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else if (Objects.equals(choice_box.getSelectionModel().getSelectedItem(), "Спектакли")) {
                try {
                    updateTables();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        to_home_button.setOnAction(actionEvent -> {
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

        upd_button.setOnAction(actionEvent -> {
            try {
                updateTables();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        del_button.setOnAction(actionEvent -> {
            try {
                deleteRecord();
                updateTables();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        add_button.setOnAction(event -> {
            upd = false;
            showEditDialog(event);
        });

        edit_button.setOnAction(event -> {
            upd = true;
            showEditDialog(event);
        });

        cansel_button.setOnAction(actionEvent -> {
            upd = false;
            try {
                updateTables();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }


    public void setTablePerformance() throws SQLException {
        id_per = new TableColumn<>("ID спектакля");
        per_name = new TableColumn<>("Название");
        show_count = new TableColumn<>("Количество показов");

        tableUsersManage.getColumns().addAll(id_per, per_name, show_count);

        showPerformance();
    }

    public void showPerformance() throws SQLException {
        id_per.setCellValueFactory(new PropertyValueFactory<>("idPer"));
        per_name.setCellValueFactory(new PropertyValueFactory<>("perName"));
        show_count.setCellValueFactory(new PropertyValueFactory<>("showCount"));

        tableUsersManage.setItems(getPerformanceList());
    }


    public ObservableList<TableRow> getPerformanceList() throws SQLException {

        ObservableList<TableRow> rolesList = FXCollections.observableArrayList();

        String query = "select * from performance";
        ResultSet res = DBConnection.inst.getRSet(query);

        Performance per;
        while (res.next()) {
            per = new Performance(res.getInt("id_per"), res.getString("per_name"), res.getInt("show_count"));
            rolesList.add(per);
        }
        return rolesList;
    }


    public void setTableRoles() throws SQLException {
        id_per_r = new TableColumn<>("ID спектакля");
        id_actor_r = new TableColumn<>("ID актера");
        role = new TableColumn<>("Роль");

        tableUsersManage.getColumns().addAll(id_per_r, id_actor_r, role);

        showRoles();
    }

    public void showRoles() throws SQLException {
        id_per_r.setCellValueFactory(new PropertyValueFactory<>("idPer"));
        id_actor_r.setCellValueFactory(new PropertyValueFactory<>("idActor"));
        role.setCellValueFactory(new PropertyValueFactory<>("role"));

        tableUsersManage.setItems(getRolesList());
    }


    public ObservableList<TableRow> getRolesList() throws SQLException {

        ObservableList<TableRow> rolesList = FXCollections.observableArrayList();

        String query = "select * from roles";
        ResultSet res = DBConnection.inst.getRSet(query);

        Role role;
        while (res.next()) {
            role = new Role(res.getInt("id_per"), res.getInt("id_actor"), res.getString("role"));
            rolesList.add(role);
        }
        return rolesList;
    }


    public void setTableTickets() throws SQLException {
        id_ticket = new TableColumn<>("ID билета");
        id_show_t = new TableColumn<>("ID показа");
        id_user_t = new TableColumn<>("ID пользователя");
        row = new TableColumn<>("Ряд");
        seat = new TableColumn<>("Место");
        price = new TableColumn<>("Цена");

        tableUsersManage.getColumns().addAll(id_ticket, id_show_t, id_user_t, row, seat, price);

        showTickets();
    }

    public void showTickets() throws SQLException {
        id_ticket.setCellValueFactory(new PropertyValueFactory<>("idTicket"));
        id_show_t.setCellValueFactory(new PropertyValueFactory<>("idShow"));
        id_user_t.setCellValueFactory(new PropertyValueFactory<>("idUser"));
        row.setCellValueFactory(new PropertyValueFactory<>("rowVal"));
        seat.setCellValueFactory(new PropertyValueFactory<>("seat"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));

        tableUsersManage.setItems(getTicketsList());
    }

    public ObservableList<TableRow> getTicketsList() throws SQLException {

        ObservableList<TableRow> ticketsList = FXCollections.observableArrayList();

        String query = "select * from tickets";
        ResultSet res = DBConnection.inst.getRSet(query);

        Ticket ticket;
        while (res.next()) {
            ticket = new Ticket(res.getInt("id_ticket"), res.getInt("id_show"), res.getInt("id_user"),
                    res.getInt("row"), res.getInt("seat"), res.getDouble("price"));
            ticketsList.add(ticket);
        }
        return ticketsList;
    }


    public void setTableShow() throws SQLException {
        id_show = new TableColumn<>("ID показа");
        show_date = new TableColumn<>("Дата показа");
        show_time = new TableColumn<>("Время показа");
        id_per_s = new TableColumn<>("ID спектакля");

        tableUsersManage.getColumns().addAll(id_show, id_per_s, show_date, show_time);

        showShow();
    }

    public void showShow() throws SQLException {
        id_show.setCellValueFactory(new PropertyValueFactory<>("idShow"));
        id_per_s.setCellValueFactory(new PropertyValueFactory<>("idPer"));
        show_date.setCellValueFactory(new PropertyValueFactory<>("showDate"));
        show_time.setCellValueFactory(new PropertyValueFactory<>("showTime"));

        tableUsersManage.setItems(getShowList());
    }

    public ObservableList<TableRow> getShowList() throws SQLException {

        ObservableList<TableRow> showList = FXCollections.observableArrayList();

        String query = "select * from show";
        ResultSet res = DBConnection.inst.getRSet(query);

        Show show;
        while (res.next()) {
            show = new Show(res.getInt("id_show"), res.getInt("id_per"), res.getDate("show_date"),
                    res.getTime("show_time"));
            showList.add(show);
        }
        return showList;
    }


    public void setTableActors() throws SQLException {
        id_actor = new TableColumn<>("ID");
        actor_ln = new TableColumn<>("Фамилия");
        actor_fn = new TableColumn<>("Имя Отчество");
        tableUsersManage.getColumns().addAll(id_actor, actor_ln, actor_fn);

        showActors();
    }

    public void showActors() throws SQLException {
        id_actor.setCellValueFactory(new PropertyValueFactory<>("idActor"));
        actor_ln.setCellValueFactory(new PropertyValueFactory<>("actorLn"));
        actor_fn.setCellValueFactory(new PropertyValueFactory<>("actorFn"));

        tableUsersManage.setItems(getActorsList());
    }

    public ObservableList<TableRow> getActorsList() throws SQLException {

        ObservableList<TableRow> actorsList = FXCollections.observableArrayList();

        String query = "select * from actors";
        ResultSet res = DBConnection.inst.getRSet(query);

        Actor actor;
        while (res.next()) {
            actor = new Actor(res.getInt("id_actor"), res.getString("actor_ln"),
                    res.getString("actor_fn"));
            actorsList.add(actor);
        }
        return actorsList;
    }


    public void setTableUser() throws SQLException {
        id_user = new TableColumn<>("ID");
        user_ln = new TableColumn<>("Фамилия");
        user_fn = new TableColumn<>("Имя Отчество");
        tickets_count = new TableColumn<>("Кол-во билетов");
        discount = new TableColumn<>("Скидка");
        user_role = new TableColumn<>("Роль");
        login = new TableColumn<>("Логин");
        password = new TableColumn<>("Пароль");
        tableUsersManage.getColumns().addAll(id_user, user_ln, user_fn, tickets_count, discount, user_role, login, password);

        showUsers();
    }

    public void showUsers() throws SQLException {
        id_user.setCellValueFactory(new PropertyValueFactory<>("idUser"));
        user_ln.setCellValueFactory(new PropertyValueFactory<>("userLn"));
        user_fn.setCellValueFactory(new PropertyValueFactory<>("userFn"));
        tickets_count.setCellValueFactory(new PropertyValueFactory<>("ticketsCount"));
        discount.setCellValueFactory(new PropertyValueFactory<>("discount"));
        user_role.setCellValueFactory(new PropertyValueFactory<>("userRole"));
        login.setCellValueFactory(new PropertyValueFactory<>("login"));
        password.setCellValueFactory(new PropertyValueFactory<>("password"));

        tableUsersManage.setItems(getUsersList());
    }

    public ObservableList<TableRow> getUsersList() throws SQLException {

        ObservableList<TableRow> usersList = FXCollections.observableArrayList();

        String query = "select * from users";
        ResultSet res = DBConnection.inst.getRSet(query);

        User user;
        while (res.next()) {
            user = new User(res.getInt("id_user"), res.getString("user_ln"),
                    res.getString("user_fn"), res.getInt("tickets_count"),
                    res.getBoolean("discount"), res.getInt("user_role"),
                    res.getString("login"), res.getString("password"));
            usersList.add(user);
        }
        return usersList;
    }


    @FXML
    private void deleteRecord() throws SQLException {
        String query = "";

        if (Objects.equals(choice_box.getSelectionModel().getSelectedItem(), "Пользователи")) {
            query = "Delete from users where id_user = " + choiceId + "";
        } else if (Objects.equals(choice_box.getSelectionModel().getSelectedItem(), "Актеры")) {
            query = "Delete from actors where id_actor = " + choiceId + "";
        } else if (Objects.equals(choice_box.getSelectionModel().getSelectedItem(), "Показы")) {
            query = "Delete from show where id_show = " + choiceId + "";
        } else if (Objects.equals(choice_box.getSelectionModel().getSelectedItem(), "Билеты")) {
            query = "Delete from tickets where id_ticket = " + choiceId + "";
        } else if (Objects.equals(choice_box.getSelectionModel().getSelectedItem(), "Роли")) {
            query = "Delete from roles where id_per = " + choiceId + "";
        } else if (Objects.equals(choice_box.getSelectionModel().getSelectedItem(), "Спектакли")) {
            query = "Delete from performance where id_per = " + choiceId + "";
        }
        DBConnection.inst.executeQuery(query);
        try {
            updateTables();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void updateTables() throws SQLException {
        if (Objects.equals(choice_box.getSelectionModel().getSelectedItem(), "Пользователи")) {
            tableUsersManage.getColumns().clear();
            setTableUser();
        } else if (Objects.equals(choice_box.getSelectionModel().getSelectedItem(), "Актеры")) {
            tableUsersManage.getColumns().clear();
            setTableActors();
        } else if (Objects.equals(choice_box.getSelectionModel().getSelectedItem(), "Показы")) {
            tableUsersManage.getColumns().clear();
            setTableShow();
        } else if (Objects.equals(choice_box.getSelectionModel().getSelectedItem(), "Билеты")) {
            tableUsersManage.getColumns().clear();
            setTableTickets();
        } else if (Objects.equals(choice_box.getSelectionModel().getSelectedItem(), "Роли")) {
            tableUsersManage.getColumns().clear();
            setTableRoles();
        } else if (Objects.equals(choice_box.getSelectionModel().getSelectedItem(), "Спектакли")) {
            tableUsersManage.getColumns().clear();
            setTablePerformance();
        }
    }

    public void handleMouseAction(MouseEvent mouseEvent) {
        if (Objects.equals(choice_box.getSelectionModel().getSelectedItem(), "Пользователи")) {
            User user = (User) tableUsersManage.getSelectionModel().getSelectedItem();
            choiceId = user.getIdUser();
            ManageController.user = user;
        } else if (Objects.equals(choice_box.getSelectionModel().getSelectedItem(), "Актеры")) {
            Actor actor = (Actor) tableUsersManage.getSelectionModel().getSelectedItem();
            choiceId = actor.getIdActor();
            ManageController.actor = actor;
        } else if (Objects.equals(choice_box.getSelectionModel().getSelectedItem(), "Показы")) {
            Show show = (Show) tableUsersManage.getSelectionModel().getSelectedItem();
            choiceId = show.getIdShow();
            ManageController.show = show;
        } else if (Objects.equals(choice_box.getSelectionModel().getSelectedItem(), "Билеты")) {
            Ticket ticket = (Ticket) tableUsersManage.getSelectionModel().getSelectedItem();
            choiceId = ticket.getIdTicket();
            ManageController.ticket = ticket;
        } else if (Objects.equals(choice_box.getSelectionModel().getSelectedItem(), "Роли")) {
            Role role = (Role) tableUsersManage.getSelectionModel().getSelectedItem();
            choiceId = role.getIdPer();
            ManageController.role_ = role;
        } else if (Objects.equals(choice_box.getSelectionModel().getSelectedItem(), "Спектакли")) {
            Performance per = (Performance) tableUsersManage.getSelectionModel().getSelectedItem();
            choiceId = per.getIdPer();
            ManageController.per = per;
        }
    }

    public void showEditString(ActionEvent event) {
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/scenes/EditString.fxml")));
            Scene scene = new Scene(root, 536, 168);
            stage.setTitle("Edit string");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.initModality(Modality.WINDOW_MODAL);
//            stage.initOwner(((Node)event.getSource()).getScene().getWindow());
            stage.show();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }


    public void showEditDialog(ActionEvent event) {
        if (Objects.equals(choice_box.getSelectionModel().getSelectedItem(), "Пользователи")) {
            try {
                Stage stage = new Stage();
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/scenes/EditUser.fxml")));
                Scene scene = new Scene(root, 350, 405);
                stage.setTitle("Users editor");
                stage.setResizable(false);
                stage.setScene(scene);
                stage.initModality(Modality.WINDOW_MODAL);
                stage.show();
            } catch (
                    IOException e) {
                e.printStackTrace();
            }
        } else if (Objects.equals(choice_box.getSelectionModel().getSelectedItem(), "Актеры")) {
            try {
                Stage stage = new Stage();
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/scenes/EditActor.fxml")));
                Scene scene = new Scene(root, 350, 237);
                stage.setTitle("Actors editor");
                stage.setResizable(false);
                stage.setScene(scene);
                stage.initModality(Modality.WINDOW_MODAL);
                stage.show();
            } catch (
                    IOException e) {
                e.printStackTrace();
            }
        } else if (Objects.equals(choice_box.getSelectionModel().getSelectedItem(), "Показы")) {
            try {
                Stage stage = new Stage();
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/scenes/EditShow.fxml")));
                Scene scene = new Scene(root, 350, 288);
                stage.setTitle("Show editor");
                stage.setResizable(false);
                stage.setScene(scene);
                stage.initModality(Modality.WINDOW_MODAL);
                stage.show();
            } catch (
                    IOException e) {
                e.printStackTrace();
            }
        } else if (Objects.equals(choice_box.getSelectionModel().getSelectedItem(), "Билеты")) {
            try {
                Stage stage = new Stage();
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/scenes/EditTickets.fxml")));
                Scene scene = new Scene(root, 350, 365);
                stage.setTitle("Tickets editor");
                stage.setResizable(false);
                stage.setScene(scene);
                stage.initModality(Modality.WINDOW_MODAL);
                stage.show();
            } catch (
                    IOException e) {
                e.printStackTrace();
            }
        } else if (Objects.equals(choice_box.getSelectionModel().getSelectedItem(), "Роли")) {
            try {
                Stage stage = new Stage();
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/scenes/EditRoles.fxml")));
                Scene scene = new Scene(root, 350, 237);
                stage.setTitle("Roles editor");
                stage.setResizable(false);
                stage.setScene(scene);
                stage.initModality(Modality.WINDOW_MODAL);
                stage.show();
            } catch (
                    IOException e) {
                e.printStackTrace();
            }
        } else if (Objects.equals(choice_box.getSelectionModel().getSelectedItem(), "Спектакли")) {
            try {
                Stage stage = new Stage();
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/scenes/EditPerformance.fxml")));
                Scene scene = new Scene(root, 350, 237);
                stage.setTitle("Performance editor");
                stage.setResizable(false);
                stage.setScene(scene);
                stage.initModality(Modality.WINDOW_MODAL);
                stage.show();
            } catch (
                    IOException e) {
                e.printStackTrace();
            }
        }
    }
}
