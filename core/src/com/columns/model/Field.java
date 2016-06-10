package com.columns.model;


public class Field {
    private int[][] data;

    public Field(int rows, int columns) {
        this.data = new int[rows][columns];
    }

    public int getRows() {
        return data.length;
    }

    public int getColumns() {
        return data[0].length;
    }

    public int[][] getData() {
        return data;
    }
}
