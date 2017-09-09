package ru.geekbrains.geekgame;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by avetc on 08.09.2017.
 */

public class RandomExplosion extends Explosion{
    public RandomExplosion(TextureRegion region, int rows, int cols, int frames, Sound sndExplosion) {
        super(region, rows, cols, frames, sndExplosion);
    }
}
