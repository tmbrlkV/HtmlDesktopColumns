package com.columns.logic;

import com.columns.model.Field;
import com.columns.model.Figure;
import com.columns.model.Model;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ColumnPasterTest {

    private State state;
    private ColumnPaster paster;

    @Before
    public void init() {
        state = new State();
        paster = new ColumnPaster();
        state.setField(new Field(Model.ROWS, Model.COLUMNS));
        state.setFigure(new Figure());
        paster.setState(state);
    }

    @Test
    public void pasteFigureInField() throws Exception {
        state.setRow(Model.ROWS - 4);
        int[][] figureInField = paster.pasteFigureInField();

        int col = state.getCol();
        int row = state.getRow();
        int[] figureData = state.getFigure().getData();

        for(int i = 0; i < figureData.length; ++i) {
            assertEquals(figureData[i], figureInField[row + i][col]);
        }

    }

}