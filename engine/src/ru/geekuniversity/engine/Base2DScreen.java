package ru.geekuniversity.engine;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Base2DScreen implements Screen, InputProcessor {

    protected final Game game;

    public Base2DScreen(Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        System.out.println("show");
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {
        System.out.println("resize: width = " + width + " height = " + height);

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

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        System.out.println("touchDown x = " + screenX + " y = " + screenY);
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
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

    public void resizeScreenLockedRatio(SpriteBatch batch){
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        float screenRatio = screenWidth/screenHeight;
        batch.getProjectionMatrix().idt(). scale(1f, 1f*screenRatio, 1f);
    }

}
