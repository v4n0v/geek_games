package ru.geekbrains.geekgame.pools;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;


import ru.geekbrains.geekgame.screens.game.EnemyShip;
import ru.geekuniversity.engine.math.Rect;
import ru.geekuniversity.engine.pool.SpritesPool;

/**
 * Created by avetc on 08.09.2017.
 */

public class EnemyPool extends SpritesPool<EnemyShip> {

    private final TextureAtlas atlas;
    Rect worldBounds;

    public EnemyPool(TextureAtlas atlas) {
        this.atlas=atlas;
    }

    @Override
    protected EnemyShip newObject() {
        return new EnemyShip(atlas,worldBounds);

    }

    @Override
    protected void debugLog() {
        System.out.println("EnemyPool change active/free: " + activeObjects.size() + "/" + freeObjects.size());
    }
}
