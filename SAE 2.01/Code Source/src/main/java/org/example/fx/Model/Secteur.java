package org.example.fx.Model;

public class Secteur {
    private Robot robot;
    private Batiment batiment;
    private Nature nature;

    public Secteur(Nature nature){
        this.robot = null;
        this.batiment = null;
        this.nature = nature;
    }

    public Nature getNature() {
        return nature;
    }

    public Batiment getbatiment() {
        return batiment;
    }

    public Robot getrobot() {
        return robot;
    }

    public boolean getdisponible(){
        return (robot == null && nature != Nature.eau);
    }

    public boolean getdisponibleBatiment(){
        return (batiment == null && nature != Nature.eau);
    }

    public void setrobot(Robot robot) {
        this.robot = robot;
    }

    public void setbatiment(Batiment batiment) {
        this.batiment = batiment;
    }

    public void setNature(Nature nature) {
        this.nature = nature;
    }

    public void removeRobot() {
        this.robot = null;
    }

    public void removeBatiment() {
        this.batiment = null;
    }
}
