package ru.geekbrains.geekgame;

import com.badlogic.gdx.Game;

import ru.geekbrains.geekgame.screens.menu.MenuScreen;

/**
 * Created by avetc on 22.08.2017.
 */

public class StarGame extends Game {

    @Override
    public void create() {
        setScreen(new MenuScreen(this));
    }
}
