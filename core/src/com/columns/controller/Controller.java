package com.columns.controller;

import com.columns.logic.State;
import com.columns.model.Model;
import com.columns.model.ModelListener;
import com.columns.view.View;

public class Controller implements ModelListener {
    private View view;
    private Model model;


    @Override
    public void onChange(State state) {
        view.draw(state);
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public void setView(View view) {
        this.view = view;
    }

    public void moveLeft() {
        model.moveLeft();
    }

    public void moveRight() {
        model.moveRight();
    }

    public void moveDown() {
        model.moveDown();
    }

    public void dropDown() {
        model.dropDown();
    }

    public void rotateUp() {
        model.rotateUp();
    }

    public void rotateDown() {
        model.rotateDown();
    }

    public void levelUp() {
        model.levelUp();
    }

    public void levelDown() {
        model.levelDown();
    }
}
