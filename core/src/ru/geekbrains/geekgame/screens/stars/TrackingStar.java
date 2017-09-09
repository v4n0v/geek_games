package ru.geekbrains.geekgame.screens.stars;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by avetc on 08.09.2017.
 */

public class TrackingStar extends Star {
    private final Vector2 trackingV;
    public TrackingStar(TextureRegion region, float vx, float vy, float width, Vector2 trackingV) {
        super(region, vx, vy, width);
        this.trackingV=trackingV;
    }

    private final Vector2 sumV = new Vector2();

    @Override
    public void update(float deltaTime) {
        sumV.setZero().mulAdd(trackingV, 0.2f).rotate(180).add(v);
        pos.mulAdd(sumV, deltaTime);
        checkAndHandleBounds();
    }

}
