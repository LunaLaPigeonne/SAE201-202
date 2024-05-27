import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class jUnit {

    protected static Monde monde;
    protected static Robot robot;
    protected static Robot robot2;

    @BeforeEach
    void setUp() throws Exception {
        monde = new Monde(1,10,1,2);
    }

    @AfterEach
    void tearDown() throws Exception {
        monde = null;
        robot = null;
        robot2 = null;
    }

    @Test
    void testMoveFreely() {
        robot = new Robot(5,5,Minerais.nickel); // Robot at the center of the grid
        monde.getSecteur(5,5).setrobot(robot);

        assertTrue(robot.avancer('N', monde));
        assertTrue(robot.avancer('S', monde));
        assertTrue(robot.avancer('E', monde));
        assertTrue(robot.avancer('O', monde));
    }

    @Test
    void testMoveBlocked() {
        robot = new Robot(5,5,Minerais.nickel); // Robot at the center of the grid
        monde.getSecteur(5,5).setrobot(robot);
        monde.getSecteur(5,6).setNature(Nature.eau); // Water at the north of the robot
        monde.getSecteur(5,4).setNature(Nature.eau); // Water at the south of the robot
        monde.getSecteur(6,5).setNature(Nature.eau); // Water at the east of the robot
        monde.getSecteur(4,5).setNature(Nature.eau); // Water at the west of the robot

        assertFalse(robot.avancer('N', monde));
        assertFalse(robot.avancer('S', monde));
        assertFalse(robot.avancer('E', monde));
        assertFalse(robot.avancer('O', monde));
    }

    @Test
    void testMoveAroundGrid() {
        robot = new Robot(0,0,Minerais.nickel); // Robot at the top left corner of the grid
        monde.getSecteur(0,0).setrobot(robot);

        assertFalse(robot.avancer('N', monde));
        assertFalse(robot.avancer('O', monde));
        assertTrue(robot.avancer('S', monde));
        assertTrue(robot.avancer('E', monde));

        robot = new Robot(9,9,Minerais.nickel); // Robot at the bottom right corner of the grid
        monde.getSecteur(9,9).setrobot(robot);

        assertFalse(robot.avancer('S', monde));
        assertFalse(robot.avancer('E', monde));
        assertTrue(robot.avancer('N', monde));
        assertTrue(robot.avancer('O', monde));
    }

    @Test
    void testMoveIntoAnotherRobot() {
        robot = new Robot(5,5,Minerais.nickel); // Robot at the center of the grid
        robot2 = new Robot(5,6,Minerais.nickel); // Another robot at the south of the first robot
        monde.getSecteur(5,5).setrobot(robot);
        monde.getSecteur(5,6).setrobot(robot2);

        assertFalse(robot.avancer('S', monde));
        assertTrue(robot.avancer('N', monde));
        assertTrue(robot.avancer('E', monde));
        assertTrue(robot.avancer('O', monde));
    }
}