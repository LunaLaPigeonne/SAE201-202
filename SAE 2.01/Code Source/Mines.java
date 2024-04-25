import java.util.Random;

public class Mines extends Batiment{
    private static int count = 0;
    private final int idMines;

    public Mines(int x, int y, Minerais typeElement){
        super(x, y, new Random().nextInt(50)+50, typeElement);
        count++;
        this.idMines = count;
    }
    public int extraireMinerai(int nbMinerai){
        int res = 0;
        if (super.getNbMinerai() >= nbMinerai) {
            super.setNbMinerai(super.getNbMinerai()-nbMinerai);
            res = nbMinerai;
        }else {
            res = super.getNbMinerai();
            super.setNbMinerai(0);
        }
        return res;
    }
    public int getid() {
        return idMines;
    }
}
