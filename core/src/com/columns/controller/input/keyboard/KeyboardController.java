package com.columns.controller.input.keyboard;

import com.badlogic.gdx.Input;
import com.columns.controller.Controller;
import com.columns.controller.MovementChanger;

public class KeyboardController extends SimpleKeyboardInputProcessor {
    private Controller controller;

    public KeyboardController(Controller controller) {
        this.controller = controller;
        MovementChanger.setController(controller);
        MovementChanger.setSpeed(1.0f);
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.LEFT:
                MovementChanger.orientationLeft();
                break;
            case Input.Keys.RIGHT:
                MovementChanger.orientationRight();
                break;
            case Input.Keys.SPACE:
                MovementChanger.dropDown(true);
                break;
            case Input.Keys.UP:
                controller.rotateUp();
                break;
            case Input.Keys.DOWN:
                controller.rotateDown();
                break;
            case Input.Keys.PLUS:
                controller.levelUp();
                break;
            case Input.Keys.MINUS:
                controller.levelDown();
                break;
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.LEFT:
                MovementChanger.orientationRight();
                break;
            case Input.Keys.RIGHT:
                MovementChanger.orientationLeft();
                break;
            case Input.Keys.SPACE:
                MovementChanger.dropDown(false);
                break;
        }
        return true;
    }
}
