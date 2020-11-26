package com.mygdx.game.desktop;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.*;
import com.mygdx.game.Menu;

public class DesktopLauncher {
	public static void main (String[] arg) {
		//ScreenManager screenManager = new ScreenManager();
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 208;
		config.height = 496;
		config.resizable = true;
		//config.vSyncEnabled = true;
		config.foregroundFPS = 120;

		new LwjglApplication(new BombermanGame(), config);
		//new LwjglApplication(new Bomberman(), config);
		//new LwjglApplication((ApplicationListener) new Bomberman(screenManager), config);
		//new LwjglApplication(new Menu(), config);
		//new LwjglApplication((ApplicationListener) new Menu(), config);
	}
}
