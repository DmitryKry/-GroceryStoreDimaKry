module org.example.javafxtest {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires java.sql;
    opens Models to javafx.base, javafx.fxml;
    opens DTO to javafx.base, javafx.fxml;
    opens org.example.javafxtest to javafx.fxml;
    exports org.example.javafxtest;
}