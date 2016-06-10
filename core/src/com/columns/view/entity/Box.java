package com.columns.view.entity;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Box extends Actor {
    public static final int SIZE = 30;
    private static Texture[] textures = new Texture[9];
    private static Color colors[] = {
            Color.BLACK, Color.CYAN, Color.BLUE,
            Color.RED, Color.GREEN, Color.YELLOW,
            Color.PINK, Color.MAGENTA, Color.BLACK
    };

    static {
        for (int i = 0; i < colors.length; ++i) {
            textures[i] = createTexture(colors[i]);
        }
    }

    private static Texture createTexture(Color color) {
        Pixmap image = new Pixmap(SIZE, SIZE, Pixmap.Format.RGBA8888);

        image.setColor(color);
        image.fill();

        return new Texture(image);
    }

    private Texture texture;

    public Box(int colorIndex) {
        texture = textures[colorIndex];
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, this.getX(), getY(), this.getOriginX(),
                this.getOriginY(), texture.getWidth(), texture.getHeight(),
                this.getScaleX(), this.getScaleY(), this.getRotation(), 0, 0,
                texture.getWidth(), texture.getHeight(), false, false);
    }


    public void setColor(int colorIndex) {
        texture = textures[colorIndex];
    }
}
