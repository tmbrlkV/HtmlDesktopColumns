package com.columns.controller.input.touch.rotate;

import com.columns.controller.Controller;
import com.columns.controller.input.touch.DirectionListener;

public class TouchFeatureController implements DirectionListener {
    private Controller controller;

    public TouchFeatureController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void onUp() {
        controller.rotateUp();
    }

    @Override
    public void onDown() {
        controller.rotateDown();
    }

    @Override
    public void drop() {
        controller.dropDown();
    }
}
