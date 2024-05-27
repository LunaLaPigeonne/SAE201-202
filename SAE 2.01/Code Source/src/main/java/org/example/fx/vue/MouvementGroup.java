package org.example.fx.vue;

import javafx.scene.control.Button;
import org.example.fx.Model.Monde;
import org.example.fx.Model.Robot;
import org.example.fx.controller.eventManagerRobot;

public class MouvementGroup extends javafx.scene.Group {
    Monde monde;
    Robot robot;
    Triangle[] triangles;
    public MouvementGroup(Robot robot, Monde monde) {
        super();
        this.robot = robot;
        this.monde = monde;
        triangles = new Triangle[4];

        Button action = new Button("action");
        action.setId("action");
        action.setOnMouseClicked(new eventManagerRobot(robot,monde));
        Triangle triangleup = new Triangle(50,50,50,270);
        triangleup.setLayoutX(50);
        triangleup.setLayoutY(50);
        Triangle triangledown = new Triangle(50,50,50,90);
        triangledown.setLayoutX(50);
        triangledown.setLayoutY(150);
        Triangle triangleleft = new Triangle(50,50,50,180);
        triangleleft.setLayoutX(0);
        triangleleft.setLayoutY(100);
        Triangle triangleright = new Triangle(50,50,50,0);
        triangleright.setLayoutX(100);
        triangleright.setLayoutY(100);

        triangles[0] = triangleup;
        triangles[1] = triangledown;
        triangles[2] = triangleleft;
        triangles[3] = triangleright;

        triangleup.setId("triangleup");
        triangledown.setId("triangledown");
        triangleleft.setId("triangleleft");
        triangleright.setId("triangleright");

        triangleup.setOnMouseClicked(new eventManagerRobot(robot,monde));
        triangledown.setOnMouseClicked(new eventManagerRobot(robot,monde));
        triangleleft.setOnMouseClicked(new eventManagerRobot(robot,monde));
        triangleright.setOnMouseClicked(new eventManagerRobot(robot,monde));

        this.getChildren().addAll(action,triangleup,triangledown,triangleleft,triangleright);
    }
    public Robot getrobot() {
        return robot;
    }
}
