package com.columns.logic;

class FieldManipulator {
    private State state;
    private int[][] fieldData;
    private int[][] copyFieldData;

    private static int ROWS;
    private static int COLUMNS;
    private ColumnChecker checker = new ColumnChecker();
    private ColumnPaster paster = new ColumnPaster();

    public void setState(State state) {
        this.state = state;
        init();
    }

    private void init() {
        fieldData = state.getField().getData();

        ROWS = fieldData.length;
        COLUMNS = fieldData[0].length;
    }

    void manipulate() {
        paster.setState(state);
        fieldData = paster.pasteFigureInField();
        do {
            checker.setFieldData(fieldData);
            checker.setChange(true);
            copyFieldData = checker.check();
            if (!checker.hasNoChanges()) {
                int highestCell = packField();
                int multiply = ROWS * 2 - state.getRow() - highestCell;
                GameConfiguration.multiScoreUp(multiply);
            }
        } while (!checker.hasNoChanges());
    }

    private int packField() {
        int highestCell = 0;
        for (int col = 0; col < COLUMNS; col++) {
            highestCell = ROWS - 1;
            for (int row = ROWS - 1; row >= 0; row--) {
                if (copyFieldData[row][col] > 0) {
                    fieldData[highestCell][col] = copyFieldData[row][col];
                    highestCell--;
                }
            }
            for (int row = highestCell; row >= 0; row--) {
                fieldData[row][col] = 0;
            }
        }
        return highestCell;
    }
}
