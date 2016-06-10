package com.columns.logic;

class ColumnPaster {
    private int[] figureData;
    private int[][] fieldData;
    private State state;

    void setState(State state) {
        figureData = state.getFigure().getData();
        fieldData = state.getField().getData();
        this.state = state;
    }

    int[][] pasteFigureInField() {
        for (int row = 0; row < figureData.length; row++) {
            if (figureData[row] != 0) {
                fieldData[state.getRow() + row][state.getCol()] = figureData[row];
            }
        }
        return fieldData;
    }
}
