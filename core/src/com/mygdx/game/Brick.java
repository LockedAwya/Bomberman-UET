package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Brick extends Entity {
    private Rectangle bounds;
//    public Brick(Vector2 vector2, Texture texture, SpriteBatch batch, Rectangle bounds) {
//        super(vector2, texture, batch, bounds);
//    }

    public Brick() {
        super();
    }

    public Brick(Texture texture, int x, int y) {
        super(texture, x, y);
    }

    public Brick(Vector2 vector2, Texture texture) {
        super(vector2, texture);
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public Vector2 getPosition() {
        return position;
    }


    @Override
    public void dispose(Texture texture) {

    }
}
