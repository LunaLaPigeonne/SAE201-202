package org.example.fx.vue;

import javafx.application.Application;
import org.example.fx.Model.Monde;

public class Main extends Application {
    private Monde monde = new Monde(5,10,1,2);
    private MainStage stageMap = new MainStage(monde);

public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(javafx.stage.Stage primaryStage) throws Exception {
        stageMap.show();
    }
}
