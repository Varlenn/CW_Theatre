package database.connection;

import database.tables.Show;
import database.tables.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class DBConnection {
    public static DBConnection inst = null;

    public DBConnection() {
        inst = this;
    }

    Connection conn = null;
    Statement st = null;
    ResultSet rSet = null;

    public void connect(){
        try {
            String driver = "org.postgresql.Driver";
            Class.forName(driver);

            System.out.println("Connecting to database ...\n");
            String connectionString = "jdbc:postgresql://172.20.8.18:5432/kp0092_22";
            String user = "st0092";
            String pwd = "qwerty92";
            conn = DriverManager.getConnection(connectionString, user, pwd);
        } catch(Exception se) {
            se.printStackTrace();
        }
    }

    public void executeQuery(String query) throws SQLException {
        st = conn.createStatement();
        st.executeUpdate(query);
    }

    public ResultSet getRSet(String query) throws SQLException {
        st = conn.createStatement();
        rSet = st.executeQuery(query);

        return rSet;
    }
}
