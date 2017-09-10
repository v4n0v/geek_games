package ru.geekbrains.geekgame;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;

import ru.geekuniversity.engine.math.Rect;
import ru.geekuniversity.engine.math.Rnd;
import ru.geekuniversity.engine.sprites.Sprite;


public class Explosion extends Sprite {

    private final Sound sndExplosion;
    private float animateInterval = 0.017f;
    private float animateTimer;
    protected Rect worldBounds;

    public Explosion(TextureRegion region, int rows, int cols, int frames, Sound sndExplosion) {
        super(region, rows, cols, frames);
        this.sndExplosion = sndExplosion;

    }

    public void set(float height, Vector2 pos) {
        frame = 0;
        this.pos.set(pos);
        setHeightProportion(height);
        if(sndExplosion.play(0.15f) == -1) throw new RuntimeException("sndExplosion.play() == -1");
    }

    // для генерации рандомных взрыв я сделал еще 1 метод set
    public void set(float height, Rect worldBounds) {
        frame = 0;
        this.worldBounds=worldBounds;
        float posX = Rnd.nextFloat( this.worldBounds.getLeft(),  this.worldBounds.getRight());
        float posY = Rnd.nextFloat( this.worldBounds.getBottom(),  this.worldBounds.getTop());
        this.pos.set(posX, posY);
        setHeightProportion(height);
        if(sndExplosion.play(0.15f) == -1) throw new RuntimeException("sndExplosion.play() == -1");
    }

    @Override
    public void update(float deltaTime) {
        animateTimer += deltaTime;
        if(animateTimer >= animateInterval) {
            animateTimer = 0f;
            if(++frame == regions.length) destroy();
        }
    }

}
