package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.Project13class;
//Запускалка для пк
public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = Project13class.WIDTH;
		config.height = Project13class.HEIGHT;
		config.title = Project13class.TITLE;
		new LwjglApplication(new Project13class(), config);
	}
}
