package ru.geekbrains.geekgame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GeekGame extends ApplicationAdapter {
	private SpriteBatch batch;
	private Texture img;
	private Sprite bgSprite;
	private Texture bgTexture;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		bgTexture= new Texture("bg.png");
		bgSprite = new Sprite(bgTexture);
		bgSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}

	public void renderBackground() {
		bgSprite.draw(batch);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		renderBackground();
		batch.draw(img, 0, 0);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
