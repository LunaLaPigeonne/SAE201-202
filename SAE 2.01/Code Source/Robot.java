import java.util.Random;

public class Robot extends Element{
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
        switch (direction) {
            case 'N':
                if (super.getY() > 0 && monde.getSecteur(this.getX(),this.getY()-1).getdisponible()){
                    super.setY(super.getY()-1);
                    monde.getSecteur(this.getX(),this.getY()).setrobot(this);
                    monde.getSecteur(this.getX(),this.getY()+1).removeRobot();
                    return true;
                }
            case 'S':
                if (super.getY() < 9 && monde.getSecteur(this.getX(),this.getY()+1).getdisponible()){
                    super.setY(super.getY()+1);
                    monde.getSecteur(this.getX(),this.getY()).setrobot(this);
                    monde.getSecteur(this.getX(),this.getY()-1).removeRobot();
                    return true;
                }
            case 'E':
                if (super.getX() < 9 && monde.getSecteur(this.getX()+1,this.getY()).getdisponible()){
                    super.setX(super.getX()+1);
                    monde.getSecteur(this.getX(),this.getY()).setrobot(this);
                    monde.getSecteur(this.getX()-1,this.getY()).removeRobot();
                    return true;
                }
            case 'O':
                if (super.getX() > 0 && monde.getSecteur(this.getX()-1,this.getY()).getdisponible()){
                    super.setX(super.getX()-1);
                    monde.getSecteur(this.getX(),this.getY()).setrobot(this);
                    monde.getSecteur(this.getX()+1,this.getY()).removeRobot();
                    return true;
                }
        }
        return false;
    }

    public boolean miner(Monde monde){
        int res = 0;
        if (monde.getSecteur(this.getX(),this.getY()).getbatiment()!=null) {
            if (monde.getSecteur(this.getX(),this.getY()).getbatiment().getClass()==Mines.class){
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
            if (monde.getSecteur(this.getX(),this.getY()).getbatiment().getClass()==Entrepot.class){
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
