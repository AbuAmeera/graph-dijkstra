module com.example.prj3 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.example.prj3 to javafx.fxml;
    exports com.example.prj3;
}