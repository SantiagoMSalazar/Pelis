module main.pelis {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires java.sql;
    requires mysql.connector.java;
    requires java.desktop;
    requires org.jetbrains.annotations;

    opens main to javafx.fxml;
    exports main;
    exports main.Vistas;
    opens main.Vistas to javafx.fxml;
    opens main.code to javafx.base;
}