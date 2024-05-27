package org.example.fx.vue;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.example.fx.Model.*;

public class MainStage extends Stage {
    private Group robotGroup;
    ControlBox control;
    public MainStage(Monde monde) {
        super();
        Group root = new Group();
        HBox root2 = new HBox();
        this.setScene(new Scene(root, 1000, 503));
        this.setTitle("Main Stage");
        this.setResizable(false);
        MapBox map = new MapBox(monde);
        Group elementGroup = new Group();
        robotGroup = new Group();
        Group entrepotGroup = new Group();
        Group mineGroup = new Group();
        control = new ControlBox(monde);

        elementGroup.getChildren().addAll(entrepotGroup, mineGroup,robotGroup);
        root.getChildren().addAll(root2, elementGroup);
        root2.getChildren().addAll(map, control);

        for (Robot robot : monde.getBase().getLstRobot()) {
            Image imagenickel = new Image("file:src/main/java/org/example/fx/image/Tourniquet_nickel.png");
            Image imageor = new Image("file:src/main/java/org/example/fx/image/Tourniquet_or.png");
            ImageView imageView = new ImageView();
            imageView.setX(robot.getX()*50.4);
            imageView.setY(robot.getY()*50.4);
            imageView.setFitHeight(50);
            imageView.setFitWidth(50);

            if (robot.getTypeMinerais().equals(Minerais.nickel)) {
                imageView.setImage(imagenickel);
            }
            else {
                imageView.setImage(imageor);
            }
            robotGroup.getChildren().add(imageView);
        }

        for (Entrepot entrepot : monde.getBase().getLstEntrepot()) {
            Image imagenickel = new Image("file:src/main/java/org/example/fx/image/Entrepot_nickel.png");
            Image imageor = new Image("file:src/main/java/org/example/fx/image/Entrepot_or.png");
            ImageView imageView = new ImageView();
            imageView.setX(entrepot.getX()*50.4);
            imageView.setY(entrepot.getY()*50.4);
            imageView.setFitHeight(50);
            imageView.setFitWidth(50);

            if (entrepot.getTypeMinerais().equals(Minerais.nickel)) {
                imageView.setImage(imagenickel);
            }
            else {
                imageView.setImage(imageor);
            }
            entrepotGroup.getChildren().add(imageView);
        }

        for (Mines mines : monde.getBase().getLstMines()) {
            Image imagenickel = new Image("file:src/main/java/org/example/fx/image/Mine_nickel.png");
            Image imageor = new Image("file:src/main/java/org/example/fx/image/Mine_or.png");
            ImageView imageView = new ImageView();
            imageView.setX(mines.getX()*50.4);
            imageView.setY(mines.getY()*50.4);
            imageView.setFitHeight(50);
            imageView.setFitWidth(50);

            if (mines.getTypeMinerais().equals(Minerais.nickel)) {
                imageView.setImage(imagenickel);
            }
            else {
                imageView.setImage(imageor);
            }
            entrepotGroup.getChildren().add(imageView);
        }

        this.show();

    }

    public void update(Monde monde) {
        for (int i = 0; i < robotGroup.getChildren().size(); i++) {
            int x = monde.getBase().getLstRobot().get(i).getX();
            int y = monde.getBase().getLstRobot().get(i).getY();
            ImageView robot = (ImageView) robotGroup.getChildren().get(i);
            robot.setX(x*50.4);
            robot.setY(y*50.4);
        }
        control.setMouvement();
        control.textupdate();
        monde.tourSuivant();
    }


}
