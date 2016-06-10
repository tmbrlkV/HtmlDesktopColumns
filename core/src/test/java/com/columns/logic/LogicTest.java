package com.columns.logic;

import com.columns.model.*;
import org.junit.*;

import java.util.Arrays;

import static org.junit.Assert.*;

public class LogicTest {
    private State state;
    private Logic logic;

    @Before
    public void init() {
        state = new State();
        state.setField(new Field(Model.ROWS, Model.COLUMNS));
        state.setFigure(new Figure());
        logic = new Logic(state);
    }

    @Test
    public void testMoveLeft() throws Exception {
        state.setCol(state.getField().getColumns());
        int col = state.getCol();
        boolean left = logic.moveLeft();
        assertTrue(left);
        assertEquals(col - 1, logic.getState().getCol());
    }

    @Test
    public void testMoveLeftTooMuch() throws Exception {
        state.setCol(0);
        assertFalse(logic.moveLeft());
        assertEquals(0, logic.getState().getCol());
    }

    @Test
    public void testMoveRight() throws Exception {
        state.setCol(0);
        int col = state.getCol();
        boolean right = logic.moveRight();
        assertTrue(right);
        assertEquals(col + 1, logic.getState().getCol());
    }

    @Test
    public void testMoveRightTooMush() throws Exception {
        int width = state.getField().getColumns();
        state.setCol(width);
        assertFalse(logic.moveRight());
        assertEquals(width, logic.getState().getCol());
    }

    @Test
    public void testMoveDown() throws Exception {
        int row = state.getRow();
        boolean down = logic.moveDown();
        assertTrue(down);
        assertEquals(row + 1, logic.getState().getRow());
    }

    @Test
    public void testMoveDownTooMuch() throws Exception {
        state.setRow(state.getField().getRows() - 3);
        boolean down = logic.moveDown();
        assertTrue(down);
        assertEquals(0, logic.getState().getRow());
    }

    @Test
    public void rotateUp() throws Exception {
        int[] data = logic.getState().getFigure().getData();
        int[] checkData = data.clone();
        int size = data.length - 1;

        logic.rotateUp();

        assertEquals(data[size], checkData[0]);
    }

    @Test
    public void rotateDown() throws Exception {
        int[] data = logic.getState().getFigure().getData();
        int[] checkData = data.clone();
        int size = data.length - 1;

        logic.rotateDown();

        assertEquals(data[0], checkData[size]);
    }
}