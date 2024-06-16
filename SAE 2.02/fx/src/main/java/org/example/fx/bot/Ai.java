package org.example.fx.bot;

import javafx.util.Pair;
import org.example.fx.Model.*;

import java.util.ArrayList;

public class Ai {
    private Map map;
    private Robot robot;
    private int[][] mapdij = new int[10][10];

    public Ai(Network network, Robot robot) {
        this.map = network.getMap();
        this.robot = robot;
    }

    //play the turn for the robot
    public void jouer() {

        if (robot.deposer(map.getMonde())) {
            map.update(robot);
            return;
        }
        if (robot.getNbMinerai() != robot.getNbMaxMinerai() && robot.miner(map.getMonde())) {
            map.update(robot);
            return;
        }

        //dijkstra algorithm to find the shortest path to all the sectors
        dijkstramap();
        String dikjtra = "";
        //find the shortest path to a mine with the same type of mineral as the robot
        if (map.minesDispo(robot) && robot.getNbMinerai()<robot.getNbMaxMinerai()){
            String newdikjtra;
            for (Mines mine : map.getMines()) {
                Secteur secteur = map.getSecteur(mine.getX(), mine.getY());
                if (mine.getTypeMinerais()==robot.getTypeMinerais() && mine.getNbMinerai()>0 && secteur.getrobot() == null) {
                    newdikjtra = distraitStr(mine.getX(), mine.getY());
                    if (dikjtra.isEmpty() || newdikjtra.length() < dikjtra.length()) {
                        dikjtra = newdikjtra;
                    }
                }
            }
        }
        //find the shortest path to a warehouse with the same type of mineral as the robot
        else if (map.entrepotsDispo(robot) && robot.getNbMinerai()>0){
            String newdikjtra;
            for (Entrepot entrepot : map.getEntrepot()) {
                if (entrepot.getTypeMinerais()==robot.getTypeMinerais()) {
                    newdikjtra = distraitStr(entrepot.getX(), entrepot.getY());
                    if (dikjtra.isEmpty() || newdikjtra.length() < dikjtra.length()) {
                        dikjtra = newdikjtra;
                    }
                }
            }
        }
        //find the shortest path to an unknown sector
        if (!map.getSecteursInconnus().isEmpty() && dikjtra.isEmpty()){
            String newdikjtra;
            for (Pair<Integer, Integer> secteur : map.getSecteursInconnus()) {
                newdikjtra = distraitStr(secteur.getKey(), secteur.getValue());
                if (dikjtra.isEmpty() || newdikjtra.length() < dikjtra.length()) {
                    dikjtra = newdikjtra;
                }
            }
        }
        //if there is no path to a mine or a warehouse with the same type of mineral as the robot, the robot goes back to the warehouse
        boolean test = true;
        for (Mines mine : map.getMines()) {
            if (mine.getTypeMinerais() == robot.getTypeMinerais() && mine.getNbMinerai() > 0) {
                test = false;
                break;
            }
        }
        if (dikjtra.isEmpty() && test){
            int x = robot.getX();
            int y = robot.getY();
            Batiment batiment = map.getSecteur(x, y).getbatiment();
            if (batiment != null && batiment.getClass() == Entrepot.class && batiment.getTypeMinerais() == robot.getTypeMinerais()) {
                Entrepot entrepot = (Entrepot) batiment;
                if (entrepot.getTypeMinerais() == robot.getTypeMinerais()) {
                    map.getMonde().popRobot(robot);
                }
            }
            else {
                String newdikjtra;
                for (Entrepot entrepot : map.getEntrepot()) {
                    if (entrepot.getTypeMinerais()==robot.getTypeMinerais()) {
                        newdikjtra = distraitStr(entrepot.getX(), entrepot.getY());
                        if (dikjtra.isEmpty() || newdikjtra.length() < dikjtra.length()) {
                            dikjtra = newdikjtra;
                        }
                    }
                }
            }
        }

        if (dikjtra.isEmpty()) {
            return;
        }
        robot.avancer(dikjtra.charAt(0), map.getMonde());
        map.update(robot);
    }

