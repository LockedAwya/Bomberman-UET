package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Wall extends Entity {
//    public Wall(Vector2 vector2, Texture texture, SpriteBatch batch, Rectangle bounds) {
//        super(vector2, texture, batch, bounds);
//    }

    public Wall() {
        super();
    }

    public Wall(Vector2 vector2, Texture texture) {
        super(vector2, texture);
    }

    public Wall(Texture texture, int x, int y) {
        super(texture, x, y);
    }

    public Rectangle getBounds() {
        return bounds;
    }

    @Override
    public void dispose(Texture texture) {
        this.texture = texture;
        texture.dispose();
    }
}
