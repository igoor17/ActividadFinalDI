module com.example.gestioninstituto {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.gestioninstituto to javafx.fxml;
    exports com.example.gestioninstituto;
    exports com.example.gestioninstituto.models;
    opens com.example.gestioninstituto.models to javafx.fxml;
}