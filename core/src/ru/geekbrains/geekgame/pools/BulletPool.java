package ru.geekbrains.geekgame.pools;

import ru.geekbrains.geekgame.Bullet;
import ru.geekuniversity.engine.pool.SpritesPool;

/**
 * Created by avetc on 08.09.2017.
 */

public class BulletPool extends SpritesPool<Bullet> {

    @Override
    protected Bullet newObject() {
        return new Bullet();
    }

    @Override
    protected void debugLog() {
        // System.out.println("BulletPool change active/free: " + activeObjects.size() + "/" + freeObjects.size());
    }
}
