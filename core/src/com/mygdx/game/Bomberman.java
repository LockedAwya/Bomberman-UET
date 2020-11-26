package com.mygdx.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import jdk.jfr.Timespan;
import org.w3c.dom.Text;

import java.util.*;

import static com.mygdx.game.SpritesManager.*;

public class Bomberman extends Game implements Screen {
    //public SpriteBatch batch;
    public SpritesManager spritesManager;
//    private OrthographicCamera camera = new OrthographicCamera();
//    private List<Entity> entities = new ArrayList<>();
//    private List<Entity> stillObjects = new ArrayList<>();
    protected ArrayList<Rectangle> r = new ArrayList<>();
    private float delta = 0;
    private BombermanGame theGame;
    //private TextureRegion[] regions;
    private boolean gameOver = false;
    private Enemy enemy = null;
    private Enemy2 enemy2 = null;
    private Player player = null;
    final Bomberman bomberman = this;

    public static char[][] map = {
            {'#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#'},
            {'#',' ',' ',' ',' ',' ',' ','*','*',' ','*',' ',' ',' ',' ','*',' ',' ',' ','*',' ',' ','*',' ','*',' ','*',' ',' ',' ','#'},
            {'#',' ','#',' ','#',' ','#','*','#','*','#',' ','#','*','#','*','#',' ','#',' ','#',' ','#','*','#','*','#','*','#',' ','#'},
            {'#',' ',' ',' ','*',' ',' ',' ',' ',' ','*','*','*',' ',' ','*',' ',' ',' ',' ',' ',' ','*',' ',' ',' ','*',' ','*',' ','#'},
            {'#',' ','#',' ','#','*','#',' ','#',' ','#','*','#',' ','#',' ','#','*','#','*','#',' ','#',' ','#',' ','#',' ','#','*','#'},
            {'#',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','s',' ','*','*',' ',' ','*',' ',' ','*',' ',' ',' ',' ',' ',' ',' ',' ',' ','#'},
            {'#',' ','#',' ','#',' ','#',' ','#',' ','#',' ','#',' ','#',' ','#',' ','#','*','#',' ','#','*','#',' ','#',' ','#',' ','#'},
            {'#','*',' ',' ','*',' ','*',' ',' ','*',' ','*',' ',' ','*',' ',' ','s',' ',' ',' ','*',' ',' ',' ',' ',' ',' ',' ',' ','#'},
            {'#',' ','#',' ','#',' ','#',' ','#','*','#',' ','#',' ','#',' ','#','*','#','*','#',' ','#',' ','#',' ','#',' ','#',' ','#'},
            {'#','*',' ',' ',' ',' ','*','*',' ',' ','*',' ',' ',' ',' ',' ',' ',' ','*',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','#'},
            {'#',' ','#','*','#',' ','#',' ','#',' ','#',' ','#',' ','#',' ','#','*','#',' ','#',' ','#',' ','#',' ','#',' ','#',' ','#'},
            {'#',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','*',' ',' ',' ','*',' ',' ','*',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','#'},
            {'#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#'}
    };

    public Bomberman() {

    }

    public Bomberman(BombermanGame theGame) {
        state = GAME_READY;
        this.theGame = theGame;
        spritesManager = new SpritesManager();
        enemy = new Enemy(theEnemy, enemyPic);
        enemy2 = new Enemy2(theEnemy2, enemyPic);
        player = new Player(character, playerImg);
        enemyDied = false;
        enemy2Died = false;
        init();
    }

    @Override
    public void create() {

    }

    @Override
    public void show() {
        //Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        //spritesManager.batch.draw(texture,0, 0, 32, 32);
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 31; j++) {
                if (map[i][j] == '#') { //wall
                    batch.draw(wallImg, i * 16, j * 16, 16, 16);
                    //loadImage(wallImg, i*16,j*16,16,16);
                } else if (map[i][j] == ' ') { //grass
                    batch.draw(grassImg, i * 16, j * 16);
                } else if (map[i][j] == '*') { //brick
                    batch.draw(brickImg, i * 16, j * 16, 16, 16);
                    //r.add(pos);
                } else if (map[i][j] == 's') { //speed item
                    batch.draw(speedItem, i * 16, j * 16, 16, 16);
                } else if (map[i][j] == 'p') { //portal
                    batch.draw(portalItem, i * 16, j * 16, 16, 16);
                }
//                else if (map[i][j] == 'f') { //flame item
//                    batch.draw(SpritesManager.flameItem, i * 16, j * 16, 16, 16);
//                }
                if (bomb_array[i][j] == '.') { //set bomb
                    batch.draw(bombImg, i * 16, j * 16, 16, 16);
                } else if (bomb_array[i][j] == 'h') { //flame horizontally
                    batch.draw(flameX, i * 16, j * 16, 16, 16);
                } else if (bomb_array[i][j] == 'v') { //flame vertically
                    batch.draw(flameY, i * 16, j * 16, 16, 16);
                } else if (bomb_array[i][j] == 'm') { //flame middle
                    batch.draw(flameMiddleItem, i * 16, j * 16, 16, 16);
                }
            }
        }
        //batch.draw(playerImg, character.x, character.y, character.width, character.height);
        if (state != GAME_OVER) {
            batch.draw(playerImg, character.x, character.y, character.width, character.height);
        }
        batch.end();
    }

