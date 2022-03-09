module com.example.roskildedaycare {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.roskildedaycare to javafx.fxml;
    exports com.example.roskildedaycare;
}