module com.example.flexitrack3fx {
    requires javafx.controls;
    requires javafx.fxml;



    opens com.example.flexitrack3fx to javafx.fxml;
    exports com.example.flexitrack3fx;
    exports com.example.flexitrack3fx.data;
    opens com.example.flexitrack3fx.data to javafx.fxml;
}