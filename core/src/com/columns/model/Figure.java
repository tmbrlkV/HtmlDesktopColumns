package com.columns.model;

import java.util.Random;

public class Figure {
    private static final int LENGTH = 3;
    public static final int COLOR_COUNT = 7;
    private int[] data = new int[LENGTH];
    private final static Random random = new Random();

    public Figure() {
        fillFigure();
    }

    private void fillFigure() {
        for (int i = 0; i < LENGTH; ++i) {
            data[i] = getRandomCellColor();
        }
    }

    private int getRandomCellColor() {
        return Math.abs(random.nextInt()) % COLOR_COUNT + 1;
    }

    public int[] getData() {
        return data;
    }
}
