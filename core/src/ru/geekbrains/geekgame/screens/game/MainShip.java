package ru.geekbrains.geekgame.screens.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import ru.geekuniversity.engine.math.Rect;
import ru.geekuniversity.engine.sprites.Sprite;

class MainShip extends Sprite {

    private static final float SHIP_HEIGHT = 0.15f;
    private static final float BOTTOM_MARGIN = 0.05f;

    private Rect worldBounds;
    private final Vector2 v0 = new Vector2(0.5f, 0f);
    private final Vector2 v = new Vector2();
    private boolean inBounds;
    private boolean leftOutOfBounds;
    private boolean rightOutOfBounds;

    MainShip(TextureAtlas atlas) {
        super(atlas.findRegion("main_ship"), 1, 2, 2);
        setHeightProportion(SHIP_HEIGHT);
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        setBottom(worldBounds.getBottom() + BOTTOM_MARGIN);
    }

    // возврат значения проверки на выход за область экрана
    public boolean isInBounds() {

        return !(rightOutOfBounds || leftOutOfBounds);

    }

    private static final int INVALID_POINTER = -1;
    private int leftPointer = INVALID_POINTER;
    private int rightPointer = INVALID_POINTER;

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        if (touch.x < worldBounds.pos.x) {
            if (leftPointer != INVALID_POINTER) return false;
            leftPointer = pointer;
            moveLeft();
        } else {
            if (rightPointer != INVALID_POINTER) return false;
            rightPointer = pointer;
            moveRight();
        }
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        if (pointer == leftPointer) {
            leftPointer = INVALID_POINTER;
            if (rightPointer != INVALID_POINTER) moveRight();
            else stop();
        } else if (pointer == rightPointer) {
            rightPointer = INVALID_POINTER;
            if (leftPointer != INVALID_POINTER) moveLeft();
            else stop();
        }
        return false;
    }

    private boolean pressedLeft;
    private boolean pressedRight;


    void keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.A:
            case Input.Keys.LEFT:
              //  if (rightOutOfBounds)rightOutOfBounds=false;
                if (rightOutOfBounds) {
                    setRight(worldBounds.getRight());
                }
                pressedLeft = true;
                moveLeft();
                rightOutOfBounds=false;
                break;
            case Input.Keys.D:
            case Input.Keys.RIGHT:
                if (leftOutOfBounds) {
                    setLeft(worldBounds.getLeft());

                }
                pressedRight = true;
                moveRight();
                leftOutOfBounds=false;
                break;
            case Input.Keys.UP:
                frame = 1;
                break;
        }
    }

    void keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.A:
            case Input.Keys.LEFT:
                pressedLeft = false;
                if (pressedRight)
                    moveRight();
                else stop();
                break;
            case Input.Keys.D:
            case Input.Keys.RIGHT:

                pressedRight = false;
                if (pressedLeft)
                    moveLeft();
                else stop();

                break;
            case Input.Keys.UP:
                frame = 0;
                break;
        }
    }

    private void moveRight() {
//        if (leftOutOfBounds) {
//            setLeft(worldBounds.getLeft());
//        }
        v.set(v0);

    }

    private void moveLeft() {
//        if (rightOutOfBounds) {
//            setRight(worldBounds.getRight());
//        }
        v.set(v0).rotate(180);
    }

    private void stop() {
        v.setZero();
    }

    @Override
    public void update(float deltaTime) {
        checkBounds();
        if (isInBounds())
            pos.mulAdd(v, deltaTime);


    }

    //   проверка на выход за область экрана
    private void checkBounds() {
        if (getLeft() < worldBounds.getLeft())
            leftOutOfBounds = true;

        else {
            leftOutOfBounds = false;

        }
        if (getRight() > worldBounds.getRight()) {
            rightOutOfBounds = true;

        } else {
            rightOutOfBounds = false;

        }

    }
}
