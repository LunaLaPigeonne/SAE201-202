package org.example.fx.bot;

import org.example.fx.Model.Monde;
import org.example.fx.Model.Robot;

import java.util.ArrayList;

public class Network {
    private Map map;
    private ArrayList<Ai> ai;

    public Network(Monde monde){
        this.map = new Map(monde);
        this.ai = new ArrayList<Ai>();
        for (Robot robot : monde.getBase().getLstRobot()) {
            this.ai.add(new Ai(this, robot));
        }
    }

    //play the turn for all the robots
    public void jouer() {
        for (Ai ai : this.ai) {
            ai.jouer();
        }
    }

    public Map getMap() {
        return map;
    }

    public ArrayList<Ai> getAi() {
        return ai;
    }
}
