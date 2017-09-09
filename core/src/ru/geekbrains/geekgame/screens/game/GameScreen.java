package ru.geekbrains.geekgame.screens.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.geekgame.Background;
import ru.geekbrains.geekgame.Explosion;
import ru.geekbrains.geekgame.pools.BulletPool;
import ru.geekbrains.geekgame.pools.EnemyPool;
import ru.geekbrains.geekgame.pools.ExplosionPool;
import ru.geekbrains.geekgame.screens.stars.Star;
import ru.geekbrains.geekgame.screens.stars.TrackingStar;
import ru.geekuniversity.engine.Base2DScreen;
import ru.geekuniversity.engine.Sprite2DTexture;
import ru.geekuniversity.engine.math.Rect;
import ru.geekuniversity.engine.math.Rnd;

/**
 * Created by avetc on 04.09.2017.
 */

public class GameScreen extends Base2DScreen {

    private static final int STARS_COUNT = 50;
    private static final float STAR_HEIGHT = 0.01f;

    private final BulletPool bulletPool = new BulletPool();
    private Explosion explosion;
    private ExplosionPool explosionPool;

    private Sprite2DTexture textureBackground;
    private TextureAtlas atlas;
    private Background background;
    private final TrackingStar[] stars = new TrackingStar[STARS_COUNT];
    private MainShip mainShip;

    Rect worldBounds;

    private Sound sndExplosion;
    private  EnemyShip enemyShip;
    private EnemyPool enemyPool;
    public GameScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();
        textureBackground = new Sprite2DTexture("textures/bg.png");
        atlas = new TextureAtlas("textures/mainAtlas.tpack");

        sndExplosion = Gdx.audio.newSound(Gdx.files.internal("sounds/explosion.wav"));
        explosionPool = new ExplosionPool(atlas, sndExplosion);

        background = new Background(new TextureRegion(textureBackground));
        mainShip = new MainShip(atlas, bulletPool);


        enemyPool = new EnemyPool(atlas);
       // enemyShip= enemyPool.obtain();

        TextureRegion starRegion = atlas.findRegion("star");

        for (int i = 0; i < stars.length; i++) {
            float vx = Rnd.nextFloat(-0.005f, 0.005f);
            float vy = Rnd.nextFloat(-0.05f, -0.1f);
            float starHeight = STAR_HEIGHT * Rnd.nextFloat(0.75f, 1f);
            stars[i] = new TrackingStar(starRegion, vx, vy, starHeight, mainShip.getV());
        }
    }

    @Override
    protected void resize(Rect worldBounds) {
       // this.worldBounds=worldBounds;
        background.resize(worldBounds);
        for (int i = 0; i < stars.length; i++) stars[i].resize(worldBounds);
        mainShip.resize(worldBounds);
        //enemyShip.resize(worldBounds);
        enemyPool.resize(worldBounds);
    }

    @Override
    protected void touchDown(Vector2 touch, int pointer) {
        mainShip.touchDown(touch, pointer);
        //explosion = explosionPool.obtain();
        enemyShip = enemyPool.obtain();
        enemyShip.set();
       // explosion.set(0.1f, touch);
    }

    @Override
    protected void touchUp(Vector2 touch, int pointer) {
        mainShip.touchUp(touch, pointer);

    }

    @Override
    public boolean keyDown(int keycode) {
        mainShip.keyDown(keycode);
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
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
        bulletPool.updateActiveSprites(deltaTime);

        //enemyShip.update(deltaTime);

        enemyPool.updateActiveSprites(deltaTime);

        explosionPool.updateActiveSprites(deltaTime);
        mainShip.update(deltaTime);
    }

    private void checkCollisions() {

    }

    private void deleteAllDestroyed() {
        bulletPool.freeAllDestroyedActiveObjects();
        explosionPool.freeAllDestroyedActiveObjects();
        enemyPool.freeAllDestroyedActiveObjects();
    }

    private void draw() {
        Gdx.gl.glClearColor(0.7f, 0.7f, 0.7f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        background.draw(batch);
        for (int i = 0; i < stars.length; i++) stars[i].draw(batch);
     //   enemyShip.draw(batch);

        bulletPool.drawActiveObjects(batch);
        explosionPool.drawActiveObjects(batch);
        enemyPool.drawActiveObjects(batch);

        mainShip.draw(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        explosionPool.dispose();
        textureBackground.dispose();
        atlas.dispose();

        bulletPool.dispose();
        enemyPool.dispose();
        explosionPool.dispose();

        sndExplosion.dispose();
        super.dispose();
    }
}