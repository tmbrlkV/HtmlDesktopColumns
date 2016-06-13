package com.columns.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.columns.controller.Controller;
import com.columns.controller.input.keyboard.KeyboardController;
import com.columns.controller.input.touch.rotate.TouchFeatureController;
import com.columns.controller.input.touch.rotate.TouchFeaturesProcessor;
import com.columns.controller.input.touch.move.TouchMovementController;
import com.columns.model.Model;
import com.columns.view.View;
import com.columns.view.entity.Box;

public class ColumnsStage extends Stage {
    private static final Controller controller = new Controller();
    private static final Model model = new Model();

    ColumnsStage() {
        OrthographicCamera camera = new OrthographicCamera();
        camera.setToOrtho(true, 210, 480);
        setViewport(new FitViewport(210, 480, camera));
        setInputProcessors();
    }

    private void setInputProcessors() {
        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(new KeyboardController(controller));
        multiplexer.addProcessor(new TouchMovementController(controller));
        multiplexer.addProcessor(new TouchFeaturesProcessor(new TouchFeatureController(controller)));
        Gdx.input.setInputProcessor(multiplexer);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    void init() {
        model.addListener(controller);
        View view = new View() {
            private Box[][] boxes = new Box[Model.ROWS][Model.COLUMNS];

            @Override
            protected void drawBox(int colorIndex, int row, int col) {

                if (boxes[row][col] == null) {
                    Box box = new Box(colorIndex);
                    boxes[row][col] = box;
                    box.setBounds(col * Box.SIZE, row * Box.SIZE, Box.SIZE, Box.SIZE);
                    ColumnsStage.this.addActor(box);
                }

                boxes[row][col].setColor(colorIndex);
            }

        };
        controller.setView(view);
        controller.setModel(model);
    }
}
