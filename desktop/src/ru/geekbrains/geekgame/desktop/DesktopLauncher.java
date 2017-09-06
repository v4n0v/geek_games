package ru.geekbrains.geekgame.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import ru.geekbrains.geekgame.GeekGame;
import ru.geekbrains.geekgame.StarGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
	//	float aspect = 480f / 854f;
		float aspect = 3f/4f;

		config.height = 800;
		config.height = (int)(config.width / aspect);
		new LwjglApplication(new StarGame(), config);
	}

}
