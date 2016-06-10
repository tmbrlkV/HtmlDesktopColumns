package com.columns.logic;

import com.columns.model.Field;
import com.columns.model.Figure;

public class Logic {
    private State state;
    private FieldManipulator manipulator;
    private boolean isGameOver;

    public Logic(State state) {
        this.state = state;
        manipulator = new FieldManipulator();
    }

    public boolean moveLeft() {
        state.setCol(state.getCol() - 1);
        if (!isFigureFitsField()) {
            state.setCol(state.getCol() + 1);
            return false;
        }
        return true;
    }

    public boolean moveRight() {
        state.setCol(state.getCol() + 1);
        if (!isFigureFitsField()) {
            state.setCol(state.getCol() - 1);
            return false;
        }
        return true;
    }

    public boolean moveDown() {
        state.setRow(state.getRow() + 1);
        if (!isFigureFitsField()) {
            state.setRow(state.getRow() - 1);
            if (!isGameOver) {
                pasteFigureInField();
                state.setFigure(new Figure());
            }
            if (!isFigureFitsField()) {
                isGameOver = true;
                return false;
            }
        }
        return true;
    }

    private void pasteFigureInField() {
        manipulator.setState(state);
        manipulator.manipulate();
    }


    public boolean dropDown() {
        return moveDown();
    }

    public boolean rotateUp() {
        int[] data = state.getFigure().getData();

        int temp = data[0];
        data[0] = data[1];
        data[1] = data[2];
        data[2] = temp;

        return true;
    }

    public boolean rotateDown() {
        int[] data = state.getFigure().getData();

        int temp = data[0];
        data[0] = data[2];
        data[2] = data[1];
        data[1] = temp;

        return true;
    }

    private boolean isFigureFitsField() {
        int[] figureData = state.getFigure().getData();
        Field field = state.getField();
        int[][] fieldData = field.getData();

        for (int r = 0; r < figureData.length; r++) {
            int row = state.getRow() + r;
            int col = state.getCol();

            if (isNotBorder(field, row, col)) {
                return false;
            }
            if (isFieldCellNotEmpty(fieldData[row][col])) {
                return false;
            }
        }
        return true;
    }

    private boolean isNotBorder(Field field, int row, int col) {
        return row < 0 || col < 0 || row >= field.getRows() || col >= field.getColumns();
    }

    private boolean isFieldCellNotEmpty(int cell) {
        return cell > 0;
    }

    public State getState() {
        return state;
    }


    public boolean levelUp() {
        GameConfiguration.levelUp();
        return true;
    }

    public boolean levelDown() {
        GameConfiguration.levelDown();
        return true;
    }
}
