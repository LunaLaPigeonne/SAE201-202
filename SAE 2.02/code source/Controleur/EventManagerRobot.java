package org.example.fx.Controleur;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.example.fx.Model.Entrepot;
import org.example.fx.Model.Mines;
import org.example.fx.Model.Monde;
import org.example.fx.Model.Robot;
import org.example.fx.bot.Network;
import org.example.fx.vue2.MapBox;
import org.example.fx.vue2.MainStage;


public class EventManagerRobot implements EventHandler {
    private Network network;
    private MapBox mapV;
    private Text textErreur;
    private TextField nb;
    private Text nbtour;
    private Monde monde;
    private MainStage stage;
    private Text info;

    public EventManagerRobot(Network network, MapBox mapV, Text textErreur, TextField nb, Text nbtour, Monde monde, MainStage stage, Text info) {
        this.network = network;
        this.mapV = mapV;
        this.textErreur = textErreur;
        this.nb = nb;
        this.nbtour = nbtour;
        this.monde = monde;
        this.stage = stage;
        this.info = info;
    }

    @Override
    public void handle(Event event) {
        if (event.getSource() instanceof Button) {
            Button button = (Button) event.getSource();
            if (button.getText().equals("Jouer")) {
                if (monde.getBase().getLstRobot().isEmpty()) {
                    stage.fin();
                }
                else if (nb.getText().matches("[0-9]+") && Integer.parseInt(nb.getText()) > 0 && Integer.parseInt(nb.getText()) <= 10){
                    for (int i = 0; i < Integer.parseInt(nb.getText()); i++) {
                        textErreur.setVisible(false);
                        jouer();
                    }
                } else {
                    textErreur.setVisible(true);
                }
            }
        }
    }

    private void jouer() {
        network.jouer();
        mapV.update();
        network.getMap().getMonde().tourSuivant();
        nbtour.setText("Tour : "+network.getMap().getMonde().getTour());
        setInfo();
    }

    public void setInfo() {
        String info = "Entrepots : \n";
        for (Entrepot entrepot : network.getMap().getEntrepot()) {
            info += "Entrepot "+entrepot.getid()+" "+entrepot.getTypeMinerais()+" : "+entrepot.getNbMinerai()+"\n";
        }
        info += "\nMine : \n";
        for (Mines mine : network.getMap().getMines()) {
            info += "Mine "+mine.getid()+" "+mine.getTypeMinerais()+" : "+mine.getNbMinerai()+"/"+mine.getNbMaxMinerai()+"\n";
        }
        info += "\nRobot : \n";
        for (Robot robot : network.getMap().getMonde().getBase().getLstRobot()) {
            info += "Robot "+robot.getid()+" "+robot.getTypeMinerais()+" : "+robot.getNbMinerai()+"/"+robot.getNbMaxMinerai()+"\n";
        }
        this.info.setText(info);
    }
}