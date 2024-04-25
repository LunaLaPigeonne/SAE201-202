import java.util.Random;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        Monde monde = new Monde(1,10,1,2);
        System.out.println(monde.toString(0));
        boolean fin = false;
        int tour = 0;
        int i=0;
        while (!fin) {
            i=0;
            ia ia = new ia();
            for (Robot robot : monde.getBase().getLstRobot()) {
                ia.action(monde, robot);
            }
            tour++;
            System.out.println(monde.toString(tour));
            for (Robot robot : monde.getBase().getLstRobot()) {
                if (robot.getNbMinerai() == 0) {
                    i++;
                }
            }
        }

    }
}