    public void update() throws NullPointerException {
        //this.enemy = enemy;
        //batch.begin();
        drawStates();
        //enemy.update();
        if (enemyDied) {
            enemy = null;
        } else {
            enemy.update();
        }
        if (state == GAME_OVER) {
            player = null;
        } else if (state == GAME_READY) {
            player.dropBomb((int)character.x/16, (int)character.y/16);
            //batch.end();
            player.speedUpgraded();
        }
        if (enemy2Died) {
            enemy2 = null;
        } else {
            enemy2.update();
        }
        //enemy.move((int)enemySpeed);
    }

    @Override
    public void render(float delta) {
        //SpritesManager.batch.begin();
        show();
        batch.begin();
        update();
        batch.end();
        //SpritesManager.batch.end();
    }

    @Override
    public void pause() {
        //if (state == GAME_RUNNING) state = GAME_PAUSED;
    }

    public void drawPaused() {
        batch.draw(pauseImg, 100, 100, 200, 200);
    }

    public void drawGameOver() {
        batch.draw(gameOverImg, 0, 0, 270, 270);
    }

    public void drawVictory() {
        batch.draw(youwinImg, 0, 0, 270, 270);
    }

    public void init() {
        //rendering brick
        map[3][4] = '*';
        map[1][7] = '*';
        map[1][8] = '*';
        map[1][10]= '*';
        map[1][15]='*';
        map[1][19]='*';
        map[1][22]='*';
        map[1][24]='*';
        map[1 ][26]='*';
        map[2 ][7] ='*';
        map[2 ][13]='*';
        map[2 ][15]='*';
        map[2 ][23]='*';
        map[2 ][25]='*';
        map[2 ][27]='*';
        map[3 ][10]='*';
        map[3 ][11]='*';
        map[3 ][12]='*';
        map[3 ][15]='*';
        map[3 ][22]='*';
        map[3 ][26]='*';
        map[3 ][28]='*';
        map[4 ][11]='*';
        map[4 ][17]='*';
        map[4 ][19]='*';
        map[4 ][29]='*';
        map[5 ][13]='*';
        map[5 ][14]='*';
        map[5 ][17]='*';
        map[5 ][20]='*';
        map[6 ][19]='*';
        map[6 ][23]='*';
        map[7 ][1] ='*';
        map[7 ][4] ='*';
        map[7 ][11]='*';
        map[7 ][14]='*';
        map[7 ][21]='*';
        map[8 ][9] ='*';
        map[8 ][17]='*';
        map[8 ][19]='*';
        map[9 ][1] ='*';
        map[9][6]  ='*';
        map[9][7]  ='*';
        map[9][10] ='*';
        map[9][18] ='*';
        map[10] [3]='*';
        map[10] [17]='*';
        map[11] [12]='*';
        map[11] [16]='*';
        map[11] [19]='*';
        //rendering speed items
        map[5][11] = 's';
        map[7][17] = 's';
    }

    public void drawStates() {
        //this.game = game;
        //SpritesManager.batch.begin();
        switch (state) {
            case GAME_READY:
                System.out.println("GAME_READY");
                break;
            case GAME_MENU:
                System.out.println("GAME_MENU");
                break;
            case GAME_PAUSED:
                drawPaused();
                break;
            case YOU_WIN:
                //presentLevelEnd();
                drawVictory();
                if (Gdx.input.isKeyPressed(Input.Keys.C)) {
                    //youdead = false;
                    inMenu = true;
                    state = GAME_MENU;
                    theGame.setScreen(new Menu(theGame));
                }
                System.out.println("YOU WIN");
                break;
            case GAME_OVER:
                System.out.println("GAME_OVER");
                youdead = true;
                drawGameOver();
                if (Gdx.input.isKeyPressed(Input.Keys.C)) {
//                    youdead = false;
//                    inMenu = true;
                    state = GAME_MENU;
                    theGame.setScreen(new Menu(theGame));
                }
                break;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            state = GAME_PAUSED;
        } else if (Gdx.input.isKeyPressed(Input.Keys.P)) {
            state = GAME_READY;
        }
    }

    @Override
    public void resume() {
        super.resume();
    }

    @Override
    public void hide() {

    }


    @Override
    public void dispose() {
        bombImg.dispose();
        brickImg.dispose();
        grassImg.dispose();
        playerImg.dispose();
        wallImg.dispose();
        flameX.dispose();
        flameY.dispose();
        dispose();
        batch.dispose();
        SpritesManager.dispose();
    }
}
