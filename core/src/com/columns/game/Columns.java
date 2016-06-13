package com.columns.game;

import com.badlogic.gdx.Game;

public class Columns extends Game {

    @Override
    public void create() {
        setScreen(new ColumnsScreen());
    }
}
