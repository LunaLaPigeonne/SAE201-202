package org.example.fx.Model;

import java.util.Random;

public class Robot extends Element {
    private static int count = 0;
    private final int idRobot;
    private final int extraction;

    public Robot(int x, int y, Minerais typeMinerais){
        super(x, y, new Random().nextInt(4)+5, 0, typeMinerais);
        this.extraction = new Random().nextInt(2)+1;
        count++;
        this.idRobot = count;
    }

    public boolean avancer(char direction, Monde monde){
        int newX = this.getX();
        int newY = this.getY();

        switch (direction) {
            case 'N':
                newY--;
                break;
            case 'S':
                newY++;
                break;
            case 'E':
                newX++;
                break;
            case 'O':
                newX--;
                break;
        }

        // Check if the new position is within the grid
        if (newX >= 0 && newX < 10 && newY >= 0 && newY < 10) {
            // Check if the new position is not occupied by another robot and not water
            if (monde.getSecteur(newX, newY).getdisponible()) {
                // Remove the robot from the current sector
                monde.getSecteur(this.getX(), this.getY()).removeRobot();

                // Update the robot's position
                this.setX(newX);
                this.setY(newY);

                // Place the robot in the new sector
                monde.getSecteur(newX, newY).setrobot(this);

                return true;
            }
        }

        return false;
    }

    public boolean miner(Monde monde){
        int res = 0;
        if (monde.getSecteur(this.getX(),this.getY()).getbatiment()!=null) {
            if (monde.getSecteur(this.getX(),this.getY()).getbatiment().getClass()== Mines.class){
                Mines mine = (Mines) monde.getSecteur(this.getX(),this.getY()).getbatiment();
                if (mine.getTypeMinerais()==super.getTypeMinerais()){
                    res = mine.extraireMinerai(Math.min(this.extraction,this.getNbMaxMinerai()-this.getNbMinerai()));
                    if (res > 0) {
                        this.setNbMinerai(this.getNbMinerai() + res);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean deposer(Monde monde){
        int res = 0;
        if (monde.getSecteur(this.getX(),this.getY()).getbatiment()!=null) {
            if (monde.getSecteur(this.getX(),this.getY()).getbatiment().getClass()== Entrepot.class){
                Entrepot entrepot = (Entrepot) monde.getSecteur(this.getX(),this.getY()).getbatiment();
                if (entrepot.getTypeMinerais()==super.getTypeMinerais()){
                    res = this.getNbMinerai();
                    if (res > 0) {
                        entrepot.recupererMinerai(res);
                        this.setNbMinerai(0);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public int getid() {
        return idRobot;
    }
}
