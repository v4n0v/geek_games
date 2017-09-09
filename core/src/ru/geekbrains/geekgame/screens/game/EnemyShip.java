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

    //public EnemyShip(TextureRegion region, int rows, int cols, int frames) {
    public EnemyShip(TextureAtlas atlas, Rect worldBounds){
        super(atlas.findRegion("enemy0"), 1, 2, 2);
        setHeightProportion(SHIP_HEIGHT);
        this.worldBounds=worldBounds;

       // super(region, rows, cols, frames);
        //float vx = Rnd.nextFloat(-0.005f, 0.005f);

    //    float vy = Rnd.nextFloat(-0.1f, -0.2f);
        v.set(0,-0.15f);
    }

    public void set(
//            int type,
//            TextureRegion region,
//            Vector2 pos0,
//            Vector2 v0,
//            float height,
      //      Rect worldBounds
//            int health
    ) {
        this.type = type;
   //     regions[0] = region;
        float posX = (Rnd.nextFloat(worldBounds.getLeft()+this.getHalfWidth(), worldBounds.getRight()))-this.getHalfWidth();
        float posY = worldBounds.getTop()+this.getHalfHeight();
        pos.set(posX, posY);
        //pos.set(pos0);
    //    v.set(v0);
     //   setHeightProportion(height);
    //    this.worldBounds = worldBounds;
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
//        super.update(deltaTime);
        pos.mulAdd(v, deltaTime);
        if (getTop() < worldBounds.getBottom()) {
            System.out.println("destroyed");
            destroy();
        }
    }

    private void checkBottom(){
      //  if (getBottom() > worldBounds.getTop()) setTop(worldBounds.getBottom());
    }
}
