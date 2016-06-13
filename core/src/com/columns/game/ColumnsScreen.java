package com.columns.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.columns.logic.GameConfiguration;

class ColumnsScreen implements Screen {
    private ColumnsStage stage = new ColumnsStage();
    private BitmapFont font = new BitmapFont();
    private Batch batch = new SpriteBatch();
    private StringBuffer info = new StringBuffer();

    @Override
    public void show() {
        stage.init();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);

        batch.begin();
        drawStatistics();
        drawHelp();
        batch.end();

        stage.draw();
    }

    private void drawStatistics() {
        info.append("Level: ").append(GameConfiguration.getLevel()).append(" ")
                .append("Score: ").append(GameConfiguration.getScore());
        font.draw(batch, info, 10, 30);
        info.setLength(0);
    }

    private void drawHelp() {
//        info.append("Move: Left/Right\n")
//                .append("Roll: Up/Down \n")
//                .append("Drop: Space \n")
//                .append("Pause: P \n")
//                .append("Level: +/- \n");
//        font.draw(batch, info, 220, 470);
//        info.setLength(0);
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}
