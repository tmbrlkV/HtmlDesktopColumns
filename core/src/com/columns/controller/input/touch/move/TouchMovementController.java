package com.columns.controller.input.touch.move;

import com.badlogic.gdx.math.Vector2;
import com.columns.controller.Controller;
import com.columns.controller.input.touch.SimpleTouchInputProcessor;

public class TouchMovementController extends SimpleTouchInputProcessor {
    private Controller controller;

    public TouchMovementController(Controller controller) {
        this.controller = controller;
    }

    private Vector2 lastTouch = new Vector2();

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        lastTouch.set(screenX / 60, screenY / 60);
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        Vector2 newTouch = new Vector2(screenX / 60, screenY / 60);
        Vector2 delta = newTouch.cpy().sub(lastTouch);
        if (Math.abs(delta.x) > Math.abs(delta.y)) {
            if (delta.x > 0) {
                controller.moveRight();
            } else if (delta.x < 0) {
                controller.moveLeft();
            }
        }
        lastTouch = newTouch;

        return false;
    }
}
