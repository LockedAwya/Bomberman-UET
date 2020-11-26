package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Grass extends Entity {
//    public Grass(Vector2 vector2, Texture texture, SpriteBatch batch) {
//        super(vector2, texture, batch);
//    }

    public Grass() {
        super();
    }

    public Grass(Vector2 vector2, Texture texture) {
        super(vector2, texture);
    }

    public Grass(Texture texture, int x, int y) {
        super(texture, x, y);
    }

//    public Grass(Entity entity, Texture texture, int x, int y, int width, int height) {
//        super(entity, texture, x, y, width, height);
//    }

    @Override
    public void dispose(Texture texture) {

    }


}
