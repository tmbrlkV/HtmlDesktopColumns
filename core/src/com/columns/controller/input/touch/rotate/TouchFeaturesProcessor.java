package com.columns.controller.input.touch.rotate;

import com.badlogic.gdx.input.GestureDetector;
import com.columns.controller.input.touch.DirectionListener;

public class TouchFeaturesProcessor extends GestureDetector {
    public TouchFeaturesProcessor(DirectionListener listener) {
        super(new TouchFeatureListener(listener));
    }
}
