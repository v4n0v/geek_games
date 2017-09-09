package ru.geekbrains.geekgame.pools;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ru.geekbrains.geekgame.screens.game.EnemyShip;
import ru.geekuniversity.engine.math.Rect;
import ru.geekuniversity.engine.pool.SpritesPool;

/**
 * Created by avetc on 08.09.2017.
 */

public class EnemyPool extends SpritesPool<EnemyShip> {

//    private final TextureRegion enemyRegion;
    private final TextureAtlas atlas;
    Rect worldBounds;

    public EnemyPool(TextureAtlas atlas) {
//        String regionName1 = "enemy0";
//        enemyRegion = atlas.findRegion(regionName1);
        this.atlas=atlas;
       // if(enemyRegion == null) throw new RuntimeException("Region " + regionName1 + " not found.");
    }
// private final TextureRegion enemyRegion;


    @Override
    protected EnemyShip newObject() {
        return new EnemyShip(atlas,worldBounds);

    }

    public void resize( Rect worldBounds){
        this.worldBounds=worldBounds;
    }

    @Override
    protected void debugLog() {
        System.out.println("EnemyPool change active/free: " + activeObjects.size() + "/" + freeObjects.size());
    }
}
