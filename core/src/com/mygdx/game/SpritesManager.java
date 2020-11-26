package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class SpritesManager {
    public static Texture grassImg = new Texture(Gdx.files.internal("grass.png"));
    public static Texture pauseImg = new Texture(Gdx.files.internal("paused.png"));
    public static Texture brickImg = new Texture(Gdx.files.internal("brick.png"));
    public static Texture wallImg = new Texture(Gdx.files.internal("wall.png"));
    public static Texture playerImg = new Texture(Gdx.files.internal("images.png"));
    public static Texture bombImg = new Texture(Gdx.files.internal("bomb.png"));
    public static Texture flameX = new Texture(Gdx.files.internal("flameX.png"));
    public static Texture flameY = new Texture(Gdx.files.internal("flameY.png"));
    public static Texture gameOverImg = new Texture(Gdx.files.internal("GameOver.png"));
    public static Texture texture = new Texture(Gdx.files.internal("spritesheet.png"));
    public static Texture speedItem = new Texture(Gdx.files.internal("powerup_speed.png"));
    public static Texture flameItem = new Texture(Gdx.files.internal("powerup_flames.png"));
    public static Texture flameMiddleItem = new Texture(Gdx.files.internal("flameMiddle.png"));
    public static Texture portalItem = new Texture(Gdx.files.internal("portal.png"));
    public static Texture enemyPic = new Texture(Gdx.files.internal("balloom_left1.png"));
    public static Texture anotherCharacterPic = new Texture(Gdx.files.internal("anotherimage.png"));
    public static Texture youwinImg = new Texture(Gdx.files.internal("youwin.png"));

    public static SpriteBatch batch = new SpriteBatch();
    public static Vector2 vector2 = new Vector2();
    public static boolean KEYLEFT = Gdx.input.isKeyPressed(Input.Keys.LEFT);
    public static boolean KEYRIGHT = Gdx.input.isKeyPressed(Input.Keys.RIGHT);
    public static boolean KEYUP = Gdx.input.isKeyPressed(Input.Keys.UP);
    public static boolean KEYDOWN = Gdx.input.isKeyPressed(Input.Keys.DOWN);
    public static Timer timer = new Timer();
    public static Entity entity = null;
    public static final Rectangle character = new Rectangle(17, 17, 7, 7);
    public static Rectangle theEnemy = new Rectangle(80, 464, 7, 7);
    public static Rectangle theEnemy2 = new Rectangle(17, 200, 7,7);
    static final int GAME_READY = 0;
    static final int GAME_MENU = 1;
    static final int GAME_PAUSED = 2;
    static final int YOU_WIN = 3;
    static final int GAME_OVER = 4;

    static int upgradedSpeed = 145;

    public static Sound bombSound = Gdx.audio.newSound(Gdx.files.internal("boomed.wav"));
    public static Sound placeBonbSound = Gdx.audio.newSound(Gdx.files.internal("placebomb.wav"));
    public static Sound youDeadSound = Gdx.audio.newSound(Gdx.files.internal("youdead.mp3"));
    public static Music backgroundSound = Gdx.audio.newMusic(Gdx.files.internal("backgroundsound.mp3"));
    public static Sound insertItemSound = Gdx.audio.newSound(Gdx.files.internal("insertItem.mp3"));
    public static Sound EnemydiedSound = Gdx.audio.newSound(Gdx.files.internal("enemydied.mp3"));
    private static SecureRandom rand = new SecureRandom();

    public static Array<Texture> _theEnemy = new Array();

    public static int randomInRange(int min, int max) {
        return min + (int)(Math.random() * ((max - min) + 1));
    }

    public static int choice = -1;
    public static int choice2 = -1;

    public static TimerTask _task1;
    public static TimerTask _task2;
    public static boolean enemyDied = false;
    public static boolean enemy2Died = false;


    public static int state;

    public HashMap<String, Entity> sprites;

    public static char[][] bomb_array = new char[32][32];

    public static float speed = (float) 45;
    public static float enemySpeed = (float) 80;

    public Grass grass;
    public Wall wall;
    public Brick brick;
    public Player player = new Player();
    public Bomb bomb;
    public static boolean youdead = false;
    public static boolean inMenu = true;
    //public Entity entity;

//    public char[][] _map = {
//            {'#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#'},
//            {'#',' ','p',' ',' ',' ',' ','*','*',' ','*',' ',' ',' ',' ','*',' ',' ',' ','*',' ',' ','*',' ','*',' ','*',' ',' ',' ','#'},
//            {'#',' ','#',' ','#',' ','#','*','#',' ','#',' ','#','*','#','*','#',' ','#',' ','#',' ','#','*','#','*','#','*','#',' ','#'},
//            {'#',' ',' ',' ','*',' ',' ',' ',' ',' ','*','*','*',' ',' ','*',' ',' ',' ',' ',' ',' ','*',' ',' ',' ','*',' ','*',' ','#'},
//            {'#',' ','#',' ','#',' ','#',' ','#',' ','#','*','#',' ','#',' ','#','*','#','*','#',' ','#',' ','#',' ','#',' ','#','*','#'},
//            {'#',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','*','*',' ',' ','*',' ',' ','*',' ',' ',' ',' ',' ',' ',' ',' ',' ','#'},
//            {'#',' ','#',' ','#',' ','#',' ','#',' ','#',' ','#',' ','#',' ','#',' ','#','*','#',' ','#','*','#',' ','#',' ','#',' ','#'},
//            {'#','*',' ',' ','*',' ',' ',' ',' ',' ',' ','*',' ',' ','*',' ',' ',' ',' ',' ',' ','*',' ',' ',' ',' ',' ',' ',' ',' ','#'},
//            {'#',' ','#',' ','#',' ','#',' ','#','*','#',' ','#',' ','#',' ','#','*','#','*','#',' ','#',' ','#',' ','#',' ','#',' ','#'},
//            {'#','*',' ',' ',' ',' ','*','*',' ',' ','*',' ',' ',' ',' ',' ',' ',' ','*',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','#'},
//            {'#',' ','#','*','#',' ','#',' ','#',' ','#',' ','#',' ','#',' ','#','*','#',' ','#',' ','#',' ','#',' ','#',' ','#',' ','#'},
//            {'#',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','*',' ',' ',' ','*',' ',' ','*',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','#'},
//            {'#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#'}
//    };

//    public static void loadImage(Texture texture, int x, int y, int width, int height) {
//        batch.begin();
//        batch.draw(texture, x, y, width, height);
//        batch.end();
//        //batch.end();
//    }

    public static void dispose() {
//        for (String name : sprites.keySet()) {
//            sprites.get(name).texture.dispose();
//        }
        bombImg.dispose();
        brickImg.dispose();
        grassImg.dispose();
        playerImg.dispose();
        wallImg.dispose();
        flameX.dispose();
        flameY.dispose();
        speedItem.dispose();
        flameItem.dispose();
        bombSound.dispose();
        placeBonbSound.dispose();
        youDeadSound.dispose();
        backgroundSound.dispose();
        enemyPic.dispose();
        batch.dispose();
        youwinImg.dispose();
        EnemydiedSound.dispose();
        insertItemSound.dispose();
        batch = null;
        bombImg = null;
        brickImg = null;
        grassImg = null;
        playerImg = null;
        wallImg = null;
        enemyPic = null;
    }
}
