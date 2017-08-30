package ru.geekbrains.geekgame.screens.buttons;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekuniversity.engine.sprites.Sprite;

public class Button extends Sprite  {
    String name;
    public Button(TextureRegion region, float size, String name) {
        super(region);
        this.setSize(size,size);
        this.name=name;
    }


    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        if (isMe(touch)) {
            System.out.println("touchDown");
            rollOver();
        }
        //return super.touchDown(touch, pointer);
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        if (isMe(touch)){
           System.out.println("touchUp");
        }
       // return super.touchUp(touch, pointer);
        return false;
    }

    public void rollOver(){
        this.setSize(getWidth()*0.10f, getHeight()*0.10f);
    }


}
