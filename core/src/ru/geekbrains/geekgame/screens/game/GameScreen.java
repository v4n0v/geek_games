package ru.geekbrains.geekgame.screens.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.geekgame.Background;
import ru.geekbrains.geekgame.screens.stars.Star;
import ru.geekbrains.geekgame.screens.stars.StarsFieldShift;
import ru.geekuniversity.engine.Base2DScreen;
import ru.geekuniversity.engine.Sprite2DTexture;
import ru.geekuniversity.engine.math.Rect;
import ru.geekuniversity.engine.math.Rnd;

/**
 * Created by avetc on 04.09.2017.
 */

public class GameScreen extends Base2DScreen{
    private Sprite2DTexture textureBackground;
    private TextureAtlas atlas;
    private Background background;
    private MainShip mainShip;
    private static final int STARS_COUNT = 250;
    private static final float STAR_HEIGHT = 0.01f;
    private final Star[] stars = new Star[STARS_COUNT];
    private TextureAtlas atlasMenu;
    boolean outOfBounds=false;


    public GameScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();
        textureBackground = new Sprite2DTexture("textures/bg.png");
        atlas = new TextureAtlas("textures/mainAtlas.tpack");

        atlasMenu=new TextureAtlas("textures/menuAtlas.tpack");
        TextureRegion regionStar = atlasMenu.findRegion("star");
        for (int i = 0; i < stars.length; i++) {
            float vx = Rnd.nextFloat(-0.005f, 0.005f);
            float vy = Rnd.nextFloat(-0.05f, -0.1f);
            float starHeight = STAR_HEIGHT * Rnd.nextFloat(0.75f, 1f);
            stars[i] = new Star(regionStar, vx, vy, starHeight);
        }
        background = new Background(new TextureRegion(textureBackground));
        mainShip = new MainShip(atlas);
    }

    @Override
    protected void resize(Rect worldBounds) {
        background.resize(worldBounds);

        mainShip.resize(worldBounds);
        for (int i = 0; i < stars.length; i++) stars[i].resize(worldBounds);
    }

    @Override
    protected void touchDown(Vector2 touch, int pointer) {
        mainShip.touchDown(touch, pointer);
        for (int i = 0; i < stars.length; i++) stars[i].touchDown(touch, pointer);

    }

    @Override
    protected void touchUp(Vector2 touch, int pointer) {

        mainShip.touchUp(touch, pointer);
        for (int i = 0; i < stars.length; i++) stars[i].touchUp(touch, pointer);
    }

    @Override
    public boolean keyDown(int keycode) {
       mainShip.keyDown(keycode);
       if (mainShip.isInBounds())
            for (int i = 0; i < stars.length; i++) stars[i].keyDown(keycode );

        //}


        return false;

    }

    @Override
    public boolean keyUp(int keycode) {

        // if (!mainShip.isInBounds())
            for (int i = 0; i < stars.length; i++) stars[i].keyUp(keycode );
      //  }
        mainShip.keyUp(keycode);
        return false;
    }

    @Override
    public void render(float delta) {
        update(delta);
        checkCollisions();
        deleteAllDestroyed();
        draw();
    }

    private void update(float deltaTime) {

        for (int i = 0; i < stars.length; i++) stars[i].update(deltaTime);
        mainShip.update(deltaTime);
    }

    private void checkCollisions() {

    }

    private void deleteAllDestroyed() {

    }

    private void draw() {
        Gdx.gl.glClearColor(0.7f, 0.7f, 0.7f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.draw(batch);

        for (int i = 0; i < stars.length; i++) stars[i].draw(batch);
        mainShip.draw(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        textureBackground.dispose();
        atlas.dispose();
        atlasMenu.dispose();
        super.dispose();
    }
}
