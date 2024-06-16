package org.example.fx.vue2;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.example.fx.Model.*;
import org.example.fx.bot.Map;

public class MapBox extends Group {
    private Map map;
    public MapBox(Map map) {
        super();
        this.map = map;
        VBox mappe = new VBox();
        this.getChildren().add(mappe);

        for (int i = 0; i < 10; i++) {
            HBox row = new HBox();
            for (int j = 0; j < 10; j++) {
                Secteur secteur = map.getSecteur(j, i);
                Rectangle square = new Rectangle(50,50);
                if (secteur == null)
                    square.setFill(Color.BLACK);
                else if (secteur.getNature().equals(Nature.eau))
                    square.setFill(Color.BLUE);
                else
                    square.setFill(Color.GREEN);
                row.getChildren().add(square);
            }
            mappe.getChildren().add(row);
        }

        Group elementGroup = new Group();
        Group robotGroup = new Group();
        Group entrepotGroup = new Group();
        Group mineGroup = new Group();
        elementGroup.getChildren().addAll(entrepotGroup, mineGroup,robotGroup);
        this.getChildren().add(elementGroup);
    }

    public void update() {
        VBox mappe = (VBox) this.getChildren().get(0);
        for (int i = 0; i < 10; i++) {
            HBox row = (HBox) mappe.getChildren().get(i);
            for (int j = 0; j < 10; j++) {
                Secteur secteur = map.getSecteur(j, i);
                Rectangle square = (Rectangle) row.getChildren().get(j);
                if (secteur != null && square.getFill().equals(Color.BLACK)){
                    if (secteur.getNature().equals(Nature.eau))
                        square.setFill(Color.BLUE);
                    else
                        square.setFill(Color.GREEN);
                }
            }
        }
        Group elementGroup = (Group) this.getChildren().get(1);
        Group entrepotGroup = (Group) elementGroup.getChildren().get(1);
        Group mineGroup = (Group) elementGroup.getChildren().get(0);
        Group robotGroup = (Group) elementGroup.getChildren().get(2);
        robotGroup.getChildren().clear();
        entrepotGroup.getChildren().clear();
        mineGroup.getChildren().clear();
        for (Robot robot : map.getMonde().getBase().getLstRobot()) {
            Image imagenickel = new Image(getClass().getResourceAsStream("/image/Tourniquet_nickel.png"));
            Image imageor = new Image(getClass().getResourceAsStream("/image/Tourniquet_or.png"));
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

        for (Entrepot entrepot : map.getEntrepot()) {
            Image imagenickel = new Image(getClass().getResourceAsStream("/image/Entrepot_nickel.png"));
            Image imageor = new Image(getClass().getResourceAsStream("/image/Entrepot_or.png"));
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

        for (Mines mines : map.getMines()) {
            Image imagenickel = new Image(getClass().getResourceAsStream("/image/Mine_nickel.png"));
            Image imageor = new Image(getClass().getResourceAsStream("/image/Mine_or.png"));
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
            mineGroup.getChildren().add(imageView);
        }

    }
}
