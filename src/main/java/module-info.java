module com.example.fpoeminiproject4 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens com.example.fpoeminiproject4 to javafx.fxml;
    opens com.example.fpoeminiproject4.controllers to javafx.fxml;
    opens com.example.fpoeminiproject4.views to javafx.fxml;
    opens com.example.fpoeminiproject4.models to  javafx.fxml;
    opens com.example.fpoeminiproject4.interfaces to javafx.fxml;

    exports com.example.fpoeminiproject4;
    exports com.example.fpoeminiproject4.controllers;
    exports com.example.fpoeminiproject4.views;
    exports com.example.fpoeminiproject4.models;
    exports com.example.fpoeminiproject4.interfaces;

}