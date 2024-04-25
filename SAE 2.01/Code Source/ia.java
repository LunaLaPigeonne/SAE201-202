import java.util.ArrayList;

public class ia {

    public ia(){

    }

    public void action(Monde monde, Robot robot){
        if (robot.getNbMinerai()==robot.getNbMaxMinerai()|| (mineVide(monde, robot) && robot.getNbMinerai()>0)){
            deposer(monde, robot);
        } else if (!mineVide(monde, robot)){
            miner(monde, robot);
        }

    }

    private void miner(Monde monde, Robot robot) {
        ArrayList<Mines> mines = new ArrayList<>();
        ArrayList<Float> distance = new ArrayList<>();
        for (Mines mine :monde.getBase().getLstMines()){
            if (mine.getTypeMinerais() == robot.getTypeMinerais()){
                mines.add(mine);
            }
        }
        Mines res = mines.getFirst();
        int index = 0;
        if (!robot.miner(monde)){
            if (!(mines.size()==1)){
                for (Mines mine :mines){
                    if (mine.getNbMinerai()==0){
                        mines.remove(mine);
                    } else {
                        distance.add((float) Math.sqrt(Math.pow(mine.getX()-robot.getX(), 2)+Math.pow(mine.getY()-robot.getY(), 2)));
                    }
                }
                for (int i = 0; i < distance.size(); i++) {
                    if (distance.get(i) < distance.get(index)) {
                        res = mines.get(i);
                        index = i;
                    }
                }
            }
            robot.avancer(getDirection(monde, robot, res.getX(), res.getY()), monde);
        }
    }

    private boolean mineVide(Monde monde, Robot robot) {
        ArrayList<Mines> mines = new ArrayList<>();
        int index = 0;
        for (Mines mine :monde.getBase().getLstMines()){
            if (mine.getTypeMinerais() == robot.getTypeMinerais()){
                mines.add(mine);
            }
        }
        for (Mines mine : mines)
            if (!(mine.getNbMinerai()>0)) index++;
        return index == mines.size();
    }

    private void deposer(Monde monde, Robot robot) {
        if (!robot.deposer(monde)){
            for (Entrepot entrepot : monde.getBase().getLstEntrepot()){
                if (entrepot.getTypeMinerais() == robot.getTypeMinerais()){
                    robot.avancer(getDirection(monde, robot, entrepot.getX(), entrepot.getY()), monde);
                }
            }
        }
    }

    private int[][] devloppeur(Monde monde, int[][] tab, int x, int y){
        int res = 0;
        if (x>0 && monde.getSecteur(x-1, y).getdisponible()){
            res= Math.min(tab[x-1][y], tab[x][y]+1);
            if (res!=tab[x-1][y]){
                tab[x-1][y] = res;
                tab = devloppeur(monde, tab, x-1, y);
            }
        }
        if (x<9 && monde.getSecteur(x+1, y).getdisponible()){
            res= Math.min(tab[x+1][y], tab[x][y]+1);
            if (res!=tab[x+1][y]){
                tab[x+1][y] = res;
                tab = devloppeur(monde, tab, x+1, y);
            }
        }
        if (y>0 && monde.getSecteur(x, y-1).getdisponible()){
            res= Math.min(tab[x][y-1], tab[x][y]+1);
            if (res!=tab[x][y-1]){
                tab[x][y-1] = res;
                tab = devloppeur(monde, tab, x, y-1);
            }
        }
        if (y<9 && monde.getSecteur(x, y+1).getdisponible()){
            res= Math.min(tab[x][y+1], tab[x][y]+1);
            if (res!=tab[x][y+1]){
                tab[x][y+1] = res;
                tab = devloppeur(monde, tab, x, y+1);
            }
        }
        return tab;
    }

    private char getDirection(Monde monde, Robot robot,int x, int y){
        ArrayList<Character> direction = new ArrayList<>();

        int[][] tab = new int[10][10];
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++)
                tab[i][j] = Integer.MAX_VALUE;
        tab[robot.getX()][robot.getY()] = 0;
        tab = devloppeur(monde, tab, robot.getX(), robot.getY());
        for (int i = tab[x][y]; i > 0; i--){
            if (x>0){
                if(tab[x-1][y] == i-1){
                    direction.add('E');
                    x--;
                }
            }
            if (x<9) {
                if (tab[x + 1][y] == i - 1) {
                    direction.add('O');
                    x++;
                }
            }
            if (y>0){
                if(tab[x][y-1] == i-1){
                    direction.add('S');
                    y--;
                }
            }
            if (y<9){
                if(tab[x][y+1] == i-1){
                    direction.add('N');
                    y++;
                }
            }
        }
        return direction.getLast();
    }


}
