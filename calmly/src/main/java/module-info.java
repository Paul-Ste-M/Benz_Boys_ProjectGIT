module com.calmly {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;
    requires jfugue;

    opens com.calmly to javafx.fxml;
    exports com.calmly;

    opens com.model to javafx.fxml;
    exports com.model;
}
