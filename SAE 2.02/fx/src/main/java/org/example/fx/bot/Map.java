package org.example.fx.bot;

import java.util.ArrayList;

import javafx.util.Pair;
import org.example.fx.Model.*;

public class Map {
    private Monde monde;
    private Secteur[][] map;
    private ArrayList<Pair<Integer, Integer>> secteursInconnus;
    private ArrayList<Mines> mines;
    private ArrayList<Entrepot> entrepots;

    public Map(Monde monde){
        this.monde = monde;
        this.map = new Secteur[10][10];
        this.secteursInconnus = new ArrayList<Pair<Integer, Integer>>();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                secteursInconnus.add(new Pair<Integer, Integer>(i, j));
            }
        }
        this.mines = new ArrayList<Mines>();
        this.entrepots = new ArrayList<Entrepot>();
    }

    //set the map with the new secteur discovered by the robot and update the list of mines and entrepôts
    public void update(Robot robot) {
        int xR = robot.getX();
        int yR = robot.getY();
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                if (xR + x >= 0 && xR + x < 10 && yR + y >= 0 && yR + y < 10) {
                    if (map[xR + x][yR + y] == null) {
                        map[xR + x][yR + y] = monde.getSecteur(xR + x, yR + y);
                        secteursInconnus.remove(new Pair<Integer, Integer>(xR + x, yR + y));
                        if (map[xR + x][yR + y].getbatiment() != null) {
                            if (map[xR + x][yR + y].getbatiment().getClass() == Mines.class) {
                                mines.add((Mines) map[xR + x][yR + y].getbatiment());
                            } else if (map[xR + x][yR + y].getbatiment().getClass() == Entrepot.class) {
                                entrepots.add((Entrepot) map[xR + x][yR + y].getbatiment());
                            }
                        }
                    }
                }
            }
        }
    }

    //return true if there is a mine with the same type of mineral as the robot
    public boolean minesDispo(Robot robot) {
        for (Mines mine : mines) {
            if (mine.getTypeMinerais() == robot.getTypeMinerais()) {
                if (mine.getNbMinerai()>0){
                    return true;
                }
            }
        }
        return false;
    }

    //return true if there is a warehouse with the same type of mineral as the robot
    public boolean entrepotsDispo(Robot robot) {
        for (Entrepot entrepot : entrepots) {
            if (entrepot.getTypeMinerais() == robot.getTypeMinerais()) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Mines> getMines() {
        return mines;
    }

    public Monde getMonde() {
        return monde;
    }

    public ArrayList<Entrepot> getEntrepot() {
        return entrepots;
    }

    public ArrayList<Pair<Integer,Integer>> getSecteursInconnus() {
        return secteursInconnus;
    }

    public Secteur getSecteur(int x, int y) {
        return map[x][y];
    }
}
