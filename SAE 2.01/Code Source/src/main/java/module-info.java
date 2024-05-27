module org.example.fx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens org.example.fx to javafx.fxml;
    exports org.example.fx.Model;
    opens org.example.fx.Model to javafx.fxml;
    exports org.example.fx.vue;
    opens org.example.fx.vue to javafx.fxml;
    exports org.example.fx.controller;
    opens org.example.fx.controller to javafx.fxml;
}