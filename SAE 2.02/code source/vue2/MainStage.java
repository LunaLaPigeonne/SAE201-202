package org.example.fx.vue2;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.example.fx.Controleur.EventManagerRobot;
import org.example.fx.Model.*;
import org.example.fx.bot.Map;
import org.example.fx.bot.Network;

public class MainStage extends Stage {
    private Network network;
    private Map map;
    private Monde monde;
    Group root = new Group();

    public MainStage(Monde monde) {
        super();

        this.monde = monde;
        this.network = new Network(this.monde);
        this.map = network.getMap();

        MapBox mapV = new MapBox(map);
        HBox root2 = new HBox();
        VBox control = new VBox();

        root2.getChildren().add(mapV);
        root2.getChildren().add(control);
        root.getChildren().add(root2);

        this.setScene(new Scene(root, 800, 500));
        this.setTitle("Main Stage");
        this.setResizable(false);
        for (Robot robot : this.monde.getBase().getLstRobot()) {
            map.update(robot);
        }
        Button jouer = new Button("Jouer");

        TextField nb = new TextField("1");
        Text textErreur = new Text("Erreur : Veuillez saisir un nombre entier valide (1 à 10)");
        Text nbtour = new Text("Tour : "+this.monde.getTour());
        Text info = new Text("");

        nb.setPromptText("Saisir un nombre :");
        textErreur.setFill(Color.RED);
        textErreur.setVisible(false);

        control.getChildren().addAll(nbtour,nb,textErreur,jouer,info);
        EventManagerRobot eventManagerRobot = new EventManagerRobot(network, mapV, textErreur, nb, nbtour, this.monde, this,info);
        jouer.setOnAction(eventManagerRobot);

        mapV.update();
        eventManagerRobot.setInfo();

        this.show();
    }

    public void fin() {
        this.setTitle("Fin");
        Button fin = new Button("Fin");
        root = new Group();
        this.setScene(new Scene(root, 300, 300));
        Text text = new Text("fin de la partie");
        text.setX(this.getWidth() / 2 - text.getLayoutBounds().getWidth() / 2);
        text.setY(this.getHeight() / 2 - text.getLayoutBounds().getHeight() / 2);
        root.getChildren().addAll(text, fin);
        fin.setOnAction(e -> {this.close();});
    }



}
