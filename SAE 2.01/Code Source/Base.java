import java.util.ArrayList;
public class Base {
    private ArrayList<Robot> lstRobot;
    private ArrayList<Entrepot> lstEntrepot;
    private ArrayList<Mines> lstMines;
    private ArrayList<Secteur> latSecteurEau;

    public Base(){
        lstRobot = new ArrayList<Robot>();
        lstEntrepot = new ArrayList<Entrepot>();
        lstMines = new ArrayList<Mines>();
        latSecteurEau = new ArrayList<Secteur>();
    }

    public void addRobot(Robot robot, Monde monde){
        lstRobot.add(robot);
        monde.getSecteur(robot.getX(), robot.getY()).setrobot(robot);
    }

    public void addEntrepot(Entrepot entrepot, Monde monde){
        lstEntrepot.add(entrepot);
        monde.getSecteur(entrepot.getX(), entrepot.getY()).setbatiment(entrepot);
    }

    public void addMines(Mines mines , Monde monde){
        lstMines.add(mines);
        monde.getSecteur(mines.getX(), mines.getY()).setbatiment(mines);
    }

    public void addSecteurEau(int x, int y, Monde monde){
        latSecteurEau.add(monde.getSecteur(x, y));
        monde.getSecteur(x, y).setNature(Nature.eau);
    }

    public ArrayList<Robot> getLstRobot() {
        return lstRobot;
    }

    public ArrayList<Entrepot> getLstEntrepot() {
        return lstEntrepot;
    }

    public ArrayList<Mines> getLstMines() {
        return lstMines;
    }
}