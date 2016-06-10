package com.columns.logic;

import com.columns.model.Field;
import com.columns.model.Figure;
import com.columns.model.Model;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FieldManipulatorTest {

    private FieldManipulator manipulator;
    private State state;
    private int[][] originFieldData;
    private int COL_SIZE = 5;
    private int COL;


    @Before
    public void init() {
        state = new State();
        state.setField(new Field(Model.ROWS, Model.COLUMNS));
        state.setFigure(new Figure());

        COL  = state.getCol();

        manipulator = new FieldManipulator();
        manipulator.setState(state);
        originFieldData = state.getField().getData();

    }


    @Test
    public void manipulate() throws Exception {

        insertVerticalTripletAndTwin();
        manipulator.manipulate();
        int[][] newFieldData = state.getField().getData();


        for (int row = Model.ROWS - 1; row >= 0; row--) {
            assertEquals(originFieldData[row][COL], newFieldData[row][COL]);
        }
        assertTrue(GameConfiguration.getScore() != 0);
    }

    private void insertVerticalTripletAndTwin() {
        originFieldData[Model.ROWS - 1][COL] = 2;

        for (int row = Model.ROWS - 2; row >= Model.ROWS - 1 - COL_SIZE; row--) {
            if (row >= Model.ROWS - 4) {
                originFieldData[row][COL] = 1;
            } else {
                originFieldData[row][COL] = 2;
            }
        }
    }

}