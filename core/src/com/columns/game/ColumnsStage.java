package com.columns.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.columns.controller.ControlEvents;
import com.columns.controller.Controller;
import com.columns.model.Model;
import com.columns.view.View;
import com.columns.view.entity.Box;

public class ColumnsStage extends Stage {
    private final Controller controller = new Controller();
    private final ControlEvents controlEvents = new ControlEvents(this, controller);
    private final Model model = new Model();

    ColumnsStage() {
        OrthographicCamera camera = new OrthographicCamera();
        camera.setToOrtho(true);
        setViewport(new ScreenViewport(camera));
        Gdx.input.setInputProcessor(this);
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
        controlEvents.handleControls();
        controlEvents.handleGameOver(model);
    }
}
