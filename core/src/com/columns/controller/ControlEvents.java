package com.columns.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.utils.Timer;
import com.columns.game.ColumnsStage;
import com.columns.model.Model;

public class ControlEvents {
    private ColumnsStage stage;
    private static Controller controller;
    private static int orientation;
    private static boolean isDroppingDown;
    private boolean isGamePaused;

    public ControlEvents(ColumnsStage stage, Controller controller) {
        this.stage = stage;
        ControlEvents.controller = controller;
    }

    public void handleControls() {
        stage.addListener(new InputListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                switch (keycode) {
                    case Input.Keys.LEFT:
                        orientation--;
                        break;
                    case Input.Keys.RIGHT:
                        orientation++;
                        break;
                    case Input.Keys.SPACE:
                        isDroppingDown = true;
                        break;
                    case Input.Keys.P:
                        if (isGamePaused) {
                            Timer.instance().start();
                            isGamePaused = false;
                        } else {
                            Timer.instance().stop();
                            isGamePaused = true;
                        }
                        break;
                }
                return true;
            }

            @Override
            public boolean keyUp(InputEvent event, int keycode) {
                switch (keycode) {
                    case Input.Keys.LEFT:
                        orientation++;
                        break;
                    case Input.Keys.RIGHT:
                        orientation--;
                        break;
                    case Input.Keys.SPACE:
                        isDroppingDown = false;
                        break;
                }
                if (!isGamePaused) {
                    switch (keycode) {
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
                }
                return true;
            }
        });
        SpeedChanger.setSpeed(1.0f);
    }

    public void handleGameOver(final Model model) {
        stage.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                if (model.isGameOver()) {
                    Gdx.input.setInputProcessor(null);
                }
                return false;
            }
        });
    }


    public static class SpeedChanger {

        private static float _speed = 1.0f;

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

    }
}
