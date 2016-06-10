package com.columns.logic;

import com.columns.model.Field;
import com.columns.model.Model;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ColumnCheckerTest {

    private static final int COL_SIZE = 3;
    private ColumnChecker checker;
    private int[][] oldFieldData;
    private int[][] original;

    @Before
    public void init() {
        State state = new State();
        checker = new ColumnChecker();
        state.setField(new Field(Model.ROWS, Model.COLUMNS));
        oldFieldData = state.getField().getData();
        original = getOriginFieldDataCopy();
    }

    private int[][] getOriginFieldDataCopy() {
        int[][] original = new int[oldFieldData.length][oldFieldData[0].length];
        for (int i = 0; i < oldFieldData.length; ++i) {
            System.arraycopy(oldFieldData[i], 0, original[i], 0, original[i].length);
        }
        return original;
    }

    private void check(int[][] fieldData) {
        checker.setFieldData(fieldData);
        int[][] newFieldData = checker.check();
        assertArrayEquals(original, newFieldData);
    }

    @Test
    public void checkHorizontal() throws Exception {
        check(insertHorizontalTriplet());
    }

    private int[][] insertHorizontalTriplet() {
        for (int col = 0; col < COL_SIZE; col++) {
            oldFieldData[Model.ROWS - 1][col] = 1;
        }
        return oldFieldData;
    }

    @Test
    public void checkVertical() throws Exception {
        check(insertVerticalTriplet());

    }

    private int[][] insertVerticalTriplet() {
        for (int row = Model.ROWS - 1; row > Model.ROWS - COL_SIZE - 1; row--) {
            oldFieldData[row][Model.COLUMNS - 1] = 1;
        }

        return oldFieldData;
    }

    @Test
    public void checkDiagonal() throws Exception {
        check(insertDiagonalTriplet());
    }

    private int[][] insertDiagonalTriplet() {
        for (int i = 0; i < COL_SIZE; i++) {
            oldFieldData[COL_SIZE - i - 1][i] = 1;
        }

        return oldFieldData;
    }

    @Test
    public void checkOppositeDiagonal() throws Exception {
        check(insertOppositeDiagonalTriplet());
    }

    private int[][] insertOppositeDiagonalTriplet() {
        for (int i = 0; i < COL_SIZE; i++) {
            oldFieldData[COL_SIZE - i - 1][Model.COLUMNS - i - 1] = 1;
        }

        return oldFieldData;
    }

    @Test
    public void checkDoubleTriplet() throws Exception {
        check(insertDoubleTriplet());
    }

    private int[][] insertDoubleTriplet() {
        for (int i = 0; i < COL_SIZE; ++i) {
            oldFieldData[Model.ROWS - COL_SIZE - 1 - i][0] = 1;
            oldFieldData[Model.ROWS - 1][i] = 1;
        }
        return oldFieldData;
    }

}