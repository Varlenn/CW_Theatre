module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens application to javafx.fxml;
    exports application;
    opens scenes to javafx.fxml;
    exports application.controllers;
    opens application.controllers to javafx.fxml;
    opens database.connection to javafx.fxml;
    exports database.connection;
    opens database.tables to javafx.fxml;
    exports database.tables;
}