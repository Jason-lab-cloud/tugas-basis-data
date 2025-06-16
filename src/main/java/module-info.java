module com.example.tugasbasisdata {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.tugasbasisdata to javafx.fxml;
    exports com.example.tugasbasisdata;
}