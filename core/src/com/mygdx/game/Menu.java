package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import javax.print.ServiceUIFactory;

import static com.mygdx.game.SpritesManager.*;

/**
 * https://happycoding.io/tutorials/libgdx/game-screens
 */

public class Menu implements Screen {

    private BombermanGame game;
    private Stage stage;
    //private Networking theNetwork;
    Game TheGame;
    final Menu mainMenu = this;

    public Menu(BombermanGame game) {
        //this.screenManager = screenManager;
        state = GAME_MENU;
        this.game = game;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        SpritesManager.backgroundSound.play();
    }

    @Override
    public void show() {
        Table table = new Table();
        //bomberman = new Bomberman();
        table.setFillParent(true);
        table.setDebug(true);
        stage.addActor(table);
        //Game game;

        Skin skin =  new Skin(Gdx.files.internal("uiskin.json"));

        TextButton playGame = new TextButton("Play", skin);
        TextButton playOnline = new TextButton("Play Online", skin);
        TextButton exit = new TextButton("Exit", skin);

        //add button to table
        table.add(playGame).fillX().uniformX();
        table.row().pad(10,0,10,0);
        table.add(playOnline).fillX().uniformX();
        table.row();
        table.add(exit).fillX().uniformX();

        //create button listeners
        playGame.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                state = GAME_READY;
                inMenu = false;
                //youdead = false;
                SpritesManager.backgroundSound.dispose();
                mainMenu.dispose();
                game.setScreen(new Bomberman(game));
            }
        });
//        playOnline.addListener(new ChangeListener() {
//            @Override
//            public void changed(ChangeEvent event, Actor actor) {
//                SpritesManager.backgroundSound.dispose();
//                mainMenu.dispose();
//            }
//        });
        exit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });
        //game.setScreen(new Bomberman(game));
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
        SpritesManager.backgroundSound.dispose();
    }
}
