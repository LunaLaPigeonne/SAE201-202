package org.example.fx.controller;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.example.fx.Model.Monde;
import org.example.fx.Model.Robot;
import org.example.fx.vue.Main;
import org.example.fx.vue.MainStage;
import org.example.fx.vue.Triangle;

public class eventManagerRobot implements EventHandler {
    Robot robot;
    Monde monde;
    public eventManagerRobot(Robot robot,Monde monde) {
        this.robot = robot;
        this.monde = monde;
    }

    @Override
    public void handle(Event event) {
        MainStage s=((MainStage) ((Node) event.getSource()).getScene().getWindow());
        if (s.getTitle().equals("Main Stage")) {
            if (((Node) event.getSource()).getId().equals("triangleup")) {
                if (robot.avancer('N',monde))
                    s.update(monde);
            }else if (((Node) event.getSource()).getId().equals("triangledown")) {
                if (robot.avancer('S',monde))
                    s.update(monde);
            }else if (((Node) event.getSource()).getId().equals("triangleleft")) {
                if (robot.avancer('O',monde))
                    s.update(monde);
            }else if (((Node) event.getSource()).getId().equals("triangleright")) {
                if (robot.avancer('E',monde))
                    s.update(monde);
            }else if (event.getSource() instanceof Button) {
                if (robot.miner(monde)) {
                    System.out.println("miner");
                    s.update(monde);
                }
                else if (robot.deposer(monde)) {
                    System.out.println("deposer");
                    s.update(monde);
                }
            }
        }


    }
}
