package ru.geekbrains.geekgame.screens.stars;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekuniversity.engine.math.Rect;
import ru.geekuniversity.engine.math.Rnd;
import ru.geekuniversity.engine.sprites.Sprite;

public class Star extends Sprite {
     final Vector2 v= new Vector2();
    private final Vector2 vLast= new Vector2();
    private Rect worldBounds;

    public Star(TextureRegion region, float vx, float vy, float width) {
        super(region);
        v.set(vx, vy); vLast.set(v);
        setWidthProportion(width);
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        float posX = Rnd.nextFloat(worldBounds.getLeft(), worldBounds.getRight());
        float posY = Rnd.nextFloat(worldBounds.getBottom(), worldBounds.getTop());
        pos.set(posX, posY);
    }
    @Override
    public void update(float deltaTime) {
        pos.mulAdd(v, deltaTime);
        checkAndHandleBounds();
    }

    protected void checkAndHandleBounds() {
        if (getRight() < worldBounds.getLeft()) setLeft(worldBounds.getRight());
        if (getLeft() > worldBounds.getRight()) setRight(worldBounds.getLeft());
        if (getTop() < worldBounds.getBottom()) setBottom(worldBounds.getTop());
        if (getBottom() > worldBounds.getTop()) setTop(worldBounds.getBottom());
    }

    private static final int INVALID_POINTER = -1;
    private int leftPointer = INVALID_POINTER;
    private int rightPointer = INVALID_POINTER;

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        if(touch.x < worldBounds.pos.x) {
            if(leftPointer != INVALID_POINTER) return false;
            leftPointer = pointer;
            moveLeft();
        } else {
            if(rightPointer != INVALID_POINTER) return false;
            rightPointer = pointer;
            moveRight();
        }
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        if(pointer == leftPointer) {
            leftPointer = INVALID_POINTER;
            if(rightPointer != INVALID_POINTER) moveRight(); else stop();
        } else if(pointer == rightPointer) {
            rightPointer = INVALID_POINTER;
            if(leftPointer != INVALID_POINTER) moveLeft(); else stop();
        }
       // stop();
        return false;
    }

    private boolean pressedLeft;
    private boolean pressedRight;

    public void keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.A:
            case Input.Keys.LEFT:
                pressedLeft = true;
                moveLeft();
                break;
            case Input.Keys.D:
            case Input.Keys.RIGHT:
                pressedRight = true;
                moveRight();
                break;
            case Input.Keys.UP:
            scale=1.65f;
            break;
        }
    }

    public void keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.A:
            case Input.Keys.LEFT:
                pressedLeft = false;
                if(pressedRight) moveRight(); else stop();
                break;
            case Input.Keys.D:
            case Input.Keys.RIGHT:
                pressedRight = false;
                if(pressedLeft) moveLeft(); else stop();
                break;
            case Input.Keys.UP:
                scale=1f;
                break;
        }
      // stop();
    }
    private void moveRight() {
        v.set(-0.25f,-0.15f);
    }

    private void moveLeft() {
         v.set(0.25f,-0.15f);
    }

    public void stop() {
        v.set(vLast);
    }
}

