package com.mygdx.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.esotericsoftware.kryonet.Connection;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import static com.mygdx.game.SpritesManager.*;
import static com.mygdx.game.SpritesManager.character;

//import static com.mygdx.game.Bomberman.bomb_array;

public class Player extends Entity implements InputProcessor {
    public Bomb bomb = new Bomb();
    public float theSpeed = SpritesManager.speed;
    public char[][] _map = Bomberman.map;
    public static Rectangle _character = SpritesManager.character;
    private boolean flamesUpgraded = false;
    public int n = 1;
    private boolean speedy = false;
    public Connection connection;
    public int playerId;
    public float x, y;
//    TimerTask _task1;
//    TimerTask _task2;

    public Player() {
        super();
    }

    public Player(Rectangle character) {
        super(character);
    }
    public Player(Rectangle character, Texture texture) {
        super(character, texture);
        character.x = 17;
        character.y = 17;
    }

    public void playerUpdate() {
        _task1 = new TimerTask() {
            @Override
            public void run() {
                theSpeed = 0;
            }
        };
        _task2 = new TimerTask() {
            @Override
            public void run() {
                theSpeed = 45;
            }
        };
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 31; j++) {
                Rectangle pos = null;
                if (_map[i][j] == '#' || _map[i][j] == '*') { //wall
                    pos = new Rectangle(i * 16, j * 16, 16, 16);
                    if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && _character.overlaps(pos)) {
                        System.out.println("HIT THE WALL!!");
                        try {
//                            timer.schedule(_task1, 500);
//                            timer.schedule(_task2, 599);
                            timer.schedule(_task1, 500);
                            timer.schedule(_task2, 599);
                        } catch (IllegalStateException e) {
                            e.printStackTrace();
                        } finally {
                            _character.x += 5;
                            System.out.println("Player posX: " + _character.x);
                        }
                    }
                    if ( Gdx.input.isKeyPressed(Input.Keys.RIGHT) && _character.overlaps(pos)) {
                        System.out.println("HIT THE WALL!!");
                        try {
                            timer.schedule(_task1, 500);
                            timer.schedule(_task2, 599);
                        } catch (IllegalStateException e) {
                            e.printStackTrace();
                        } finally {
                            _character.x -= 5;
                            System.out.println("Player posX: " + _character.x);
                        }
                    }
                    if ( Gdx.input.isKeyPressed(Input.Keys.UP) && _character.overlaps(pos)) {
                        System.out.println("HIT THE WALL!!");
                        try {
                            timer.schedule(_task1, 500);
                            timer.schedule(_task2, 599);
                        } catch (IllegalStateException e) {
                            e.printStackTrace();
                        } finally {
                            _character.y -= 5;
                            System.out.println("Player posY: " + _character.y);
                        }
                    }
                    if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && _character.overlaps(pos)) {
                        //_character.y += 0.005;
                        System.out.println("HIT THE WALL!!");
                        try {
                            timer.schedule(_task1, 500);
                            timer.schedule(_task2, 599);
                        } catch (IllegalStateException e) {
                            e.printStackTrace();
                        } finally {
                            _character.y += 5;
                            System.out.println("Player posY: " + _character.y);
                        }
                    }
                }
                if ((bomb_array[i][j] == 'h' || bomb_array[i][j] == 'v' || bomb_array[i][j] == 'm')) {
                    pos = new Rectangle(i * 16, j * 16, 16, 16);
                    if (_character.overlaps(pos)) {
                        state = GAME_OVER;
                        //youdead = true;
                        //System.out.println("DIEEEEEEE");
//                        _character.x = 17;
//                        _character.y = 17;
                        youDeadSound.play(0.25f);
                        break;
                    }
                }
                if ((_map[i][j] == 's')) { //hit item speed
                    pos = new Rectangle(i * 16, j * 16, 16, 16);
                    if (_character.overlaps(pos)) {
                        _map[i][j] = ' ';
                        System.out.println("HIT SPEED ITEM");
                        speedy = true;
                        insertItemSound.play(0.25f);
                    }
                }
                if (_map[i][j] == 'f') {
                    pos = new Rectangle(i * 16, j * 16, 16, 16);
                    if (_character.overlaps(pos)) {
                        _map[i][j] = '\0';
                        _map[i][j] = ' ';
                        System.out.println("HIT FLAME ITEM");
                        flamesUpgraded = true;
                        //speedy = true;
                    }
                }
                //destroy the brick
                if ((bomb_array[i][j] == 'h' || bomb_array[i][j] == 'v') && _map[i][j] == '*') {
                        if (i == 11 && j == 12) {
                            _map[i][j] = '\0';
                            _map[i][j] = 'p';
                        } else {
                            _map[i][j] = '\0';
                            _map[i][j] = ' ';
                        }
                }
                if (_map[i][j] == 'p') {
                    pos = new Rectangle(i * 16, j * 16, 16, 16);
                    if (_character.overlaps(pos) && enemyDied && enemy2Died) {
                        state = YOU_WIN;
                        System.out.println("You win!!!");
                    }
                }
            }
        }
    }

    public void speedUpgraded() {
        //boolean speedy = false;
        playerUpdate();
        if (speedy) {
            //playerUpdate();
            try {
                move(150);
                playerUpdate();
            } catch (IllegalStateException ise) {
                ise.printStackTrace();
            }
        } else {
            move(theSpeed);
            playerUpdate();
        }
//        timer.schedule(_task1, 30000);
//        timer.schedule(_task2, 40000);
    }

    public void move(float theSpeed) {
        this.theSpeed = theSpeed;
        boolean shouldMove = true;
        if (state == GAME_READY) {
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT) &&
                    !Gdx.input.isKeyPressed(Input.Keys.RIGHT) &&
                    !Gdx.input.isKeyPressed(Input.Keys.UP) &&
                    !Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                _character.x -= theSpeed * Gdx.graphics.getDeltaTime();
                System.out.println("Player posX: " + _character.x);
            }
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) &&
                    !Gdx.input.isKeyPressed(Input.Keys.LEFT) &&
                    !Gdx.input.isKeyPressed(Input.Keys.UP) &&
                    !Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                _character.x += theSpeed * Gdx.graphics.getDeltaTime();
                System.out.println("Player posX: " + _character.x);
            }
            if (Gdx.input.isKeyPressed(Input.Keys.UP) &&
                    !Gdx.input.isKeyPressed(Input.Keys.RIGHT) &&
                    !Gdx.input.isKeyPressed(Input.Keys.LEFT) &&
                    !Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                _character.y += theSpeed * Gdx.graphics.getDeltaTime();
                System.out.println("Player posY: " + _character.y);
            }
            if (Gdx.input.isKeyPressed(Input.Keys.DOWN) &&
                    !Gdx.input.isKeyPressed(Input.Keys.RIGHT) &&
                    !Gdx.input.isKeyPressed(Input.Keys.UP) &&
                    !Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                _character.y -= theSpeed * Gdx.graphics.getDeltaTime();
                System.out.println("Player posY: " + _character.y);
            }
        } else {
            return;
        }
    }

    public boolean collisionCheck(int x, int y) {
        if (character.x > x && character.x < x + 16 && character.y > y && character.y < y + 16) {
            return true;
        }
        return false;
    }

    public void dropBomb(final int x, final int y) {
        this.n = n;
        //System.out.println("n: " + n);
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            bomb_array[x][y] = '.';
            placeBonbSound.play(0.25f);
            //placeBonbSound.dispose();
            TimerTask task1 = new TimerTask() {
                @Override
                public void run() {
                    bomb_array[x][y] = 'm';
                    //bomb_array[(x/16)-2][(y/16)] = 'v';
                    bomb_array[(x) - 1][(y)] = 'v';
                    bomb_array[(x) + 1][(y)] = 'v'; //flame-y
                    //bomb_array[(x/16)+2][(y/16)] = 'v';
                    bomb_array[(x)][(y) - 1] = 'h'; //flame-x
                    bomb_array[(x)][(y) + 1] = 'h';
                    bombSound.play(0.25f);
                    //bomb_array[(x/16)][(y/16)-2] = 'h'; //flame-x
                    //bomb_array[(x/16)][(y/16)+2] = 'h';
                }
            };
            TimerTask task2 = new TimerTask() {
                @Override
                public void run() {
                    //bomb_array[(x/16)-2][(y/16)] = '\0';
                    bomb_array[x][y] = '\0';
                    bomb_array[(x) - 1][(y)] = '\0';
                    bomb_array[(x) + 1][(y)] = '\0'; //flame-y
                    //bomb_array[(x/16)+2][(y/16)] = '\0';
                    bomb_array[(x)][(y) - 1] = '\0'; //flame-x
                    bomb_array[(x)][(y) + 1] = '\0';
                    //bomb_array[(x/16)][(y/16)-2] = '\0'; //flame-x
                    //bomb_array[(x/16)][(y/16)+2] = '\0';
                }
            };
            TimerTask task3 = new TimerTask() {
                @Override
                public void run() {
                    bomb_array[x ][y ] = 'm';
                    bomb_array[(x ) - 2][(y )] = 'v';
                    bomb_array[(x ) - 1][(y )] = 'v';
                    bomb_array[(x ) + 1][(y )] = 'v'; //flame-y
                    bomb_array[(x ) + 2][(y )] = 'v';
                    bomb_array[(x )][(y ) - 1] = 'h'; //flame-x
                    bomb_array[(x )][(y ) + 1] = 'h';
                    bomb_array[(x )][(y ) - 2] = 'h'; //flame-x
                    bomb_array[(x )][(y ) + 2] = 'h';
                }
            };
            TimerTask task4 = new TimerTask() {
                @Override
                public void run() {
                    bomb_array[x ][y ] = '\0';
                    bomb_array[(x ) - 2][(y )] = '\0';
                    bomb_array[(x ) - 1][(y )] = '\0';
                    bomb_array[(x ) + 1][(y )] = '\0'; //flame-y
                    bomb_array[(x ) + 2][(y )] = '\0';
                    bomb_array[(x )][(y ) - 1] = '\0'; //flame-x
                    bomb_array[(x )][(y ) + 1] = '\0';
                    bomb_array[(x )][(y ) - 2] = '\0'; //flame-x
                    bomb_array[(x )][(y ) + 2] = '\0';
                }
            };
            timer.schedule(task1, 800);
            timer.schedule(task2, 1000);
        }
    }

    @Override
    public void dispose(Texture texture) {

    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

//    @Override
//    public void keyTyped(KeyEvent e) {
//
//    }

//    @Override
//    public void keyPressed(KeyEvent e) {
//        if (e.getKeyCode() == )
//    }

//    @Override
//    public void keyReleased(KeyEvent e) {
//
//    }
}
