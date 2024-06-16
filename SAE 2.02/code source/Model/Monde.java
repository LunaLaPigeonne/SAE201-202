package org.example.fx.Model;

import java.util.ArrayList;
import java.util.Random;

public class Monde {
    private Secteur[][] matrice;
    private Base base;
    private int tour=0;

    public Monde(int minSecteurEau,int maxSecteurEau, int minMines, int maxMines, int minRobot, int maxRobot){
        matrice = new Secteur[10][10];
        base = new Base();
        for (int i = 0; i < 10; i++){
            for (int j = 0; j < 10; j++){
                matrice[i][j] = new Secteur(Nature.terre);
            }
        }
        int i = 0;
        while (i < new Random().nextInt(maxSecteurEau-minSecteurEau+1)+minSecteurEau) {
            int x = new Random().nextInt(10);
            int y = new Random().nextInt(10);
            if (getSecteur(x, y).getNature() == Nature.terre) {
                addSecteurEau(x, y);
                i++;
            }
        }
        i = 0;
        while (i < new Random().nextInt(maxMines-minMines+1)+minMines) {
            int x = new Random().nextInt(10);
            int y = new Random().nextInt(10);
            if (getSecteur(x, y).getNature() == Nature.terre) {
                addMines(new Mines(x, y, Minerais.or));
                i++;
            }
        }
        i = 0;
        while (i < new Random().nextInt(maxMines-minMines+1)+minMines) {
            int x = new Random().nextInt(10);
            int y = new Random().nextInt(10);
            if (getSecteur(x, y).getdisponibleBatiment()) {
                addMines(new Mines(x, y, Minerais.nickel));
                i++;
            }
        }
        i = 0;
        while (i < 2) {
            int x = new Random().nextInt(10);
            int y = new Random().nextInt(10);
            if (getSecteur(x, y).getdisponibleBatiment()) {
                if (i == 0) {
                    addEntrepot(new Entrepot(x, y, Minerais.nickel));
                } else {
                    addEntrepot(new Entrepot(x, y, Minerais.or));
                }
                i++;
            }
        }
        i = 0;
        while (i < new Random().nextInt(maxRobot-minRobot+1)+minRobot) {
            int x = new Random().nextInt(10);
            int y = new Random().nextInt(10);
            if (getSecteur(x, y).getdisponible()) {
                addRobot(new Robot(x, y, Minerais.nickel));
                i++;
            }
        }
        i = 0;
        while (i < new Random().nextInt(maxRobot-minRobot+1)+minRobot) {
            int x = new Random().nextInt(10);
            int y = new Random().nextInt(10);
            if (getSecteur(x, y).getdisponible()) {
                addRobot(new Robot(x, y, Minerais.or));
                i++;
            }
        }

    }

    public void tourSuivant(){
        tour++;
    }

    public int getTour(){
        return tour;
    }

    public void popRobot(Robot robot){
        base.popRobot(robot);
        getSecteur(robot.getX(), robot.getY()).removeRobot();

    }

    public void addRobot(Robot robot){
        base.addRobot(robot, this);
    }

    public void addEntrepot(Entrepot entrepot){
        base.addEntrepot(entrepot, this);
    }

    public void addMines(Mines mines){
        base.addMines(mines, this);
    }

    public void addSecteurEau(int x, int y){
        base.addSecteurEau(x, y, this);
    }

    public Secteur getSecteur(int x, int y){
        return matrice[x][y];
    }

    public Base getBase(){
        return base;
    }

    public String toString(int tour){
        String res = " +-00-+-01-+-02-+-03-+-04-+-05-+-06-+-07-+-08-+-09-+\n";
        String l1;
        String l2;
        Secteur s;
        ArrayList<Element> lst = new ArrayList<Element>();
        lst.addAll(base.getLstMines());
        lst.addAll(base.getLstEntrepot());
        lst.addAll(base.getLstRobot());
        for (int i = 0; i < 10; i++){
            l1=i+"|";
            l2=" |";
            for (int j = 0; j < 10; j++) {
                s = matrice[j][i];
                if (s.getNature() == Nature.eau) {
                    l1 += " XX ";
                    l2 += " XX ";
                } else {
                    if (s.getbatiment() != null) {
                        switch (s.getbatiment().getClass().getName()) {
                            case "Mines":
                                l1 += "M ";
                                break;
                            case "Entrepot":
                                l1 += "E ";
                                break;
                            default:
                                l1 += "  ";
                        }
                        if (s.getbatiment().getid() < 10) {
                            l1 += "0";
                        }
                        l1 += Integer.toString(s.getbatiment().getid());
                    } else {
                        l1 += "    ";
                    }
                    if (s.getrobot() != null) {
                        l2 += "R ";
                        if (s.getrobot().getid() < 10) {
                            l2 += "0";
                        }
                        l2 += Integer.toString(s.getrobot().getid());
                    } else {
                        l2 += "    ";
                    }
                }
                l1 += "|";
                l2 += "|";
            }
            res += l1 + "\n" + l2 + "\n";
            res += " +----+----+----+----+----+----+----+----+----+----+\n";
        }
        res += "\nTour " + tour + "\n";

        for (Element e : lst) {
            switch (e.getClass().getName()){
                case "Robot":
                    res+="R";
                    break;
                case "Mines":
                    res+="M";
                    break;
                case "Entrepot":
                    res+="E";
                    break;

            }
            res += e.getid() + "   " + e.getY() + " " + e.getX() + "  ";
            if (e.getTypeMinerais() == Minerais.nickel) {
                res += "NI";
            } else if (e.getTypeMinerais() == Minerais.or) {
                res += "OR";
            }
            if (e.getClass().getName() == "Entrepot") {
                res += "  " + e.getNbMinerai() + "\n";
            } else {
                res += "  " + e.getNbMinerai() + "/" + e.getNbMaxMinerai() + "\n";
            }
        }
        return res;
    }
}
