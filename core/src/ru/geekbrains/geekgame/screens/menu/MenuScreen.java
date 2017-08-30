package ru.geekbrains.geekgame.screens.menu;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.geekgame.screens.buttons.Button;
import ru.geekbrains.geekgame.screens.stars.Star;
import ru.geekuniversity.engine.Base2DScreen;
import ru.geekuniversity.engine.Sprite2DTexture;
import ru.geekuniversity.engine.math.Rect;
import ru.geekuniversity.engine.math.Rnd;
import ru.geekuniversity.engine.sprites.Sprite;

public class MenuScreen extends Base2DScreen {
    float aspect = 9f / 16f;
    private static final float STAR_WIDTH = 0.01f;
    private static final int STARS_COUNT =200;
    //private SpriteBatch batch;
//    private Texture textureCircle;
//    TextureRegion texRegion;
//    private Sprite circle;
    Texture textureBackground;
    Background background;
  //  private Star star;
    private TextureAtlas atlas;
    public MenuScreen(Game game) {
        super(game);
    }
    private Star[] stars;
    // объявляем сущности кнопок
    private Button btPlay;
    private Button btExit;

    @Override
    public void show() {

        super.show();
        textureBackground = new Sprite2DTexture("textures/bg.png");
        background = new Background(new TextureRegion(textureBackground));
        atlas = new TextureAtlas("textures/mainAtlas.pack");
        TextureRegion regionStar = atlas.findRegion("star");

        float vx;  float vy; float starWidth;
        stars = new Star[STARS_COUNT];
        for (int i = 0; i < STARS_COUNT; i++) {
            vx = Rnd.nextFloat(-0.005f, 0.005f);
            vy = Rnd.nextFloat(-0.05f, -0.1f);
            starWidth = STAR_WIDTH * Rnd.nextFloat(0.75f, 1f);
            stars[i] = new Star(regionStar, vx, vy, starWidth);
        }

        // описываем регионы кнопок
        TextureRegion regionBtPlay = atlas.findRegion("btPlay");
        TextureRegion regionBtExit = atlas.findRegion("btExit");
        // инициализируем кнопки, задаем размер и положение относительно высоты экрана в мировой системе
        // присваиваем имя кнопке для инициализации нажатия по нему
        btPlay = new Button(regionBtPlay, 0.15f,"btPlay");
        btExit = new Button(regionBtExit, 0.15f,"btExit");

        btPlay.setLeft(-(WORLD_HEIGHT/2*aspect));
        btPlay.setBottom(-(WORLD_HEIGHT/2));
        btExit.setLeft((WORLD_HEIGHT/2*aspect)-btExit.getWidth());
        btExit.setBottom(-(WORLD_HEIGHT/2));

//        //btPlay.setSize(0.15f, 0.15f);
//        btExit.setLeft(0.15f);
//        btExit.setBottom(-0.38f);
        //btExit.setSize(0.15f, 0.15f);

//        float vx = Rnd.nextFloat(-0.005f, 0.005f);
//        float vy = Rnd.nextFloat(-0.05f, 0.1f);
//        float starWidth = STAR_WIDTH * Rnd.nextFloat(0.75f, 1f);
//        star = new Star(regionStar, vx, vy, starWidth);
////        batch = new SpriteBatch();
//
//        //textureCircle.setWrap();
//        textureCircle = new Sprite2DTexture("circle.png");
//        textureCircle.setFilter(Texture.TextureFilter.MipMapLinearNearest, Texture.TextureFilter.Linear);
//        circle = new Sprite(new TextureRegion(textureCircle));
//        //circle.setSize(1f,1f);
//        circle.setWidthProportion(0.67f);

    }
    @Override
    protected void resize(Rect worldBounds) {
        background.resize(worldBounds);
        for (int i = 0; i < STARS_COUNT; i++) {
            stars[i].resize(worldBounds);
        }
        btPlay.resize(worldBounds);
        btExit.resize(worldBounds);
        //star.resize(worldBounds);
    }

    @Override
    protected void touchDown(Vector2 touch, int pointer) {

        //System.out.println(touch);
        btPlay.touchDown(touch,pointer);
        btExit.touchDown(touch,pointer);
    }

    @Override
    public void render(float delta) {
        update(delta);
        draw();
    }
    private void draw( ){
        Gdx.gl.glClearColor(0.7f, 0.7f, 0.7f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.draw(batch);

        for (int i = 0; i < STARS_COUNT; i++) {
            stars[i].draw(batch);
        }
        btPlay.draw(batch);
        btExit.draw(batch);
    //    star.draw(batch);
        batch.end();
    }
    private void update(float deltaTime){

        for (int i = 0; i < STARS_COUNT; i++) {
            stars[i].update(deltaTime);
        }
        btPlay.update(deltaTime);
        btExit.update(deltaTime);
        //star.update(deltaTime);
    }
    @Override
    public void dispose() {
        batch.dispose();
        atlas.dispose();

        textureBackground.dispose();
//        textureCircle.dispose();
        super.dispose();
    }
}
