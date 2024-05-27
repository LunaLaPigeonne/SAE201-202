package org.example.fx.Model;

public abstract class Element {
    private int x;
    private int y;
    private int nbMinerai;
    private int nbMaxMinerai;
    private Minerais typeMinerais;

    public Element(int x, int y, int nbMaxMinerai, int nbMinerai, Minerais typeMinerais){
        this.x = x;
        this.y = y;
        this.nbMaxMinerai = nbMaxMinerai;
        this.nbMinerai = nbMinerai;
        this.typeMinerais = typeMinerais;
    }
    public abstract int getid();

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getNbMinerai() {
        return nbMinerai;
    }

    public void setNbMinerai(int nbMinerai) {
        this.nbMinerai = nbMinerai;
    }

    public int getNbMaxMinerai() {
        return nbMaxMinerai;
    }

    public void setNbMaxMinerai(int nbMaxMinerai) {
        this.nbMaxMinerai = nbMaxMinerai;
    }

    public Minerais getTypeMinerais() {
        return typeMinerais;
    }

    public void setTypeMinerais(Minerais typeMinerais) {
        this.typeMinerais = typeMinerais;
    }
}
