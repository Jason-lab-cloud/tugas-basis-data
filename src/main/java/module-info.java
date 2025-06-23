module com.example.tugasbasisdata {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.zaxxer.hikari;
    requires java.sql;
    requires org.slf4j;


    opens com.example.tugasbasisdata to javafx.fxml;
    exports com.example.tugasbasisdata;
    exports com.example.tugasbasisdata.source;
    opens com.example.tugasbasisdata.source to javafx.fxml;
    exports com.example.tugasbasisdata.controller;
    opens com.example.tugasbasisdata.controller to javafx.fxml;
}