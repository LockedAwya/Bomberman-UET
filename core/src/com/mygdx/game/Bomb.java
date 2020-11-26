package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Bomb extends Entity {
    public Bomb() {
        super();
    }
    public Bomb(Vector2 vector2, Texture texture, SpriteBatch batch) {
        super(vector2, texture, batch);
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public Bomb(Texture texture, int x, int y) {
        super(texture, x, y);
    }

    @Override
    public void dispose(Texture texture) {

    }
}
