package com.columns.controller.input.touch.rotate;

import com.badlogic.gdx.input.GestureDetector;
import com.columns.controller.input.touch.DirectionListener;

class TouchFeatureListener extends GestureDetector.GestureAdapter {
    private DirectionListener listener;

    TouchFeatureListener(DirectionListener listener) {
        this.listener = listener;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        if (Math.abs(velocityX) < Math.abs(velocityY)) {
            if (velocityY > 0) {
                listener.onDown();
            } else {
                listener.onUp();
            }
        }
        return super.fling(velocityX, velocityY, button);
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        listener.drop();
        return super.tap(x, y, count, button);
    }
}
