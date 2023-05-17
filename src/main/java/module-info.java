module com.example.software1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens kc_software_1 to javafx.fxml;
    exports kc_software_1;
    exports controller;
    opens controller to javafx.fxml;
    opens model.products to javafx.base;
    opens model.parts to javafx.base;
}