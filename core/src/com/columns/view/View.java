package com.columns.view;

import com.columns.logic.State;

public class View {

    public void draw(State state) {
        drawField(state.getField().getData());
        drawFigure(state.getFigure().getData(), state.getRow(), state.getCol());
    }

    private void drawField(int[][] fieldData) {
        for (int i = 0; i < fieldData.length; ++i) {
            for (int j = 0; j < fieldData[i].length; ++j) {
                drawBox(fieldData[i][j], i, j);
            }
        }
    }

    private void drawFigure(int[] figureData, int row, int col) {
        for (int i = 0; i < figureData.length; ++i) {
            if (figureData[i] == 0) {
                continue;
            }
            drawBox(figureData[i], row + i, col);
            drawBox(figureData[i], row + i, col);
        }

    }

    protected void drawBox(int colorIndex, int row, int col) {
    }
}
