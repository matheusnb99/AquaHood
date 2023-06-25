module com.example.aquahood {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.sdv.aquahood to javafx.fxml;
    exports aquahood;
}
