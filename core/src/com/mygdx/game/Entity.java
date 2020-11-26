package com.mygdx.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import org.w3c.dom.Text;

public abstract class Entity {
    private static SpriteBatch batch = new SpriteBatch();
    protected int x;
    protected int y;
    protected Texture texture;
    //protected SpriteBatch batch;
    protected int width;
    protected int height;
    protected Rectangle character;
    protected Rectangle bounds;
    protected Rectangle rectangle;
    protected OrthographicCamera camera;
    protected Sprite sprite;
    protected Vector2 vector2;
    protected Entity entity;
    //protected ArrayList<Bomb> al = new ArrayList<>();
//    public int width = 688;
//    public int height = 688;
    public Vector2 position = new Vector2(0, 0);

    public Entity() {

    }

    public Entity(Rectangle rectangle) {
        this.rectangle = rectangle;
    }
    public Entity(Rectangle rectangle, Texture texture) {
        this.rectangle = rectangle;
        this.texture = texture;
    }

    public Entity(Vector2 vector2) {
        this.vector2 = vector2;
    }

    public Entity(Vector2 vector2, Texture texture) {
        this.vector2 = vector2;
        this.texture = texture;
    }

    public Entity(Vector2 vector2, Texture texture, SpriteBatch batch, Rectangle bounds) {
        //this.bounds = bounds;
        this.vector2 = vector2;
        this.texture = texture;
        this.batch = batch;
        batch.draw(texture, vector2.x, vector2.y);
        this.bounds = new Rectangle((int)vector2.x, (int)vector2.y, 16, 16);
    }

    public Rectangle getBounds() {
        //return bounds.setPosition(vector2.x, vector2.y);
        return bounds;
    }

    public Entity (Texture texture, int x, int y) {
        this.texture = texture;
        this.x = x * 16;
        this.y = y * 16;
//        batch.begin();
//        batch.draw(texture, x, y, width, height);
//        batch.end();
    }

    public Vector2 getPosition() {
        return position;
    }

    //init for player

    public Entity(Vector2 vector2, Texture texture, SpriteBatch batch) {
        this.vector2 = vector2;
        this.texture = texture;
        this.batch = batch;
        batch.draw(texture, vector2.x, vector2.y);
    }

    public Entity(Texture texture, SpriteBatch batch) {
        this.batch = batch;
        this.texture = texture;
        batch.draw(texture, position.x, position.y);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void render(Texture texture, SpriteBatch batch, int x, int y) {

    }
    public void render(Batch batch) {
        batch.draw(texture, x, y);
    }
    public void update() {

    }

    public abstract void dispose(Texture texture);
}
