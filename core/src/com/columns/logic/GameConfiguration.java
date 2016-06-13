package com.columns.logic;

import com.columns.controller.MovementChanger;

public class GameConfiguration {
    private static final int MAX_LEVEL = 7;
    private static final int FIGURES_TO_LEVEL_UP = 30;
    private static final float SPEED_DELTA = 0.125f;
    private static int score;
    private static int figures;
    private static int level;
    private static float speed = 1.0f;

    static void scoreUp() {
        score += (level + 1) * 10;
    }

    static void multiScoreUp(int multiply) {
        score += (((level + 1) * multiply * 2) % 5) * 5;
    }

    static void tryToLevelUp() {
        figures++;
        if (figures > FIGURES_TO_LEVEL_UP) {
            figures = 0;
            levelUp();
        }
    }

    static void levelUp() {
        if (level < MAX_LEVEL) {
            level++;
            speed -= SPEED_DELTA;
            MovementChanger.setSpeed(speed);
        }
    }

    static void levelDown() {
        if (level > 0) {
            level--;
            speed += SPEED_DELTA;
            MovementChanger.setSpeed(speed);
        }
    }

    public static int getLevel() {
        return level;
    }

    public static int getScore() {
        return score;
    }
}
