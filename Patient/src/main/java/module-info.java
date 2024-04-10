module org.example.patient {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.patient to javafx.fxml;
    exports org.example.patient;
}