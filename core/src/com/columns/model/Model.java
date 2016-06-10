package com.columns.model;

import com.columns.logic.Logic;
import com.columns.logic.State;

import java.util.LinkedList;
import java.util.List;

public class Model {
    public static final int ROWS = 15;
    public static final int COLUMNS = 7;
    private Logic logic;
    private List<ModelListener> listeners = new LinkedList<>();
    private boolean isGameOver;

    public Model() {
        Field field = new Field(ROWS, COLUMNS);
        State state = new State();

        state.setField(field);
        state.setFigure(new Figure());

        logic = new Logic(state);
    }

    public void addListener(ModelListener modelListener) {
        listeners.add(modelListener);
    }

    public void removeListener(ModelListener modelListener) {
        listeners.remove(modelListener);
    }

    private void fireChangedEvent() {
        for (ModelListener modelListener : listeners) {
            modelListener.onChange(logic.getState());
        }
    }

    public void moveLeft() {
        if (logic.moveLeft()) {
            fireChangedEvent();
        }
    }

    public void moveRight() {
        if (logic.moveRight()) {
            fireChangedEvent();
        }
    }

    public void moveDown() {
        if (logic.moveDown()) {
            fireChangedEvent();
        } else {
            listeners.clear();
            isGameOver = true;
        }
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public void dropDown() {
        if (logic.dropDown()) {
            fireChangedEvent();
        }
    }

    public void rotateUp() {
        if (logic.rotateUp()) {
            fireChangedEvent();
        }
    }

    public void rotateDown() {
        if (logic.rotateDown()) {
            fireChangedEvent();
        }
    }

    public void levelUp() {
        logic.levelUp();
    }

    public void levelDown() {
        logic.levelDown();
    }
}
