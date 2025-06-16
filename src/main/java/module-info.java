module com.example.tugasbasisdata {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.zaxxer.hikari;
    requires java.sql;
    requires org.slf4j;


    opens com.example.tugasbasisdata to javafx.fxml;
    exports com.example.tugasbasisdata;
}