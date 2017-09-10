package ru.geekbrains.geekgame.screens.game;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.geekgame.Ship;
import ru.geekuniversity.engine.math.Rect;
import ru.geekuniversity.engine.math.Rnd;

/**
 * Created by avetc on 08.09.2017.
 */

public class EnemyShip extends Ship {
    private static final float SHIP_HEIGHT = 0.15f;

    private final Vector2 v=new Vector2();
    private int health;
    private int type;
    Rect worldBounds;

    public EnemyShip(TextureAtlas atlas, Rect worldBounds){
        super(atlas.findRegion("enemy0"), 1, 2, 2);
        setHeightProportion(SHIP_HEIGHT);
        this.worldBounds=worldBounds;
        v.set(0,-0.15f);
    }

    public void set(
            Rect worldBounds
    ) {
        float posX = (Rnd.nextFloat(worldBounds.getLeft()+getHalfWidth(), worldBounds.getRight()))-getHalfWidth();
        float posY = worldBounds.getTop()+this.getHalfHeight();
        pos.set(posX, posY);
        this.worldBounds = worldBounds;
         this.health = health;
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;

        float posX = (Rnd.nextFloat(worldBounds.getLeft()+this.getHalfWidth(), worldBounds.getRight()-this.getHalfWidth()));
        float posY = worldBounds.getTop()+this.getHalfHeight();
        pos.set(posX, posY);
    }

    @Override
    public void update(float deltaTime) {

        pos.mulAdd(v, deltaTime);
        if (getTop() < worldBounds.getBottom()) {
            System.out.println("destroyed");
            destroy();
        }
    }

}
