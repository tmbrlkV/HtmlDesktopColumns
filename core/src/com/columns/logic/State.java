package com.columns.logic;


import com.columns.model.Field;
import com.columns.model.Figure;

public class State {
    private Field field;
    private Figure figure;

    private int row;
    private int col;

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public Figure getFigure() {
        return figure;
    }

    public void setFigure(Figure figure) {
        this.figure = figure;
        this.row = 0;
        this.col = field.getColumns() / 2;
    }

    public int getRow() {
        return row;
    }

    void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    void setCol(int col) {
        this.col = col;
    }
}
