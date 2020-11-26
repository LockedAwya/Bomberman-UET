package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

import java.util.TimerTask;

import static com.mygdx.game.Bomberman.map;
import static com.mygdx.game.SpritesManager.*;

public class Enemy extends Entity {
    //private int enemySpeed;
    //private Array<Enemy> _theEnemy;
    private Enemy enemy = null;

    public Enemy(Rectangle rectangle, Texture texture) {
        super(rectangle, texture);
        theEnemy.x = 84;
        theEnemy.y = 468;
    }

    public Enemy() {
        super();
    }

    public void move(int enemySpeed) {

    }

    public void update() {
//        if (choice == 0) {
//            theEnemy.x -= enemySpeed * Gdx.graphics.getDeltaTime();
//            System.out.println("x :" + theEnemy.x);
//        } else if (choice == 1) {
//            theEnemy.x += enemySpeed * Gdx.graphics.getDeltaTime();
//            System.out.println("x :" + theEnemy.x);
//        } else if (choice == 2) {
//            theEnemy.y += enemySpeed * Gdx.graphics.getDeltaTime();
//            System.out.println("y :" + theEnemy.x);
//        } else if (choice == 3) {
//            theEnemy.y -= enemySpeed * Gdx.graphics.getDeltaTime();
//            System.out.println("y :" + theEnemy.x);
//        }
        //choice = randomInRange(0, 4);
        if (choice == 1) {
            theEnemy.x += enemySpeed * Gdx.graphics.getDeltaTime();
            System.out.println("x :" + theEnemy.x);
        }
        choice = 1;
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 31; j++) {
                Rectangle pos = null;
                if (map[i][j] == '#' || map[i][j] == '*') {
                    pos = new Rectangle(i * 16, j * 16, 16, 16);
                    if (choice == 0 && theEnemy.overlaps(pos)) {
                        theEnemy.x += 10;
                        System.out.println("Move left and hit the object");
                    } else if (choice == 1 && theEnemy.overlaps(pos)) {
                        theEnemy.x -= 100;
                        System.out.println("Move right and hit the object");
                    } else if (choice == 2 && theEnemy.overlaps(pos)) {
                        theEnemy.y -= 10;
                        System.out.println("Move up and hit the object");
                    } else if (choice == 3 && theEnemy.overlaps(pos)) {
                        theEnemy.y += 10;
                        System.out.println("Move down and hit the object");
                    }
                } else if (bomb_array[i][j] == 'v' || bomb_array[i][j] == 'h' || bomb_array[i][j] == 'm') {
                    pos = new Rectangle(i * 16, j * 16, 16, 16);
                    if (theEnemy.overlaps(pos)) {
                        enemyDied = true;
                        EnemydiedSound.play(0.25f);
                    }
                }
            }
        }
        if (!enemyDied) {
            batch.draw(enemyPic, theEnemy.x, theEnemy.y, theEnemy.width, theEnemy.height);
        }
        if (theEnemy.overlaps(SpritesManager.character)) {
            SpritesManager.character.x = 17;
            SpritesManager.character.y = 17;
        }
    }

    @Override
    public void dispose(Texture texture) {

    }
}
