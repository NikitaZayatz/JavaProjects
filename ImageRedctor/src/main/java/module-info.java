module com.example.imageredctor {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.imageredctor to javafx.fxml;
    exports com.example.imageredctor;
}