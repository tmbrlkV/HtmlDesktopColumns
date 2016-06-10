package com.columns.logic;

class ColumnChecker {
    private int[][] fieldData;
    private static int ROWS;
    private static int COLUMNS;
    private int[][] oldFieldData;
    private boolean noChange = true;

    void setFieldData(int[][] fieldData) {
        this.fieldData = fieldData;
        init(fieldData);
    }

    private void init(int[][] fieldData) {
        ROWS = fieldData.length;
        COLUMNS = fieldData[0].length;
        oldFieldData = new int[ROWS][COLUMNS];
        for (int row = 0; row < ROWS; row++) {
            System.arraycopy(fieldData[row], 0, oldFieldData[row], 0, COLUMNS);
        }
    }

    int[][] check() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                if (isEmptyCell(fieldData[row][col])) {
                    continue;
                }
                if (isCorner(row, col)) {
                    continue;
                }
                if (isVerticalBorder(col)) {
                    checkNeighbours(row - 1, col, row + 1, col);
                    continue;
                }
                if (isHorizontalBorder(row)) {
                    checkNeighbours(row, col - 1, row, col + 1);
                    continue;
                }

                checkNeighbours(row - 1, col, row + 1, col);
                checkNeighbours(row, col - 1, row, col + 1);
                checkNeighbours(row - 1, col - 1, row + 1, col + 1);
                checkNeighbours(row - 1, col + 1, row + 1, col - 1);
            }
        }
        return oldFieldData;
    }

    private boolean isCorner(int row, int col) {
        return isHorizontalBorder(row) && isVerticalBorder(col);
    }

    private boolean isVerticalBorder(int col) {
        return col == 0 || col == COLUMNS - 1;
    }

    private boolean isHorizontalBorder(int row) {
        return row == 0 || row == ROWS - 1;
    }

    private boolean isEmptyCell(int i) {
        return i == 0;
    }

    private void checkNeighbours(int leftTopRow, int leftCol, int rightBottomRow, int rightCol) {
        int midRow = (rightBottomRow + leftTopRow) / 2;
        int midCol = (rightCol + leftCol) / 2;
        if ((fieldData[leftTopRow][leftCol] == fieldData[midRow][midCol]) && (fieldData[midRow][midCol] == fieldData[rightBottomRow][rightCol])) {
            oldFieldData[leftTopRow][leftCol] = 0;
            oldFieldData[midRow][midCol] = 0;
            oldFieldData[rightBottomRow][rightCol] = 0;

            noChange = false;
            GameConfiguration.scoreUp();
            GameConfiguration.tryToLevelUp();
        }
    }

    boolean hasNoChanges() {
        return noChange;
    }

    void setChange(boolean change) {
        this.noChange = change;
    }
}
