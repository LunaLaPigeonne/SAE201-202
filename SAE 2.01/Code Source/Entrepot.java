public class Entrepot extends Batiment{
    private static int count = 0;
    private final int idEntrepot;

    public Entrepot(int x, int y, Minerais typeElement){
        super(x, y, Integer.MAX_VALUE, 0, typeElement);
        count++;
        this.idEntrepot = count;
    }

    public void recupererMinerai(int nbMinerai){
        super.setNbMinerai(super.getNbMinerai()+nbMinerai);
    }

    public int getid() {
        return idEntrepot;
    }
}
