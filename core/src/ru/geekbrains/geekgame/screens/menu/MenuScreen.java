package ru.geekbrains.geekgame.screens.menu;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;

import ru.geekbrains.geekgame.Background;
import ru.geekbrains.geekgame.Explosion;
import ru.geekbrains.geekgame.pools.ExplosionPool;
import ru.geekbrains.geekgame.screens.game.GameScreen;
import ru.geekbrains.geekgame.screens.stars.Star;
import ru.geekuniversity.engine.Base2DScreen;
import ru.geekuniversity.engine.Sprite2DTexture;
import ru.geekuniversity.engine.math.Rect;
import ru.geekuniversity.engine.math.Rnd;
import ru.geekuniversity.engine.ui.ActionListener;
public class MenuScreen extends Base2DScreen implements ActionListener {

    private static final int STARS_COUNT = 250;
    private static final float STAR_HEIGHT = 0.01f;

    private static final float BUTTONS_HEIGHT = 0.15f;
    private static final float BUTTONS_PRESS_SCALE = 0.9f;
    private static final float EXPLOSION_DELAY = 3000f;

    private Sprite2DTexture textureBackground;
    private TextureAtlas atlas;
    private TextureAtlas atlasExplosion;
    private Background background;
    private final Star[] stars = new Star[STARS_COUNT];
    private ButtonExit buttonExit;

    private ButtonNewGame buttonNewGame;

    private Explosion randomExplosion;
    private ExplosionPool explosionPool;

    private Sound sndExplosion;
    private Music bgMusic;
    long lastExplosion;

    public MenuScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();

        textureBackground = new Sprite2DTexture("textures/bg.png");
        atlasExplosion = new TextureAtlas("textures/mainAtlas.tpack");
        atlas = new TextureAtlas("textures/menuAtlas.tpack");
        TextureRegion regionStar = atlas.findRegion("star");

        background = new Background(new TextureRegion(textureBackground));

        for (int i = 0; i < stars.length; i++) {
            float vx = Rnd.nextFloat(-0.005f, 0.005f);
            float vy = Rnd.nextFloat(-0.05f, -0.1f);
            float starHeight = STAR_HEIGHT * Rnd.nextFloat(0.75f, 1f);
            stars[i] = new Star(regionStar, vx, vy, starHeight);
        }

        sndExplosion = Gdx.audio.newSound(Gdx.files.internal("sounds/explosion.wav"));

        // создаем пул взрывов, верем из него экземпляр
        explosionPool = new ExplosionPool(atlasExplosion, sndExplosion);
        randomExplosion=explosionPool.obtain();
        randomExplosion.set(0.1f, this.getWorldBounds());
        lastExplosion = TimeUtils.millis();
        // устанавливаем музыку
        bgMusic = Gdx.audio.newMusic(Gdx.files.internal("music/muzLoop.mp3"));
        bgMusic.setVolume(0.5f);
        bgMusic.setLooping(true);


        buttonNewGame = new ButtonNewGame(atlas, this, BUTTONS_PRESS_SCALE);
        buttonNewGame.setHeightProportion(BUTTONS_HEIGHT);
        buttonExit = new ButtonExit(atlas, this, BUTTONS_PRESS_SCALE);
        buttonExit.setHeightProportion(BUTTONS_HEIGHT);

        System.out.println("show");
    }

    @Override
    protected void resize(Rect worldBounds) {

        background.resize(worldBounds);
        for (int i = 0; i < stars.length; i++) stars[i].resize(worldBounds);

        // задаем позицию взрыва, тем самым передаем в него знаечние мировых координат
        // засекаем время
        //randomExplosion.resize(worldBounds);


        buttonExit.resize(worldBounds);
        buttonNewGame.resize(worldBounds);

    }

    @Override
    protected void touchDown(Vector2 touch, int pointer) {
        buttonExit.touchDown(touch, pointer);
        buttonNewGame.touchDown(touch, pointer);
    }

    @Override
    protected void touchUp(Vector2 touch, int pointer) {
        buttonExit.touchUp(touch, pointer);
        buttonNewGame.touchUp(touch, pointer);
    }

    @Override
    public void actionPerformed(Object src) {
        if(src == buttonExit) {
            System.out.println("exitBtn");
            Gdx.app.exit();
        } else if(src == buttonNewGame) {
            System.out.println("playBtn");
            game.setScreen(new GameScreen(game));
        } else {
            throw new RuntimeException("Unknown src = " + src);
        }
    }

    @Override
    public void render (float delta) {

        update(delta);
        deleteAllDestroyed();
        draw();
        bgMusic.play();


    }

    private void update(float deltaTime) {
        for (int i = 0; i < stars.length; i++) stars[i].update(deltaTime);
        explosionPool.updateActiveSprites(deltaTime);

        // оэкземпляр взрыаа знаем мировые координаты, поэтому просто перезапускаем его через
        // каждые 3 секунды
        if(TimeUtils.millis() - lastExplosion > EXPLOSION_DELAY) {
            randomExplosion=explosionPool.obtain();
            randomExplosion.set(0.1f, this.getWorldBounds());
            lastExplosion=TimeUtils.millis();
        }


    }

    private void draw() {
        Gdx.gl.glClearColor(0.7f, 0.7f, 0.7f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
            background.draw(batch);
            for (int i = 0; i < stars.length; i++) stars[i].draw(batch);
            explosionPool.drawActiveObjects(batch);
            buttonExit.draw(batch);
            buttonNewGame.draw(batch);
        batch.end();

    }
    private void deleteAllDestroyed() {
        explosionPool.freeAllDestroyedActiveObjects();
    }
    @Override
    public void dispose () {
        explosionPool.dispose();
        textureBackground.dispose();
        atlas.dispose();
        bgMusic.dispose();
        super.dispose();
    }
}
