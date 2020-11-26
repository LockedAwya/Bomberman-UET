package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import static com.mygdx.game.SpritesManager.inMenu;
import static com.mygdx.game.SpritesManager.youdead;

public class BombermanGame extends Game {
	public SpriteBatch batch;
	Bomberman bomberman;
	//private ScreenManager screenManager;

	@Override
	public void create () {
		batch = new SpriteBatch();
		//this.screenManager = screenManager;
		if (inMenu) {
			this.setScreen(new Menu(this));
		}
		//this.setScreen(new Bomberman());
	}

	@Override
	public void render () throws NullPointerException {
		super.render();
	}

	@Override
	public void dispose () {
		super.dispose();
	}
}
