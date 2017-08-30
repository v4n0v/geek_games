package ru.geekbrains.geekgame.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import ru.geekbrains.geekgame.GeekGame;
import ru.geekbrains.geekgame.StarGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		float aspect = 9f / 16f;
		//float aspect = 3f/4f;

		config.width = 450;
		config.height = (int)(config.width / aspect);
		new LwjglApplication(new StarGame(), config);
	}

}