    //update the map with the robot position
    private void dijkstramap(){
        ArrayList<Pair<Integer, Integer>> notVisited = new ArrayList<>();
        ArrayList<Pair<Integer, Integer>> proche = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                notVisited.add(new Pair<>(i, j));
            }
        }
        mapdij[robot.getX()][robot.getY()] = 0;
        notVisited.remove(new Pair<>(robot.getX(), robot.getY()));

        if (robot.getX() - 1 >= 0) {
            //if the sector is available, the value is 1, if not, the value is -1
            if (map.getSecteur(robot.getX()-1, robot.getY()).getdisponible()) {
                mapdij[robot.getX()-1][robot.getY()] = 1;
                proche.addAll(getProche(robot.getX()-1, robot.getY(), notVisited));
            } else {
                mapdij[robot.getX()-1][robot.getY()] = -1;
            }
            notVisited.remove(new Pair<>(robot.getX()-1, robot.getY()));
        }
        if (robot.getX() + 1 < 10) {
            //if the sector is available, the value is 1, if not, the value is -1
            if (map.getSecteur(robot.getX()+1, robot.getY()).getdisponible()) {
                mapdij[robot.getX()+1][robot.getY()] = 1;
                proche.addAll(getProche(robot.getX()+1, robot.getY(), notVisited));
            } else {
                mapdij[robot.getX()+1][robot.getY()] = -1;
            }
            notVisited.remove(new Pair<>(robot.getX()+1, robot.getY()));
        }
        if (robot.getY() - 1 >= 0) {
            //if the sector is available, the value is 1, if not, the value is -1
            if (map.getSecteur(robot.getX(), robot.getY()-1).getdisponible()) {
                mapdij[robot.getX()][robot.getY()-1] = 1;
                proche.addAll(getProche(robot.getX(), robot.getY()-1, notVisited));
            } else {
                mapdij[robot.getX()][robot.getY()-1] = -1;
            }
            notVisited.remove(new Pair<>(robot.getX(), robot.getY()-1));
        }
        if (robot.getY() + 1 < 10) {
            //if the sector is available, the value is 1, if not, the value is -1
            if (map.getSecteur(robot.getX(), robot.getY()+1).getdisponible()) {
                mapdij[robot.getX()][robot.getY()+1] = 1;
                proche.addAll(getProche(robot.getX(), robot.getY()+1, notVisited));
            } else {
                mapdij[robot.getX()][robot.getY()+1] = -1;
            }
            notVisited.remove(new Pair<>(robot.getX(), robot.getY()+1));
        }

        //dijkstra algorithm to find the shortest path to all the sectors
        while (!proche.isEmpty()) {
            ArrayList<Pair<Integer, Integer>> newProche = new ArrayList<>();
            for (Pair<Integer, Integer> pair : proche) {
                Secteur secteur = map.getSecteur(pair.getKey(), pair.getValue());
                if (secteur == null || (secteur.getdisponible())) {
                    mapdij[pair.getKey()][pair.getValue()] = plusPetitVoisin(pair.getKey(), pair.getValue())+1;
                    newProche.addAll(getProche(pair.getKey(), pair.getValue(), notVisited));
                } else if(!secteur.getNature().equals(Nature.eau) && secteur.getbatiment() != null){
                    mapdij[pair.getKey()][pair.getValue()] = plusPetitVoisin(pair.getKey(), pair.getValue())+1;
                    newProche.addAll(getProche(pair.getKey(), pair.getValue(), notVisited));
                } else {
                    mapdij[pair.getKey()][pair.getValue()] = -1;
                }
                notVisited.remove(pair);
            }
            proche = newProche;
        }
    }

    //return the sectors around a sector
    private ArrayList<Pair<Integer, Integer>> getProche(int x, int y, ArrayList<Pair<Integer, Integer>> notVisited) {
        ArrayList<Pair<Integer, Integer>> proche = new ArrayList<>();
        for (Pair<Integer, Integer> pair : notVisited) {
            if (pair.getKey() == x - 1 && pair.getValue() == y) {
                proche.add(pair);
            }
            if (pair.getKey() == x + 1 && pair.getValue() == y) {
                proche.add(pair);
            }
            if (pair.getKey() == x && pair.getValue() == y - 1) {
                proche.add(pair);
            }
            if (pair.getKey() == x && pair.getValue() == y + 1) {
                proche.add(pair);
            }
        }
        return proche;
    }

    //return the smallest value of the sectors around a sector that is not -1 or 0/empty
    private int plusPetitVoisin(int x, int y) {
        int min = 1000;
        if (x - 1 >= 0 && mapdij[x-1][y] < min && mapdij[x-1][y] != -1 && mapdij[x-1][y] != 0) {
            min = mapdij[x-1][y];
        }
        if (x + 1 < 10 && mapdij[x+1][y] < min && mapdij[x+1][y] != -1 && mapdij[x+1][y] != 0) {
            min = mapdij[x+1][y];
        }
        if (y - 1 >= 0 && mapdij[x][y-1] < min && mapdij[x][y-1] != -1 && mapdij[x][y-1] != 0) {
            min = mapdij[x][y-1];
        }
        if (y + 1 < 10 && mapdij[x][y+1] < min && mapdij[x][y+1] != -1 && mapdij[x][y+1] != 0) {
            min = mapdij[x][y+1];
        }
        return min;
    }

    //return the path to go from the robot to a sector
    private String distraitStr(int x, int y) {
        StringBuilder chemin = new StringBuilder();
        int x2 = x;
        int y2 = y;
        while (mapdij[x2][y2] != 0){
            if (x2 - 1 >= 0 && mapdij[x2-1][y2] == mapdij[x2][y2]-1) {
                chemin.append("E");
                x2 = x2 - 1;
            }
            else if (x2 + 1 < 10 && mapdij[x2+1][y2] == mapdij[x2][y2]-1) {
                chemin.append("O");
                x2 = x2 + 1;
            }
            else if (y2 - 1 >= 0 && mapdij[x2][y2-1] == mapdij[x2][y2]-1) {
                chemin.append("S");
                y2 = y2 - 1;
            }
            else if (y2 + 1 < 10 && mapdij[x2][y2+1] == mapdij[x2][y2]-1) {
                chemin.append("N");
                y2 = y2 + 1;
            }
            else {
                break;
            }
        }
        chemin.reverse();
        return chemin.toString();
    }
}