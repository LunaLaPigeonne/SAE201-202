package org.example.fx.vue;

import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import org.example.fx.Model.*;

public class ControlBox extends VBox {
    private MouvementGroup mouvementGroup1;
    private MouvementGroup mouvementGroup2;
    private MouvementGroup mouvement;
    private Monde monde;
    private VBox data = new VBox();

    private int i = 1;
    public ControlBox(Monde monde) {
        super();
        this.monde = monde;
        textupdate();

        this.mouvementGroup1 = new MouvementGroup(monde.getBase().getLstRobot().get(0),monde);
        this.mouvementGroup2 = new MouvementGroup(monde.getBase().getLstRobot().get(1),monde);
        this.mouvement = mouvementGroup1;
        this.getChildren().addAll(data,mouvement);
    }
    public void setMouvement() {
        if (this.i==1) {
            this.getChildren().remove(this.mouvement);
            this.mouvement = mouvementGroup2;
            this.getChildren().add(this.mouvement);
            i=2;
            System.out.println("robot 2");
        }
        else {
            this.getChildren().remove(this.mouvement);
            this.mouvement = mouvementGroup1;
            this.getChildren().add(this.mouvement);
            i=1;
            System.out.println("robot 1");
        }
    }
    public void textupdate() {
        data.getChildren().clear();
        VBox datarobot = new VBox();
        Text tour = new Text();
        tour.setText("Tour : "+monde.getTour());
        tour.setFont(Font.font(15));
        data.getChildren().add(tour);
        Text tourde = new Text();
        if (i==1){
            tourde.setText("Tour du robot nickel");
        }else
        {
            tourde.setText("Tour du robot or");
        }
        data.getChildren().add(tourde);
        for (Robot robot : monde.getBase().getLstRobot()) {
            Text robotinfo = new Text();
            robotinfo.setText("Robot "+robot.getTypeMinerais().toString()+
                    "   Position : "+(robot.getX()+1)+","+(robot.getY()+1)
                    +"   nombre de minerais : "+robot.getNbMinerai()+"/"+robot.getNbMaxMinerai());
            datarobot.getChildren().add(robotinfo);
            robotinfo.setFont(Font.font(15));
        }
        VBox datamine = new VBox();
        for (Mines mine : monde.getBase().getLstMines()) {
            Text mineinfo = new Text();
            mineinfo.setText("Mine "+mine.getTypeMinerais().toString()+
                    "   Position : "+(mine.getX()+1)+","+(mine.getY()+1)
                    +"   nombre de minerais : "+mine.getNbMinerai());
            datamine.getChildren().add(mineinfo);
            mineinfo.setFont(Font.font(15));
        }
        VBox dataEntrepot = new VBox();
        for (Entrepot entrepot : monde.getBase().getLstEntrepot()) {
            Text entrepotinfo = new Text();
            entrepotinfo.setText("Entrepot "+entrepot.getTypeMinerais().toString()+
                    "   Position : "+(entrepot.getX()+1)+","+(entrepot.getY()+1)
                    +"   nombre de minerais : "+entrepot.getNbMinerai());
            dataEntrepot.getChildren().add(entrepotinfo);
            entrepotinfo.setFont(Font.font(15));
        }
        data.getChildren().addAll(datarobot,datamine,dataEntrepot);
    }
}
