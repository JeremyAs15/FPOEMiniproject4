module com.example.fpoeminiproject4 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens com.example.fpoeminiproject4 to javafx.fxml;
    opens com.example.fpoeminiproject4.Controller to javafx.fxml;

    exports com.example.fpoeminiproject4;
    exports com.example.fpoeminiproject4.Controller;

}