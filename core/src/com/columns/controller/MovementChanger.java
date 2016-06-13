package com.columns.controller;

import com.badlogic.gdx.utils.Timer;

public class MovementChanger {
    private static int orientation;
    private static boolean isDroppingDown;
    private static float _speed = 1.0f;
    private static Controller controller;

    public static void setController(Controller controller) {
        MovementChanger.controller = controller;
    }

    public static void setSpeed(float speed) {
        _speed = speed;
        act();
        figureMovement();
    }

    private static void act() {
        Timer.instance().clear();
        Timer.instance().scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                controller.moveDown();
            }
        }, 0.0f, _speed);
    }

    private static void figureMovement() {
        Timer.schedule(new Timer.Task() {

            @Override
            public void run() {
                if (isDroppingDown) {
                    controller.dropDown();
                }
                if (orientation > 0) {
                    controller.moveRight();
                }
                if (orientation < 0) {
                    controller.moveLeft();
                }
            }
        }, 0.0f, 0.075f);
    }

    public static void orientationLeft() {
        orientation--;
    }

    public static void orientationRight() {
        orientation++;
    }

    public static void dropDown(boolean isDroppingDown) {
        MovementChanger.isDroppingDown = isDroppingDown;
    }
}
