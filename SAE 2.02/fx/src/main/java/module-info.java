module org.example.fx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires javatuples;

    opens org.example.fx to javafx.fxml;
    exports org.example.fx.Model;
    opens org.example.fx.Model to javafx.fxml;
    exports org.example.fx.vue2;
    opens org.example.fx.vue2 to javafx.fxml;
    exports org.example.fx.Controleur;
    opens org.example.fx.Controleur to javafx.fxml;
}