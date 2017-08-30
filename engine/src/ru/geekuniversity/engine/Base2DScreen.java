package ru.geekuniversity.engine;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;

import ru.geekuniversity.engine.math.MatrixUtils;
import ru.geekuniversity.engine.math.Rect;

public class Base2DScreen implements Screen, InputProcessor {

    protected final float WORLD_HEIGHT=1f;
    public SpriteBatch batch;
    protected final Game game;

    private final Rect screenBounds = new Rect(); // граница экрана, области рисования в пкс
    private final Rect worldBounds = new Rect();  // граница проекции мировых координат
    private final Rect glBounds = new Rect(0f, 0f, 1, 1); // дефолтная границы прекции мира = gl

    protected final Matrix4 matWorldToGL = new Matrix4();

    protected final Matrix3 matScreenToWorld = new Matrix3();

    public Base2DScreen(Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        System.out.println("show");
        Gdx.input.setInputProcessor(this);
        if (batch!=null) throw new RuntimeException("batch != null, повторная установка scree wo dispose");
        batch = new SpriteBatch();
    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {
        System.out.println("resize: width = " + width + " height = " + height);
        screenBounds.setSize(width, height);
        screenBounds.setLeft(0);
        screenBounds.setBottom(0);

        float aspect = width/(float)height;
        worldBounds.setHeight(WORLD_HEIGHT);
        worldBounds.setWidth(WORLD_HEIGHT*aspect);
        MatrixUtils.calcTransitionMatrix(matWorldToGL, worldBounds, glBounds);
        batch.setProjectionMatrix(matWorldToGL);
        MatrixUtils.calcTransitionMatrix(matScreenToWorld, screenBounds, worldBounds);
        resize(worldBounds);
    }
    protected void resize(Rect worldBounds){

    }

    @Override
    public void pause() {
        System.out.println("pause");
    }

    @Override
    public void resume() {
        System.out.println("resume");
    }

    @Override
    public void hide() {
        System.out.println("hide");
        dispose();

    }

    @Override
    public void dispose() {
        System.out.println("dispose");
        batch.dispose();
        batch = null;
    }


    @Override
    public boolean keyDown(int keycode) {
        System.out.print("keydown");
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        System.out.println("keyUp");
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        System.out.println("key typed '"+character+"'");
        return false;
    }

    private final Vector2 touch = new Vector2();

    protected void touchDown(Vector2 touch, int pointer){

}
    protected void touchUp(Vector2 touch, int pointer){

    }
    protected void touchDragged(Vector2 touch, int pointer){

    }
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        touch.set(screenX, Gdx.graphics.getHeight()-screenX).mul(matScreenToWorld);
        touchDown(touch, pointer);
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        touch.set(screenX, Gdx.graphics.getHeight()-screenX).mul(matScreenToWorld);
        touchUp(touch, pointer);
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        touch.set(screenX, Gdx.graphics.getHeight()-screenX).mul(matScreenToWorld);
        touchDragged(touch, pointer);
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }


}
