package ru.geekbrains.geekgame.screens.menu;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ru.geekuniversity.engine.Base2DScreen;
import ru.geekuniversity.engine.Sprite2DTexture;
import ru.geekuniversity.engine.sprites.Sprite;

public class MenuScreen extends Base2DScreen {

    //private SpriteBatch batch;
    private Texture textureCircle;
    TextureRegion texRegion;
    private Sprite circle;

    public MenuScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {

        super.show();
        batch = new SpriteBatch();

        //textureCircle.setWrap();
        textureCircle = new Sprite2DTexture("circle.png");
        textureCircle.setFilter(Texture.TextureFilter.MipMapLinearNearest, Texture.TextureFilter.Linear);
        circle = new Sprite(new TextureRegion(textureCircle));
        //circle.setSize(1f,1f);
        circle.setWidthProportion(0.67f);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.7f, 0.7f, 0.7f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();

        circle.draw(batch);

        batch.end();
    }


    @Override
    public void dispose() {
        batch.dispose();
        textureCircle.dispose();
        super.dispose();
    }
}
