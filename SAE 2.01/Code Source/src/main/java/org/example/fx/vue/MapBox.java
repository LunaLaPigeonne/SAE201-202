package org.example.fx.vue;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.example.fx.Model.Monde;
import org.example.fx.Model.Nature;
import org.example.fx.Model.Secteur;

public class MapBox extends VBox {
    public MapBox(Monde monde) {
        super();
        for (int i = 0; i < 10; i++) {
            HBox row = new HBox();
            for (int j = 0; j < 10; j++) {
                Secteur secteur = monde.getSecteur(j, i);
                Rectangle square = new Rectangle(50,50);
                if (secteur.getNature().equals(Nature.eau))
                    square.setFill(Color.BLUE);
                else
                    square.setFill(Color.GREEN);
                row.getChildren().add(square);
            }
            this.getChildren().add(row);
        }
    }
}
