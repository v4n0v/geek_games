package ru.geekuniversity.engine.sprites;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekuniversity.engine.math.Rect;

/**
 * Created by avetc on 28.08.2017.
 */

public class Sprite extends Rect {
    protected float angle;
    protected float scale = 1f;
    protected TextureRegion region;
    protected TextureRegion[] regions;
    protected int frame;

    public Sprite(TextureRegion region) {
        if (region == null) throw new RuntimeException("Create Sprite with null region");
        regions = new TextureRegion[1];
        regions[0] = region;
    }
    public void resize(Rect worldBounds) {
    }
    public void draw(SpriteBatch batch) {
        batch.draw(
                regions[frame],
                getLeft(), getBottom(),
                halfWidth, halfHeight,
                getWidth(), getHeight(),
                scale, scale, angle
        );
    }
    public void setHeightProportion(float height) {
        setHeight(height);
        float aspect = regions[frame].getRegionWidth() / (float) regions[frame].getRegionHeight();
        setWidth(height * aspect);
    }
    public void setWidthProportion(float width) {
        setWidth(width);
        float aspect = regions[frame].getRegionWidth() / (float) regions[frame].getRegionHeight();
        setHeight(width / aspect);
    }
    public boolean touchDown(Vector2 touch, int pointer) {
        return false;
    }

    public boolean touchUp(Vector2 touch, int pointer) {
        return false;
    }

    public boolean touchDragged(Vector2 touch, int pointer) {
        return false;
    }
    public void update(float deltaTime) {

    }
    @Override
    public String toString() {
        return "Sprite: " + "angle=" + angle + ", scale= " + scale + super.toString();

    }
}
