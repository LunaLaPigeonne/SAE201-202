public abstract class Batiment extends Element {
    public Batiment(int x, int y, int nbMaxMinerai, int nbMinerai, Minerais typeMinerais){
        super(x, y, nbMaxMinerai,nbMinerai, typeMinerais);
    }
    public Batiment(int x, int y, int nbMaxMinerai, Minerais typeMinerais){
        super(x, y, nbMaxMinerai, nbMaxMinerai, typeMinerais);
    }
}
