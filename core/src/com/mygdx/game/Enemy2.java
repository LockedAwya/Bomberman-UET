package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import org.w3c.dom.Text;

import java.awt.*;

import static com.mygdx.game.Bomberman.map;
import static com.mygdx.game.SpritesManager.*;
import static com.mygdx.game.SpritesManager.theEnemy;

public class Enemy2 extends Entity{

    public Enemy2(Rectangle rectangle, Texture texture) {
        super(rectangle, texture);
        theEnemy2.x = 100;
        theEnemy2.y = 400;
    }

    public Enemy2() {
        super();
    }

    public void update() {
        choice2 = randomInRange(0, 4);
        //choice = randomInRange(0, 4);
        if (choice2 == 0) {
            theEnemy2.x -= enemySpeed * Gdx.graphics.getDeltaTime();
            System.out.println("x :" + theEnemy2.x);
        } else if (choice2 == 1) {
            theEnemy2.x += enemySpeed * Gdx.graphics.getDeltaTime();
            System.out.println("x :" + theEnemy2.x);
        } else if (choice2 == 2) {
            theEnemy2.y += enemySpeed * Gdx.graphics.getDeltaTime();
            System.out.println("y :" + theEnemy2.x);
        } else if (choice2 == 3) {
            theEnemy2.y -= enemySpeed * Gdx.graphics.getDeltaTime();
            System.out.println("y :" + theEnemy2.x);
        }
        //choice2 = 1;
        //choice2 = randomInRange(0, 4);
        if ((int)theEnemy2.x > 13 * 16 || (int)theEnemy2.y >= 31 * 16) {
            //choice = 0;
            //theEnemy.x -= enemySpeed * Gdx.graphics.getDeltaTime();
            //go back to init pos
            theEnemy2.x = 100;
            theEnemy2.y = 400;
        }
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 31; j++) {
                Rectangle pos = null;
                if (map[i][j] == '#' || map[i][j] == '*') {
                    pos = new Rectangle(i * 16, j * 16, 16, 16);
                    if (choice2 == 0 && theEnemy2.overlaps(pos)) {
                        theEnemy2.x += 10;
                        System.out.println("Move left and hit the object");
                    } else if (choice2 == 1 && theEnemy2.overlaps(pos)) {
                        theEnemy2.x -= 10;
                        System.out.println("Move right and hit the object");
                    } else if (choice2 == 2 && theEnemy2.overlaps(pos)) {
                        theEnemy2.y -= 10;
                        System.out.println("Move up and hit the object");
                    } else if (choice2 == 3 && theEnemy2.overlaps(pos)) {
                        theEnemy2.y += 10;
                        System.out.println("Move down and hit the object");
                    }
                } else if (bomb_array[i][j] == 'v' || bomb_array[i][j] == 'h' || bomb_array[i][j] == 'm') {
                    pos = new Rectangle(i * 16, j * 16, 16, 16);
                    if (theEnemy2.overlaps(pos)) {
                        enemy2Died = true;
                        EnemydiedSound.play(0.25f);
                    }
                }
            }
        }
        if (!enemy2Died) {
            batch.draw(enemyPic, theEnemy2.x, theEnemy2.y, theEnemy2.width, theEnemy2.height);
        }
        if (theEnemy2.overlaps(SpritesManager.character)) {
            SpritesManager.character.x = 17;
            SpritesManager.character.y = 17;
        }
    }
    @Override
    public void dispose(Texture texture) {

    }
}
