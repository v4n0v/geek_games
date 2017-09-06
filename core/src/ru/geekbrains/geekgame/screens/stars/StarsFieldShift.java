package ru.geekbrains.geekgame.screens.stars;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekuniversity.engine.math.Rect;
import ru.geekuniversity.engine.math.Rnd;

/**
 * Created by avetc on 06.09.2017.
 */

public class StarsFieldShift extends Star {
    private final int STARS_COUNT=250;
    private final Star[] stars = new Star[STARS_COUNT];
    private static final float STAR_HEIGHT = 0.01f;
    TextureAtlas atlas = new TextureAtlas("textures/menuAtlas.tpack");
    TextureRegion region = atlas.findRegion("star");
    private final Vector2 v= new Vector2();
    private final Vector2 v0= new Vector2();
    private Rect worldBounds;

    public StarsFieldShift(TextureRegion region, float vx, float vy, float width) {
        super(region, vx, vy, width);
    }

    public void generateStars(){
        for (int i = 0; i < stars.length; i++) {
            float vx = Rnd.nextFloat(-0.005f, 0.005f);
            float vy = Rnd.nextFloat(-0.05f, -0.1f);
            float starHeight = STAR_HEIGHT * Rnd.nextFloat(0.75f, 1f);
            stars[i] = new Star(region, vx, vy, starHeight);
        }
    }

    private static final int INVALID_POINTER = -1;
    private int leftPointer = INVALID_POINTER;
    private int rightPointer = INVALID_POINTER;
//
//    @Override
//    public boolean touchDown(Vector2 touch, int pointer) {
//        if(touch.x < worldBounds.pos.x) {
//            if(leftPointer != INVALID_POINTER) return false;
//            leftPointer = pointer;
//            moveLeft();
//        } else {
//            if(rightPointer != INVALID_POINTER) return false;
//            rightPointer = pointer;
//            moveRight();
//        }
//        return false;
//    }
//
//    @Override
//    public boolean touchUp(Vector2 touch, int pointer) {
//        if(pointer == leftPointer) {
//            leftPointer = INVALID_POINTER;
//            if(rightPointer != INVALID_POINTER) moveRight(); else stop();
//        } else if(pointer == rightPointer) {
//            rightPointer = INVALID_POINTER;
//            if(leftPointer != INVALID_POINTER) moveLeft(); else stop();
//        }
//        return false;
//    }
//
//    private boolean pressedLeft;
//    private boolean pressedRight;
//
//    void keyDown(int keycode) {
//        switch (keycode) {
//            case Input.Keys.A:
//            case Input.Keys.LEFT:
//                pressedLeft = true;
//                moveLeft();
//                break;
//            case Input.Keys.D:
//            case Input.Keys.RIGHT:
//                pressedRight = true;
//                moveRight();
//                break;
//            case Input.Keys.UP:
//                frame = 1;
//                break;
//        }
//    }
//
//    void keyUp(int keycode) {
//        switch (keycode) {
//            case Input.Keys.A:
//            case Input.Keys.LEFT:
//                pressedLeft = false;
//                if(pressedRight) moveRight(); else stop();
//                break;
//            case Input.Keys.D:
//            case Input.Keys.RIGHT:
//                pressedRight = false;
//                if(pressedLeft) moveLeft(); else stop();
//                break;
//            case Input.Keys.UP:
//                frame = 0;
//                break;
//        }
//    }
//    private void moveRight() {
//        v.set(0.5f,-0.5f);
//    }
//
//    private void moveLeft() {
//        v.set(-0.5f,-0.5f);
//    }
//
//    private void stop() {
//        v.setZero();
//    }

    @Override
    public void update(float deltaTime) {
        for (int i = 0; i < stars.length; i++) stars[i].update(deltaTime);
    }

    @Override
    public void draw(SpriteBatch batch) {
        for (int i = 0; i < stars.length; i++) stars[i].draw(batch);
    }
}
